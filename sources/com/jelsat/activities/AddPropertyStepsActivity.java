package com.jelsat.activities;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.framework.ErrorCodes;
import com.businesslogic.propertydetail.IPropertyDetailView;
import com.businesslogic.propertydetail.PropertyDetailPresenter;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.widgets.FancyButton;

public class AddPropertyStepsActivity extends BaseAppCompactActivity implements IPropertyDetailView {
    @BindView(2131361851)
    TextView addYourPropertyTV;
    @BindView(2131361904)
    LinearLayout basicInfoStepOneLayout;
    @BindView(2131362089)
    LinearLayout descriptionPropertyStepTwoLayout;
    private boolean isFromEdit = false;
    @BindView(2131362432)
    LinearLayout priceSettingStepThreeLayout;
    private PropertyDetailPresenter propertyDetailPresenter = new PropertyDetailPresenter(this, RetrofitClient.getAPIService());
    private PropertyDetailResponse propertyDetailResponse;
    @BindView(2131362790)
    FancyButton unPublishProperty;

    @OnClick({2131362089})
    public void clickOnDescriptionPropertyStepTwoLayout() {
    }

    @OnClick({2131362432})
    public void clickOnPriceSettingStepThreeLayout() {
    }

    @OnClick({2131362790})
    public void clickOnPublishProperty() {
    }

    public int getActivityLayout() {
        return R.layout.activity_add_property_steps;
    }

    public void onSuccess(BookingCancelResponse bookingCancelResponse) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131361904})
    public void clickOnBasicInfoStepOneLayout() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(StringConstants.IS_FROM_EDIT_PROPERTY, this.isFromEdit);
        goToActivity(AddPropertyListOfPropertyTypeActivity.class, bundle);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        changeColorsOfViewsBasedOnNavigation(this.basicInfoStepOneLayout, R.color.normal_text_color, R.color.light_text_color, R.drawable.ic_arrow_right);
        changeColorsOfViewsBasedOnNavigation(this.descriptionPropertyStepTwoLayout, R.color.property_step_diable, R.color.property_step_diable, R.drawable.ic_arrow_right_disable);
        changeColorsOfViewsBasedOnNavigation(this.priceSettingStepThreeLayout, R.color.property_step_diable, R.color.property_step_diable, R.drawable.ic_arrow_right_disable);
    }

    private void changeColorsOfViewsBasedOnNavigation(LinearLayout linearLayout, @ColorRes int i, @ColorRes int i2, @DrawableRes int i3) {
        int childCount = linearLayout.getChildCount();
        for (int i4 = 0; i4 < childCount; i4++) {
            View childAt = linearLayout.getChildAt(i4);
            if (childAt instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) childAt;
                ((TextView) viewGroup.getChildAt(0)).setTextColor(applyColor(i));
                ((TextView) viewGroup.getChildAt(1)).setTextColor(applyColor(i2));
            }
            if (childAt instanceof ImageView) {
                ((ImageView) childAt).setImageResource(i3);
            }
        }
    }

    public void onSuccess(PropertyDetailResponse propertyDetailResponse) {
        this.propertyDetailResponse = propertyDetailResponse;
    }

    public void onError(APIError aPIError, int i) {
        if (i != ErrorCodes.API_ERROR) {
            if (i == ErrorCodes.INTERNAL_SERVER_ERROR) {
                showToast(getString(R.string.internal_server_error));
            } else if (i == ErrorCodes.SOCKET_TIME_OUT_ERROR_CODE) {
                showToast(getString(R.string.socket_time_out_error));
            } else if (i == 511) {
                showToast(getString(R.string.network_error));
            }
        } else if (aPIError != null) {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onDetachedFromWindow() {
        if (this.propertyDetailPresenter != null) {
            this.propertyDetailPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
