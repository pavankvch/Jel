package com.jelsat.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.data.searchproperty.SearchProperty;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.CancelableCallback;
import com.google.android.gms.maps.GoogleMap.OnCameraIdleListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.SnapshotReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition.Builder;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.jelsat.R;
import com.jelsat.events.MarkerOnClickEvent;
import com.jelsat.googlemap.IconGenerator;
import de.hdodenhof.circleimageview.CircleImageView;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.greenrobot.eventbus.EventBus;

public class GoogleMapUtils {
    private Context context;
    private GoogleMap mGoogleMap;
    private Marker mSelectedMarker;
    private Map<Marker, SearchProperty> markerPropertyMap = new HashMap();

    public class CameraAnimationFinishEvent {
        private LatLng latLng;

        public CameraAnimationFinishEvent(LatLng latLng) {
            this.latLng = latLng;
        }

        public LatLng getLatLng() {
            return this.latLng;
        }
    }

    public class CameraIdleEvent {
        private LatLng latLng;

        public CameraIdleEvent(LatLng latLng) {
            this.latLng = latLng;
        }

        public LatLng getLatLng() {
            return this.latLng;
        }
    }

    public class GoogleMapSnapShot {
        private Bitmap bitmap;

        public GoogleMapSnapShot(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }
    }

    public GoogleMapUtils(Context context) {
        this.context = context;
    }

    public void onGoogleMapReady(GoogleMap googleMap, boolean z) {
        this.mGoogleMap = googleMap;
        this.mGoogleMap.getUiSettings().setAllGesturesEnabled(true);
        this.mGoogleMap.getUiSettings().setRotateGesturesEnabled(false);
        this.mGoogleMap.getUiSettings().setMapToolbarEnabled(false);
    }

