package com.jelsat.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.jelsat.R;

public class BookingPersonalDetailsActivity_ViewBinding implements Unbinder {
    private BookingPersonalDetailsActivity target;
    private View view2131361892;
    private View view2131361959;
    private View view2131362074;
    private View view2131362346;
    private View view2131362357;

    @UiThread
    public BookingPersonalDetailsActivity_ViewBinding(BookingPersonalDetailsActivity bookingPersonalDetailsActivity) {
        this(bookingPersonalDetailsActivity, bookingPersonalDetailsActivity.getWindow().getDecorView());
    }

    @UiThread
    public BookingPersonalDetailsActivity_ViewBinding(final BookingPersonalDetailsActivity bookingPersonalDetailsActivity, View view) {
        this.target = bookingPersonalDetailsActivity;
        bookingPersonalDetailsActivity.userName = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name, "field 'userName'", TextView.class);
        bookingPersonalDetailsActivity.userEmail = (TextView) Utils.findRequiredViewAsType(view, R.id.user_email, "field 'userEmail'", TextView.class);
        bookingPersonalDetailsActivity.userMobileNumber = (TextView) Utils.findRequiredViewAsType(view, R.id.user_mobile_number, "field 'userMobileNumber'", TextView.class);
        bookingPersonalDetailsActivity.userDateOfBirth = (TextView) Utils.findRequiredViewAsType(view, R.id.user_date_of_birth, "field 'userDateOfBirth'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.date_of_birth_layout, "field 'dateOfBirthLayout' and method 'clickOnDateOfBirthLayout'");
        bookingPersonalDetailsActivity.dateOfBirthLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.date_of_birth_layout, "field 'dateOfBirthLayout'", LinearLayout.class);
        this.view2131362074 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPersonalDetailsActivity.clickOnDateOfBirthLayout();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.national_id_layout, "field 'nationalIdLayout' and method 'clickOnNationalIdLayout'");
        bookingPersonalDetailsActivity.nationalIdLayout = (LinearLayout) Utils.castView(findRequiredView, R.id.national_id_layout, "field 'nationalIdLayout'", LinearLayout.class);
        this.view2131362346 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPersonalDetailsActivity.clickOnNationalIdLayout();
            }
        });
        bookingPersonalDetailsActivity.uploadNationalIdTv = (TextView) Utils.findRequiredViewAsType(view, R.id.upload_national_id_tv, "field 'uploadNationalIdTv'", TextView.class);
        bookingPersonalDetailsActivity.dateOfBirthView = Utils.findRequiredView(view, R.id.date_of_birth_view, "field 'dateOfBirthView'");
        bookingPersonalDetailsActivity.nationalIdImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.national_id_IV, "field 'nationalIdImageView'", ImageView.class);
        findRequiredView = Utils.findRequiredView(view, R.id.back_arrow_IV, "method 'clickOnBack'");
        this.view2131361892 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPersonalDetailsActivity.clickOnBack();
            }
        });
        findRequiredView = Utils.findRequiredView(view, R.id.cancel_TV, "method 'clickOnCancel'");
        this.view2131361959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPersonalDetailsActivity.clickOnCancel();
            }
        });
        view = Utils.findRequiredView(view, R.id.next_button, "method 'clickOnNextButton'");
        this.view2131362357 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                bookingPersonalDetailsActivity.clickOnNextButton();
            }
        });
    }

    @CallSuper
    public void unbind() {
        BookingPersonalDetailsActivity bookingPersonalDetailsActivity = this.target;
        if (bookingPersonalDetailsActivity == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        bookingPersonalDetailsActivity.userName = null;
        bookingPersonalDetailsActivity.userEmail = null;
        bookingPersonalDetailsActivity.userMobileNumber = null;
        bookingPersonalDetailsActivity.userDateOfBirth = null;
        bookingPersonalDetailsActivity.dateOfBirthLayout = null;
        bookingPersonalDetailsActivity.nationalIdLayout = null;
        bookingPersonalDetailsActivity.uploadNationalIdTv = null;
        bookingPersonalDetailsActivity.dateOfBirthView = null;
        bookingPersonalDetailsActivity.nationalIdImageView = null;
        this.view2131362074.setOnClickListener(null);
        this.view2131362074 = null;
        this.view2131362346.setOnClickListener(null);
        this.view2131362346 = null;
        this.view2131361892.setOnClickListener(null);
        this.view2131361892 = null;
        this.view2131361959.setOnClickListener(null);
        this.view2131361959 = null;
        this.view2131362357.setOnClickListener(null);
        this.view2131362357 = null;
    }
}
