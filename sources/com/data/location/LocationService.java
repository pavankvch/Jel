package com.data.location;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build.VERSION;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsRequest.Builder;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;

public class LocationService {
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    public static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 68;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private static final int REQUEST_CHECK_SETTINGS = 1;
    private static final String TAG = "LocationService";
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    private final WeakReference<Activity> activityWeakReference;
    private boolean isFromAddProperty;
    private boolean isShowGPSDialog;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private SettingsClient mSettingsClient;

    public class LocationChangedEvent {
        private boolean isFromAddProperty;
        private final LatLng location;

        public LocationChangedEvent(Location location, boolean z) {
            this.location = new LatLng(location.getLatitude(), location.getLongitude());
            this.isFromAddProperty = z;
        }

        public LatLng getLocation() {
            return this.location;
        }

        public boolean isFromAddProperty() {
            return this.isFromAddProperty;
        }
    }

    public class LocationRequestEvent {
    }

    public class PermissionDeniedEvent {
        boolean isCheckNeverAsk;

        public PermissionDeniedEvent(boolean z) {
            this.isCheckNeverAsk = z;
        }

        public boolean isCheckNeverAsk() {
            return this.isCheckNeverAsk;
        }
    }

    public LocationService(Activity activity, boolean z, boolean z2) {
        this.activityWeakReference = new WeakReference(activity);
        this.isShowGPSDialog = z;
        this.isFromAddProperty = z2;
        if (isGooglePlayServicesAvailable(activity)) {
            this.mFusedLocationClient = LocationServices.getFusedLocationProviderClient(activity);
            this.mSettingsClient = LocationServices.getSettingsClient(activity);
            createLocationCallback();
            createLocationRequest();
            buildLocationSettingsRequest();
        }
    }

    private boolean isGooglePlayServicesAvailable(Context context) {
        GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        int isGooglePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        if (isGooglePlayServicesAvailable == 0) {
            return true;
        }
        if (instance.isUserResolvableError(isGooglePlayServicesAvailable)) {
            instance.getErrorDialog((Activity) context, isGooglePlayServicesAvailable, 1000).show();
        } else {
            Toast.makeText(context, "This device is not supported.", 1).show();
            ((Activity) context).finish();
        }
        return null;
    }

