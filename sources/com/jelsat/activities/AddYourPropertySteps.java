package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.media.ExifInterface;
import android.support.v7.app.AlertDialog.Builder;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.PublishProperty.IPublishProperty;
import com.businesslogic.PublishProperty.PublishPropertyPresenter;
import com.businesslogic.propertydetail.IPropertyDetailView;
import com.businesslogic.propertydetail.PropertyDetailPresenter;
import com.data.addproperty.Steps;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.PropertyDetailRequest;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.publishproperty.PublishPropertyRequest;
import com.data.publishproperty.PublishPropertyResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.widgets.FancyButton;

public class AddYourPropertySteps extends BaseAppCompactActivity implements IPublishProperty, IPropertyDetailView {
    @BindView(2131361811)
    TextView StepOneTxV;
    @BindView(2131361812)
    TextView StepThreeTxV;
    @BindView(2131361813)
    TextView StepTwoTxV;
    @BindView(2131361893)
    ImageView back_arrow_property;
    Boolean checkIntent;
    Boolean checkPublishOrUnpublish = Boolean.valueOf(false);
    Bundle dataParseStep1;
    Bundle extraBundle;
    String fromListingPropId;
    String fromStep1;
    String fromStep2;
    String fromStepsPropertyId;
    String mainStep;
    private PropertyDetailPresenter propertyDetailPresenter = new PropertyDetailPresenter(this, RetrofitClient.getAPIService());
    private PropertyDetailResponse propertyDetailResponse;
    private PublishPropertyPresenter publishPropertyPresenter = new PublishPropertyPresenter(this, RetrofitClient.getAPIService());
    PublishPropertyRequest publishPropertyRequest;
    @BindView(2131362624)
    TextView stepOneDisc;
    @BindView(2131362625)
    TextView stepThreeDisc;
    @BindView(2131362626)
    ImageView stepThreeImageview;
    @BindView(2131362627)
    TextView stepTwoDisc;
    @BindView(2131362628)
    ImageView stepTwoImageview;
    @BindView(2131362629)
    LinearLayout step_one;
    @BindView(2131362630)
    LinearLayout step_three;
    @BindView(2131362631)
    LinearLayout step_two;
    String subStep;
    @BindView(2131362789)
    FancyButton unPublishButton;

    public int getActivityLayout() {
        return R.layout.add_your_property;
    }

