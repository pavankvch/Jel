package com.jelsat.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.location.places.AutocompleteFilter.Builder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlaceAutocomplete.IntentBuilder;
import org.bouncycastle.crypto.tls.CipherSuite;
import org.greenrobot.eventbus.EventBus;

public class GooglePlaceUtils {
    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA;
    private boolean isNeedFilter;

    public class PlaceReceivedEvent {
        private final boolean isNeedFilter;
        private final Place place;

        public PlaceReceivedEvent(Place place, boolean z) {
            this.place = place;
            this.isNeedFilter = z;
        }

        public Place getPlace() {
            return this.place;
        }

        public boolean isNeedFilter() {
            return this.isNeedFilter;
        }
    }

    public void onActivityResult(Context context, int i, int i2, Intent intent) {
        if (i == this.PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (i2 == -1) {
                EventBus.getDefault().post(new PlaceReceivedEvent(PlaceAutocomplete.getPlace(context, intent), this.isNeedFilter));
            } else if (i2 == 2) {
                Log.i(ProfilePictureView.TAG, PlaceAutocomplete.getStatus(context, intent).getStatusMessage());
            }
        }
    }

    public void openSearch(Activity activity, boolean z, String str) {
        try {
            this.isNeedFilter = z;
            IntentBuilder intentBuilder = new IntentBuilder(1);
            if (z) {
                intentBuilder.setFilter(new Builder().setTypeFilter(5).setCountry(str).build());
            }
            activity.startActivityForResult(intentBuilder.build(activity), this.PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (Activity activity2) {
            activity2.printStackTrace();
        } catch (Activity activity22) {
            activity22.printStackTrace();
        }
    }
}