    private void createLocationCallback() {
        this.mLocationCallback = new LocationCallback() {
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.d("TAG", locationResult.toString());
                EventBus.getDefault().post(new LocationChangedEvent(locationResult.getLastLocation(), LocationService.this.isFromAddProperty));
                LocationService.this.stopLocationUpdates();
            }
        };
    }

    private void createLocationRequest() {
        this.mLocationRequest = new LocationRequest();
        this.mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        this.mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        this.mLocationRequest.setPriority(100);
    }

    private void buildLocationSettingsRequest() {
        Builder builder = new Builder();
        builder.addLocationRequest(this.mLocationRequest);
        this.mLocationSettingsRequest = builder.build();
    }

    public void checkForLocationPermission() {
        Activity activity = (Activity) this.activityWeakReference.get();
        if (activity != null && !requestLocation(activity.getApplicationContext())) {
            ActivityCompat.requestPermissions(activity, new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"}, 68);
        }
    }

    private boolean requestLocation(Context context) {
        if (ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") != 0 || ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_COARSE_LOCATION") != null) {
            return null;
        }
        if (this.isShowGPSDialog != null) {
            startLocationUpdates();
        } else {
            getLastKnownLocation();
        }
        return true;
    }

    private void getLastKnownLocation() {
        Activity activity = (Activity) this.activityWeakReference.get();
        if (activity != null) {
            this.mFusedLocationClient.getLastLocation().addOnCompleteListener(activity, new OnCompleteListener<Location>() {
                public void onComplete(@NonNull Task<Location> task) {
                    if (!task.isSuccessful() || task.getResult() == null) {
                        Log.w(LocationService.TAG, "getLastKnownLocation:exception", task.getException());
                    } else {
                        EventBus.getDefault().post(new LocationChangedEvent((Location) task.getResult(), LocationService.this.isFromAddProperty));
                    }
                }
            });
        }
    }

    public void setShowGPSDialog(boolean z) {
        this.isShowGPSDialog = z;
    }

    public void requestPermissionResult(int i, String[] strArr, int[] iArr) {
        if (i == 68) {
            Activity activity = (Activity) this.activityWeakReference.get();
            if (iArr.length <= null || iArr[0] != null) {
                if (iArr[0] == -1) {
                    Log.i(TAG, "User interaction was cancelled.");
                    if (VERSION.SDK_INT >= 23) {
                        if (activity.shouldShowRequestPermissionRationale("android.permission.ACCESS_FINE_LOCATION") != 0) {
                            EventBus.getDefault().post(new PermissionDeniedEvent(false));
                        } else {
                            EventBus.getDefault().post(new PermissionDeniedEvent(true));
                        }
                    }
                    EventBus.getDefault().post(new PermissionDeniedEvent(false));
                    return;
                }
                EventBus.getDefault().post(new PermissionDeniedEvent(true));
            } else if (activity != null) {
                requestLocation(activity.getApplicationContext());
            }
        }
    }

    private void startLocationUpdates() {
        final Activity activity = (Activity) this.activityWeakReference.get();
        if (activity != null) {
            EventBus.getDefault().post(new LocationRequestEvent());
            this.mSettingsClient.checkLocationSettings(this.mLocationSettingsRequest).addOnSuccessListener(activity, new OnSuccessListener<LocationSettingsResponse>() {
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                    Log.i(LocationService.TAG, "All location settings are satisfied.");
                    LocationService.this.mFusedLocationClient.requestLocationUpdates(LocationService.this.mLocationRequest, LocationService.this.mLocationCallback, Looper.myLooper());
                }
            }).addOnFailureListener(activity, new OnFailureListener() {
                public void onFailure(@NonNull Exception exception) {
                    int statusCode = ((ApiException) exception).getStatusCode();
                    if (statusCode != 6) {
                        if (statusCode == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE) {
                            exception = "Location settings are inadequate, and cannot be fixed here. Fix in Settings.";
                            Log.e(LocationService.TAG, exception);
                            Toast.makeText(activity.getApplicationContext(), exception, 1).show();
                        }
                        return;
                    }
                    Log.i(LocationService.TAG, "Location settings are not satisfied. Attempting to upgrade location settings ");
                    try {
                        ((ResolvableApiException) exception).startResolutionForResult(activity, 1);
                    } catch (Exception exception2) {
                        Log.i(LocationService.TAG, "PendingIntent unable to execute request.");
                        exception2.printStackTrace();
                    } catch (Exception exception22) {
                        exception22.printStackTrace();
                    }
                }
            });
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            switch (i2) {
                case -1:
                    Log.i(TAG, "User agreed to make required location settings changes.");
                    startLocationUpdates();
                    return;
                case 0:
                    Log.i(TAG, "User chose not to make required location settings changes.");
                    break;
                default:
                    break;
            }
        }
    }

    private void stopLocationUpdates() {
        Activity activity = (Activity) this.activityWeakReference.get();
        if (activity != null) {
            this.mFusedLocationClient.removeLocationUpdates(this.mLocationCallback).addOnCompleteListener(activity, new OnCompleteListener<Void>() {
                public void onComplete(@NonNull Task<Void> task) {
                    try {
                        if (!task.isSuccessful() || task.getResult() == null) {
                            Log.w(LocationService.TAG, "stopLocationUpdates:exception ", task.getException());
                            return;
                        }
                        String access$200 = LocationService.TAG;
                        StringBuilder stringBuilder = new StringBuilder("stopLocationUpdates");
                        stringBuilder.append(String.valueOf(task.isSuccessful()));
                        Log.w(access$200, stringBuilder.toString());
                    } catch (Task<Void> task2) {
                        task2.printStackTrace();
                    }
                }
            });
        }
    }
}