    public void onSuccess(BookingCancelResponse bookingCancelResponse) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.step_two.setEnabled(false);
        this.step_three.setEnabled(false);
        this.unPublishButton.setVisibility(8);
        this.extraBundle = new Bundle();
        if (getIntent().getExtras() != null) {
            if (getIntent().getStringExtra("FromActivity").equalsIgnoreCase("fromProfileHostFragment") != null) {
                this.checkIntent = Boolean.valueOf(getIntent().getExtras().getBoolean("checkIntent"));
            } else if (getIntent().getStringExtra("FromActivity").equalsIgnoreCase("fromStep1") != null) {
                this.extraBundle = getIntent().getExtras();
                this.checkIntent = Boolean.valueOf(getIntent().getExtras().getBoolean("checkIntent"));
                this.fromStep1 = getIntent().getStringExtra("FromActivity");
                if (!(this.fromStep1 == null || this.fromStep1.trim().isEmpty() != null || this.fromStep1.equalsIgnoreCase("fromStep1") == null)) {
                    this.fromStepsPropertyId = this.extraBundle.getString("PropertyId");
                }
            } else if (getIntent().getStringExtra("FromActivity").equalsIgnoreCase("fromStep2") != null) {
                this.extraBundle = getIntent().getExtras();
                this.checkIntent = Boolean.valueOf(getIntent().getExtras().getBoolean("checkIntent"));
                this.fromStep2 = getIntent().getStringExtra("FromActivity");
                if (!(this.fromStep2 == null || this.fromStep2.trim().isEmpty() != null || this.fromStep2.equalsIgnoreCase("fromStep2") == null)) {
                    if (this.extraBundle.getString("editPropertyId") != null) {
                        this.fromStepsPropertyId = this.extraBundle.getString("editPropertyId");
                    } else if (this.extraBundle.getString("PropertyId") != null) {
                        this.fromStepsPropertyId = this.extraBundle.getString("PropertyId");
                    }
                }
            } else if (getIntent().getStringExtra("FromActivity").equalsIgnoreCase("propertyListing") != null) {
                this.checkIntent = Boolean.valueOf(getIntent().getExtras().getBoolean("checkIntent"));
                this.checkPublishOrUnpublish = Boolean.valueOf(getIntent().getExtras().getBoolean("checkPublishOrUnpublish"));
                if (this.checkIntent.booleanValue() != null) {
                    this.fromListingPropId = getIntent().getStringExtra("listingPropertyId");
                    if (this.fromListingPropId.equalsIgnoreCase("") == null) {
                        this.fromStepsPropertyId = this.fromListingPropId;
                    }
                }
            }
        }
    }

    @OnClick({2131362629})
    public void stepOne() {
        Intent intent;
        if (this.checkIntent.booleanValue()) {
            intent = new Intent(this, ListOfProperty.class);
            intent.putExtras(this.extraBundle);
            intent.putExtras(this.dataParseStep1);
            intent.putExtra("checkIntent", true);
        } else {
            intent = new Intent(this, ListOfProperty.class);
            intent.putExtras(this.extraBundle);
            if ((this.fromStep1 == null || this.fromStep1.trim().isEmpty()) && (this.fromStep2 == null || this.fromStep2.trim().isEmpty())) {
                intent.putExtra("checkIntent", false);
            } else {
                intent.putExtras(this.dataParseStep1);
                intent.putExtra("checkIntent", true);
            }
        }
        startActivity(intent);
    }

    @OnClick({2131362631})
    public void stepTwo() {
        if (this.checkIntent.booleanValue()) {
            Intent intent = new Intent(this, HouseRulesActivity.class);
            intent.putExtras(this.extraBundle);
            intent.putExtras(this.dataParseStep1);
            intent.putExtra("editPropertyId", this.fromListingPropId);
            intent.putExtra("checkIntent", true);
            startActivity(intent);
            return;
        }
        intent = new Intent(this, HouseRulesActivity.class);
        intent.putExtras(this.extraBundle);
        if (this.fromStep2 == null || this.fromStep2.trim().isEmpty()) {
            intent.putExtra("checkIntent", false);
        } else if (this.fromStep2.equalsIgnoreCase("fromStep2")) {
            intent.putExtra("editPropertyId", this.fromListingPropId);
            intent.putExtra("checkIntent", true);
        }
        intent.putExtras(this.dataParseStep1);
        startActivity(intent);
    }

    @OnClick({2131362630})
    public void stepThree() {
        Intent intent;
        if (this.checkIntent.booleanValue()) {
            if (this.mainStep.equalsIgnoreCase("finished")) {
                intent = new Intent(this, MinimumNightsToBook.class);
            } else {
                intent = new Intent(this, CostPerNightActivity.class);
                if (this.fromStepsPropertyId != null) {
                    intent.putExtra(StringConstants.PROPERTY_ID, this.fromStepsPropertyId);
                } else {
                    intent.putExtra(StringConstants.PROPERTY_ID, this.fromListingPropId);
                }
            }
            if (this.fromStepsPropertyId != null) {
                intent.putExtra(StringConstants.PROPERTY_ID, this.fromStepsPropertyId);
            } else {
                intent.putExtra(StringConstants.PROPERTY_ID, this.fromListingPropId);
            }
            intent.putExtras(this.extraBundle);
            intent.putExtra("checkIntent", true);
        } else {
            intent = new Intent(this, CostPerNightActivity.class);
            intent.putExtras(this.extraBundle);
            intent.putExtra(StringConstants.PROPERTY_ID, this.fromStepsPropertyId);
            intent.putExtra("checkIntent", false);
        }
        intent.putExtras(this.dataParseStep1);
        startActivity(intent);
    }

    @OnClick({2131362789})
    public void unPublishProperty() {
        if (this.checkPublishOrUnpublish.booleanValue()) {
            Intent intent = new Intent(this, PublishPropertyActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("PropertyId", this.fromListingPropId);
            bundle.putParcelable("fullInfo", this.propertyDetailResponse.getFullInfo());
            bundle.putParcelable("basicInfo", this.propertyDetailResponse.getPropertyInfo());
            intent.putExtras(bundle);
            startActivity(intent);
            return;
        }
        Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.addYourProperty_confirmation));
        builder.setMessage(getString(R.string.addYourProperty_sure_to_unpublish));
        builder.setCancelable(false);
        builder.setPositiveButton(getString(R.string.addYourProperty_ok), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                AddYourPropertySteps.this.publishPropertyRequest = new PublishPropertyRequest();
                AddYourPropertySteps.this.publishPropertyRequest.setPropertyId(AddYourPropertySteps.this.fromListingPropId);
                AddYourPropertySteps.this.publishPropertyRequest.setStatus(0);
                AddYourPropertySteps.this.publishPropertyPresenter.getPublishPropertyResult(AddYourPropertySteps.this.getString(R.string.please_wait), AddYourPropertySteps.this.publishPropertyRequest);
            }
        });
        builder.setNegativeButton(getString(R.string.add_image_cancel), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.show();
    }

    @OnClick({2131361893})
    public void backArrowPressed() {
        finish();
    }

    public void onSuccess(PropertyDetailResponse propertyDetailResponse) {
        if (propertyDetailResponse != null) {
            this.propertyDetailResponse = propertyDetailResponse;
            this.dataParseStep1 = new Bundle();
            this.dataParseStep1.putParcelable("searchProperty", propertyDetailResponse.getPropertyInfo());
            this.dataParseStep1.putParcelable("fullInfo", propertyDetailResponse.getFullInfo());
            this.dataParseStep1.putParcelable("steps", propertyDetailResponse.getSteps());
            this.dataParseStep1.putParcelable("pricing", propertyDetailResponse.getPricing());
            validateSteps(propertyDetailResponse.getSteps());
        }
    }

    private void validateSteps(Steps steps) {
        this.mainStep = steps.getStep();
        this.subStep = steps.getSubstep();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.mainStep);
        Log.d("mainStep", stringBuilder.toString());
        if (this.mainStep.equalsIgnoreCase("finished") != null) {
            this.unPublishButton.setVisibility(0);
            if (this.checkPublishOrUnpublish.booleanValue() != null) {
                this.unPublishButton.setText(getString(R.string.addproperty_reviewPublishProperty));
                this.unPublishButton.setTextColor(getResources().getColor(R.color.bookings_approved));
            } else {
                this.unPublishButton.setText(getString(R.string.addYourProperty_unpublish));
                this.unPublishButton.setTextColor(getResources().getColor(R.color.bookings_rejected));
                this.unPublishButton.setIconResource(getResources().getDrawable(R.drawable.ic_unpublish));
            }
        }
        if (this.mainStep.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) != null) {
            if (Integer.parseInt(this.subStep) > 2) {
                this.step_two.setEnabled(true);
                this.step_three.setEnabled(false);
                this.StepTwoTxV.setTextColor(getResources().getColor(R.color.property_step_color));
                this.stepTwoDisc.setTextColor(getResources().getColor(R.color.property_step_color));
                this.stepTwoImageview.setImageResource(R.drawable.ic_arrow_right);
                this.StepOneTxV.setTextColor(getResources().getColor(R.color.other_room));
            }
        } else if (this.mainStep.equals(ExifInterface.GPS_MEASUREMENT_2D) != null) {
            if (Integer.parseInt(this.subStep) > 3) {
                this.step_two.setEnabled(true);
                this.StepTwoTxV.setTextColor(getResources().getColor(R.color.property_step_color));
                this.stepTwoDisc.setTextColor(getResources().getColor(R.color.property_step_color));
                this.StepOneTxV.setTextColor(getResources().getColor(R.color.other_room));
                this.step_three.setEnabled(true);
                this.StepThreeTxV.setTextColor(getResources().getColor(R.color.property_step_color));
                this.stepThreeDisc.setTextColor(getResources().getColor(R.color.property_step_color));
                this.StepTwoTxV.setTextColor(getResources().getColor(R.color.other_room));
                this.stepTwoImageview.setImageResource(R.drawable.ic_arrow_right);
                this.stepThreeImageview.setImageResource(R.drawable.ic_arrow_right);
                return;
            }
            this.step_two.setEnabled(true);
            this.step_three.setEnabled(false);
            this.StepTwoTxV.setTextColor(getResources().getColor(R.color.property_step_color));
            this.stepTwoDisc.setTextColor(getResources().getColor(R.color.property_step_color));
            this.StepOneTxV.setTextColor(getResources().getColor(R.color.other_room));
            this.stepTwoImageview.setImageResource(R.drawable.ic_arrow_right);
        } else if (!(this.mainStep.equals(ExifInterface.GPS_MEASUREMENT_3D) == null && this.mainStep.equalsIgnoreCase("finished") == null)) {
            if (this.subStep.equalsIgnoreCase("finished") != null) {
                this.StepThreeTxV.setTextColor(getResources().getColor(R.color.other_room));
            } else {
                this.StepThreeTxV.setTextColor(getResources().getColor(R.color.property_step_color));
            }
            this.step_two.setEnabled(true);
            this.stepTwoDisc.setTextColor(getResources().getColor(R.color.property_step_color));
            this.StepOneTxV.setTextColor(getResources().getColor(R.color.other_room));
            this.step_three.setEnabled(true);
            this.stepThreeDisc.setTextColor(getResources().getColor(R.color.property_step_color));
            this.StepTwoTxV.setTextColor(getResources().getColor(R.color.other_room));
            this.stepTwoImageview.setImageResource(R.drawable.ic_arrow_right);
            this.stepThreeImageview.setImageResource(R.drawable.ic_arrow_right);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.fromStepsPropertyId != null) {
            PropertyDetailRequest propertyDetailRequest = new PropertyDetailRequest();
            propertyDetailRequest.setPropertyId(this.fromStepsPropertyId);
            this.propertyDetailPresenter.getPropertyDetailData(getString(R.string.please_wait), propertyDetailRequest, true);
        }
    }

    public void onSuccessResponse(PublishPropertyResponse publishPropertyResponse) {
        showToast(getString(R.string.addYourProperty_unpublish_success));
        publishPropertyResponse = new Intent(this, DashBoardActivity.class);
        publishPropertyResponse.setFlags(67108864);
        publishPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
        startActivity(publishPropertyResponse);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onError(APIError aPIError, int i) {
        if (aPIError == null || aPIError.getSeken_errors() == 0) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onDetachedFromWindow() {
        if (this.propertyDetailPresenter != null) {
            this.propertyDetailPresenter.unSubscribeDisposables();
        }
        if (this.publishPropertyPresenter != null) {
            this.publishPropertyPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
