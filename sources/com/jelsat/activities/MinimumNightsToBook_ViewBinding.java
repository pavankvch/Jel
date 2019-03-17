package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class MinimumNightsToBook_ViewBinding implements Unbinder {
    private MinimumNightsToBook target;
    private View view2131361889;
    private View view2131361891;
    private View view2131361966;
    private View view2131362519;
    private View view2131362531;

    @UiThread
    public MinimumNightsToBook_ViewBinding(MinimumNightsToBook minimumNightsToBook) {
        this(minimumNightsToBook, minimumNightsToBook.getWindow().getDecorView());
    }

    @UiThread
    public MinimumNightsToBook_ViewBinding(final MinimumNightsToBook minimumNightsToBook, View view) {
        this.target = minimumNightsToBook;
        View findRequiredView = Utils.findRequiredView(view, R.id.backButton, "field 'backButton' and method 'backbutton'");
        minimumNightsToBook.backButton = (ImageView) Utils.castView(findRequiredView, R.id.backButton, "field 'backButton'", ImageView.class);
        this.view2131361891 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                minimumNightsToBook.backbutton();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.saveAndExit, "field 'saveAndExit' and method 'saveAndExit'");
        minimumNightsToBook.saveAndExit = (TextView) Utils.castView(findRequiredView, R.id.saveAndExit, "field 'saveAndExit'", TextView.class);
        this.view2131362531 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                minimumNightsToBook.saveAndExit();
            }
        });
        minimumNightsToBook.minNights = (Spinner) Utils.findRequiredViewAsType(view, R.id.minNights, "field 'minNights'", Spinner.class);
        minimumNightsToBook.bookingType = (Spinner) Utils.findRequiredViewAsType(view, R.id.bookingType, "field 'bookingType'", Spinner.class);
        findRequiredView = Utils.findRequiredView(view, R.id.cancelationPolicesEdt, "field 'cancelationPolicesEdt' and method 'cancelationpolicy'");
        minimumNightsToBook.cancelationPolicesEdt = (EditText) Utils.castView(findRequiredView, R.id.cancelationPolicesEdt, "field 'cancelationPolicesEdt'", EditText.class);
        this.view2131361966 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                minimumNightsToBook.cancelationpolicy();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.reviewProperty, "field 'reviewProperty' and method 'reviewYourProperty'");
        minimumNightsToBook.reviewProperty = (Button) Utils.castView(findRequiredView, R.id.reviewProperty, "field 'reviewProperty'", Button.class);
        this.view2131362519 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                minimumNightsToBook.reviewYourProperty();
            }
        });
        minimumNightsToBook.calenderLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.calenderLayout, "field 'calenderLayout'", LinearLayout.class);
        findRequiredView = Utils.findRequiredView(view, R.id.availabilty_pricing, "field 'availabilityPricing' and method 'availabilityPricing'");
        minimumNightsToBook.availabilityPricing = (TextView) Utils.castView(findRequiredView, R.id.availabilty_pricing, "field 'availabilityPricing'", TextView.class);
        this.view2131361889 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                minimumNightsToBook.availabilityPricing();
            }
        });
        minimumNightsToBook.editTimeLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.editTimeLayout, "field 'editTimeLayout'", LinearLayout.class);
        minimumNightsToBook.checkInTimeEdt = (EditText) Utils.findRequiredViewAsType(view, R.id.checkInTimeEdt, "field 'checkInTimeEdt'", EditText.class);
        minimumNightsToBook.checkOutTimeEdt = (EditText) Utils.findRequiredViewAsType(view, R.id.checkOutTimeEdt, "field 'checkOutTimeEdt'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        MinimumNightsToBook minimumNightsToBook = this.target;
        if (minimumNightsToBook == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        minimumNightsToBook.backButton = null;
        minimumNightsToBook.saveAndExit = null;
        minimumNightsToBook.minNights = null;
        minimumNightsToBook.bookingType = null;
        minimumNightsToBook.cancelationPolicesEdt = null;
        minimumNightsToBook.reviewProperty = null;
        minimumNightsToBook.calenderLayout = null;
        minimumNightsToBook.availabilityPricing = null;
        minimumNightsToBook.editTimeLayout = null;
        minimumNightsToBook.checkInTimeEdt = null;
        minimumNightsToBook.checkOutTimeEdt = null;
        this.view2131361891.setOnClickListener(null);
        this.view2131361891 = null;
        this.view2131362531.setOnClickListener(null);
        this.view2131362531 = null;
        this.view2131361966.setOnClickListener(null);
        this.view2131361966 = null;
        this.view2131362519.setOnClickListener(null);
        this.view2131362519 = null;
        this.view2131361889.setOnClickListener(null);
        this.view2131361889 = null;
    }
}