    public void setInitialMapCenter(LatLng latLng) {
        if (latLng != null) {
            this.mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 7.0f), new CancelableCallback() {
                public void onFinish() {
                    EventBus.getDefault().post("IntialMapAnimation");
                }

                public void onCancel() {
                    Log.d(getClass().getSimpleName(), "map animate camera failed when setting initial map center");
                }
            });
        }
    }

    public void updateMapCentre(final LatLng latLng) {
        this.mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new Builder().target(latLng).zoom(12.0f).bearing(0.0f).tilt(0.0f).build()), 1000, new CancelableCallback() {
            public void onFinish() {
                EventBus.getDefault().post(new CameraAnimationFinishEvent(latLng));
            }

            public void onCancel() {
                Log.d(getClass().getSimpleName(), "map animate camera failed when updating map center");
            }
        });
    }

    public void initCameraIdleListener() {
        this.mGoogleMap.setOnCameraIdleListener(new OnCameraIdleListener() {
            public void onCameraIdle() {
                EventBus.getDefault().post(new CameraIdleEvent(GoogleMapUtils.this.mGoogleMap.getCameraPosition().target));
            }
        });
    }

    public void loadMarkers(Map<LatLng, SearchProperty> map) {
        this.mGoogleMap.setMaxZoomPreference(15.0f);
        this.mGoogleMap.clear();
        IconGenerator iconGenerator = new IconGenerator(this.context);
        if (!(this.markerPropertyMap == null || this.markerPropertyMap.isEmpty())) {
            this.markerPropertyMap.clear();
        }
        this.mSelectedMarker = null;
        map.size();
        map = map.entrySet().iterator();
        while (map.hasNext()) {
            Entry entry = (Entry) map.next();
            addIcon(iconGenerator, String.format(Locale.getDefault(), "%s %.2f", new Object[]{this.context.getString(R.string.saudi_currency), Float.valueOf(((SearchProperty) entry.getValue()).getPrice())}), (LatLng) entry.getKey(), (SearchProperty) entry.getValue());
        }
    }

    public void initMarkerOnClickListener() {
        this.mGoogleMap.setOnMarkerClickListener(new OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {
                final SearchProperty searchProperty = (SearchProperty) GoogleMapUtils.this.markerPropertyMap.get(marker);
                EventBus.getDefault().post(new MarkerOnClickEvent(marker.getPosition(), searchProperty));
                if (GoogleMapUtils.this.mSelectedMarker != null) {
                    final SearchProperty searchProperty2 = (SearchProperty) GoogleMapUtils.this.markerPropertyMap.get(GoogleMapUtils.this.mSelectedMarker);
                    final Marker access$200 = GoogleMapUtils.this.mSelectedMarker;
                    Glide.with(GoogleMapUtils.this.context).load(searchProperty2.getImage()).listener(new RequestListener<Drawable>() {
                        public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                            return false;
                        }

                        public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                            access$200.setZIndex(null);
                            access$200.setIcon(BitmapDescriptorFactory.fromBitmap(GoogleMapUtils.this.createCustomMarker(GoogleMapUtils.this.context, searchProperty2.getImage(), "", false, searchProperty2.getType(), drawable)));
                            return null;
                        }
                    }).preload();
                }
                GoogleMapUtils.this.mSelectedMarker = marker;
                Glide.with(GoogleMapUtils.this.context).load(searchProperty.getImage()).listener(new RequestListener<Drawable>() {
                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                        return false;
                    }

                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                        obj = new StringBuilder();
                        obj.append(GoogleMapUtils.this.context.getString(R.string.sar_string));
                        obj.append(" ");
                        obj.append(searchProperty.getPrice());
                        obj.append(" ");
                        String stringBuilder = obj.toString();
                        GoogleMapUtils.this.mSelectedMarker.setZIndex(1.0f);
                        GoogleMapUtils.this.mSelectedMarker.setIcon(BitmapDescriptorFactory.fromBitmap(GoogleMapUtils.this.createCustomMarker(GoogleMapUtils.this.context, searchProperty.getImage(), stringBuilder, true, searchProperty.getType(), drawable)));
                        return null;
                    }
                }).preload();
                return true;
            }
        });
    }

    public void addMarker(LatLng latLng, SearchProperty searchProperty, boolean z) {
        if (this.mGoogleMap != null) {
            this.mGoogleMap.clear();
            if (z) {
                this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
                this.mGoogleMap.addMarker(new MarkerOptions().icon(bitmapDescriptorFromVector(R.drawable.ic_location1)).position(latLng).title(searchProperty.getName()).snippet(searchProperty.getAddress()));
                return;
            }
            this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
            this.mGoogleMap.addCircle(new CircleOptions().center(latLng).radius(0).fillColor(Utils.applyColor(this.context, true)).strokeColor(Utils.applyColor(this.context, true)).strokeWidth(2.0f));
        }
    }

    private BitmapDescriptor bitmapDescriptorFromVector(int i) {
        i = ContextCompat.getDrawable(this.context, i);
        i.setBounds(0, 0, i.getIntrinsicWidth(), i.getIntrinsicHeight());
        Bitmap createBitmap = Bitmap.createBitmap(i.getIntrinsicWidth(), i.getIntrinsicHeight(), Config.ARGB_8888);
        i.draw(new Canvas(createBitmap));
        return BitmapDescriptorFactory.fromBitmap(createBitmap);
    }

    private void addIcon(final IconGenerator iconGenerator, String str, final LatLng latLng, final SearchProperty searchProperty) {
        Glide.with(this.context).load(searchProperty.getImage()).listener(new RequestListener<Drawable>() {
            public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                return false;
            }

            public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                GoogleMapUtils.this.markerPropertyMap.put(GoogleMapUtils.this.mGoogleMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromBitmap(GoogleMapUtils.this.createCustomMarker(GoogleMapUtils.this.context, searchProperty.getImage(), "Jelsat", false, searchProperty.getType(), drawable))).position(latLng).anchor(iconGenerator.getAnchorU(), iconGenerator.getAnchorV())), searchProperty);
                return null;
            }
        }).preload();
    }

    public Bitmap createCustomMarker(Context context, String str, String str2, boolean z, String str3, Drawable drawable) {
        if (z) {
            z = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.custom_marker_layout, null);
            ((RelativeLayout) z.findViewById(R.id.tohide)).setVisibility(0);
            CircleImageView circleImageView = (CircleImageView) z.findViewById(R.id.user_dp);
            TextView textView = (TextView) z.findViewById(R.id.name);
            textView.setText(str2);
            if (str3.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) != null) {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_instant_booking_white), null);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(null, null, context.getResources().getDrawable(R.drawable.ic_24hour_booking_white), null);
            }
            circleImageView.setImageDrawable(drawable);
            str2 = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(str2);
            z.setLayoutParams(new LayoutParams(52, -2));
            z.measure(str2.widthPixels, str2.heightPixels);
            z.layout(0, 0, str2.widthPixels, str2.heightPixels);
            z.buildDrawingCache();
            context = Bitmap.createBitmap(z.getMeasuredWidth(), z.getMeasuredHeight(), Config.ARGB_8888);
            z.draw(new Canvas(context));
            return context;
        }
        z = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.custom_marker_layout, null);
        ((RelativeLayout) z.findViewById(R.id.tohide)).setVisibility(8);
        CircleImageView circleImageView2 = (CircleImageView) z.findViewById(R.id.user_dp);
        ((TextView) z.findViewById(R.id.name)).setText(str2);
        circleImageView2.setBackground(context.getResources().getDrawable(R.drawable.map_border_back_for_image));
        circleImageView2.setImageDrawable(drawable);
        str2 = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(str2);
        z.setLayoutParams(new LayoutParams(52, -2));
        z.measure(str2.widthPixels, str2.heightPixels);
        z.layout(0, 0, str2.widthPixels, str2.heightPixels);
        z.buildDrawingCache();
        context = Bitmap.createBitmap(z.getMeasuredWidth(), z.getMeasuredHeight(), Config.ARGB_8888);
        z.draw(new Canvas(context));
        return context;
    }

    public void removeMarker(SearchProperty searchProperty) {
        for (Entry entry : this.markerPropertyMap.entrySet()) {
            if (((SearchProperty) entry.getValue()).getPropertyId().equalsIgnoreCase(searchProperty.getPropertyId())) {
                Marker marker = (Marker) entry.getKey();
                marker.remove();
                this.markerPropertyMap.remove(marker);
                return;
            }
        }
    }

    public void moveMapBasedOnLatLng(LatLng latLng) {
        this.mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13.0f));
    }

    public void getGoogleMapScreenShot() {
        this.mGoogleMap.snapshot(new SnapshotReadyCallback() {
            public void onSnapshotReady(Bitmap bitmap) {
                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 50, byteArrayOutputStream);
                bitmap = byteArrayOutputStream.toByteArray();
                EventBus.getDefault().post(new GoogleMapSnapShot(BitmapFactory.decodeByteArray(bitmap, 0, bitmap.length)));
            }
        });
    }
}
