package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.media.ExifInterface;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddYourPropertyPresenter;
import com.businesslogic.AddYourProperty.IAddYourPropertyView;
import com.businesslogic.propertydetail.IPropertyDetailView;
import com.businesslogic.propertydetail.PropertyDetailPresenter;
import com.data.addproperty.AddPropertyRequest;
import com.data.addproperty.AddPropertyResponse;
import com.data.addproperty.Steps;
import com.data.bookings.BookingCancelResponse;
import com.data.propertydetail.FullInfo;
import com.data.propertydetail.PropertyDetailRequest;
import com.data.propertydetail.PropertyDetailResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.customclasses.CustomEditText;

public class AboutYourProperty extends BaseAppCompactActivity implements IAddYourPropertyView, IPropertyDetailView {
    private String PropertyId;
    @BindView(2131361822)
    EditText aboutProperty;
    private AddPropertyRequest addPropertyRequest;
    private AddYourPropertyPresenter addYourPropertyPresenter = new AddYourPropertyPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131361883)
    EditText areaOfPropertyEd;
    private String checkClick;
    private boolean checkIntent;
    private String editPropertyId;
    private Bundle extraBundle;
    private FullInfo fullInfo;
    private String mainStep;
    @BindView(2131362357)
    Button nextButton;
    private PropertyDetailPresenter propertyDetailPresenter = new PropertyDetailPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362470)
    CustomEditText propertyImage;
    @BindView(2131362476)
    EditText propertyName;
    private PropertyDetailResponse response;
    @BindView(2131362531)
    TextView saveAndExit;
    private SearchProperty searchProperty;
    private Steps steps;
    private String subStep;
    private final TextWatcher watcher = new TextWatcher() {
        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (AboutYourProperty.this.response == null || AboutYourProperty.this.response.getPropertyInfo().getImagesList().size() <= 4) {
                AboutYourProperty.this.updateVisibility(false);
            } else if (AboutYourProperty.this.propertyName.getText().toString().trim().length() <= 4 || AboutYourProperty.this.aboutProperty.getText().toString().trim().length() <= 49 || AboutYourProperty.this.propertyImage.getText().toString().trim().length() == null) {
                AboutYourProperty.this.updateVisibility(false);
            } else {
                AboutYourProperty.this.updateVisibility(1);
            }
        }
    };

    public int getActivityLayout() {
        return R.layout.about_your_property;
    }

    public void onSuccess(BookingCancelResponse bookingCancelResponse) {
    }

    public void showSwipeToRefresh(boolean z) {
    }

    @OnClick({2131361893})
    public void backbutton() {
        finish();
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.aboutProperty.setImeOptions(6);
        this.aboutProperty.setRawInputType(1);
        bundle = getIntent();
        this.extraBundle = bundle.getExtras();
        this.checkIntent = bundle.getExtras().getBoolean("checkIntent");
        this.PropertyId = this.extraBundle.getString("PropertyId");
        if (this.checkIntent != null) {
            this.fullInfo = (FullInfo) this.extraBundle.getParcelable("fullInfo");
            this.steps = (Steps) this.extraBundle.getParcelable("steps");
            this.mainStep = this.steps.getStep();
            this.subStep = this.steps.getSubstep();
            this.searchProperty = (SearchProperty) this.extraBundle.getParcelable("searchProperty");
            this.editPropertyId = this.extraBundle.getString("editPropertyId");
        }
        this.propertyName.addTextChangedListener(this.watcher);
        this.aboutProperty.addTextChangedListener(this.watcher);
        this.propertyImage.addTextChangedListener(this.watcher);
        if (isNetworkConnected() != null) {
            bundle = new PropertyDetailRequest();
            if (this.checkIntent) {
                bundle.setPropertyId(this.editPropertyId);
            } else {
                bundle.setPropertyId(this.PropertyId);
            }
            this.propertyDetailPresenter.getPropertyDetailData(getString(R.string.please_wait), bundle, true);
        } else {
            showToast(getString(R.string.internet_connection_label));
        }
        this.aboutProperty.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == R.id.about_property_ed) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((motionEvent.getAction() & 255) == 1) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }

    @OnClick({2131362470})
    public void propertimage() {
        if (this.response != null) {
            Intent intent = new Intent(this, AddingImagesActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelable("searchProperty", this.response.getPropertyInfo());
            if (this.checkIntent) {
                intent.putExtra("checkIntent", true);
            } else {
                intent.putExtra("checkIntent", false);
            }
            intent.putExtras(bundle);
            startActivityForResult(intent, 102);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 102 && intent != null) {
            if (isNetworkConnected() != 0) {
                i = new PropertyDetailRequest();
                if (this.checkIntent != 0) {
                    i.setPropertyId(this.editPropertyId);
                } else {
                    i.setPropertyId(this.PropertyId);
                }
                this.propertyDetailPresenter.getPropertyDetailData(getString(R.string.please_wait), i, true);
                return;
            }
            showToast(getString(R.string.internet_connection_label));
        }
    }

    @OnClick({2131362531})
    public void saveAndExit() {
        this.checkClick = "saveAndExit";
        this.addPropertyRequest = new AddPropertyRequest();
        if (!this.checkIntent) {
            this.addPropertyRequest.setPropertyid(this.PropertyId);
            this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
            this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
        } else if (this.mainStep.equalsIgnoreCase("finished")) {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
        } else {
            this.addPropertyRequest.setPropertyid(this.editPropertyId);
            if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) < 3) {
                this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
            }
        }
        this.addPropertyRequest.setPropertyName(this.propertyName.getText().toString());
        this.addPropertyRequest.setAboutProperty(this.aboutProperty.getText().toString());
        this.addPropertyRequest.setAreaInMeters(this.areaOfPropertyEd.getText().toString());
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    @OnClick({2131362357})
    public void aboutYourPropertyNext() {
        this.checkClick = "nextbutton";
        this.addPropertyRequest = new AddPropertyRequest();
        this.addPropertyRequest.setPropertyName(this.propertyName.getText().toString());
        this.addPropertyRequest.setAboutProperty(this.aboutProperty.getText().toString());
        if (this.areaOfPropertyEd.getText().toString().equalsIgnoreCase("")) {
            this.addPropertyRequest.setAreaInMeters(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else {
            this.addPropertyRequest.setAreaInMeters(this.areaOfPropertyEd.getText().toString());
        }
        if (this.checkIntent) {
            if (this.mainStep.equalsIgnoreCase("finished")) {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
            } else {
                this.addPropertyRequest.setPropertyid(this.editPropertyId);
                if (Integer.parseInt(this.mainStep) < 2 || Integer.parseInt(this.subStep) < 3) {
                    this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
                    this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
                }
            }
            if (this.propertyName.getText().toString().equalsIgnoreCase(this.searchProperty.getName()) && this.areaOfPropertyEd.getText().toString().equalsIgnoreCase(this.searchProperty.getAreaInMeters())) {
                if (this.aboutProperty.getText().toString().equalsIgnoreCase(Html.fromHtml(this.fullInfo.getAboutProperty()).toString())) {
                    Intent intent;
                    if (!PrefUtils.getInstance().getSkipAddressBollen().booleanValue()) {
                        intent = new Intent(this, SaveLocation.class);
                    } else if (this.fullInfo.getPropertyTypeId().equalsIgnoreCase("168")) {
                        intent = new Intent(this, SaveLocation.class);
                    } else {
                        intent = new Intent(this, AddPropertyLocated.class);
                        intent.putExtra("forSkippingAddress", PrefUtils.getInstance().getSkipAddressBollen());
                    }
                    this.extraBundle.putParcelable("searchProperty", this.searchProperty);
                    this.extraBundle.putParcelable("fullInfo", this.fullInfo);
                    this.extraBundle.putParcelable("steps", this.steps);
                    intent.putExtras(this.extraBundle);
                    intent.putExtra("checkIntent", true);
                    startActivity(intent);
                    return;
                }
            }
            this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
            return;
        }
        this.addPropertyRequest.setPropertyid(this.PropertyId);
        this.addPropertyRequest.setStep(ExifInterface.GPS_MEASUREMENT_2D);
        this.addPropertyRequest.setSubstep(ExifInterface.GPS_MEASUREMENT_3D);
        this.addYourPropertyPresenter.getAddPropertyResult(getString(R.string.please_wait), this.addPropertyRequest);
    }

    public void onSuccessResponse(AddPropertyResponse addPropertyResponse) {
        if (this.checkIntent != null) {
            if (this.checkClick.equals("nextbutton") != null) {
                this.searchProperty.setName(this.propertyName.getText().toString());
                this.searchProperty.setAreaInMeters(this.areaOfPropertyEd.getText().toString());
                this.fullInfo.setAboutProperty(Html.fromHtml(this.aboutProperty.getText().toString()).toString());
                addPropertyResponse = new Intent(this, SaveLocation.class);
                this.extraBundle.putParcelable("searchProperty", this.searchProperty);
                this.extraBundle.putParcelable("fullInfo", this.fullInfo);
                this.extraBundle.putParcelable("steps", this.steps);
                addPropertyResponse.putExtras(this.extraBundle);
                addPropertyResponse.putExtra("checkIntent", true);
                startActivity(addPropertyResponse);
            } else if (this.checkClick.equals("saveAndExit") != null) {
                addPropertyResponse = new Intent(this, DashBoardActivity.class);
                addPropertyResponse.addFlags(67108864);
                addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
                startActivity(addPropertyResponse);
            }
        } else if (this.checkClick.equals("nextbutton") != null) {
            addPropertyResponse = new Intent(this, SaveLocation.class);
            this.extraBundle.putParcelable("searchProperty", this.searchProperty);
            this.extraBundle.putParcelable("fullInfo", this.fullInfo);
            addPropertyResponse.putExtras(this.extraBundle);
            addPropertyResponse.putExtra("checkIntent", false);
            startActivity(addPropertyResponse);
        } else if (this.checkClick.equals("saveAndExit") != null) {
            addPropertyResponse = new Intent(this, DashBoardActivity.class);
            addPropertyResponse.addFlags(67108864);
            addPropertyResponse.putExtra(StringConstants.FROM_PUBLISH_PAGE, true);
            startActivity(addPropertyResponse);
        }
    }

    public void onSuccess(PropertyDetailResponse propertyDetailResponse) {
        if (propertyDetailResponse != null) {
            this.response = propertyDetailResponse;
            this.searchProperty = propertyDetailResponse.getPropertyInfo();
            this.PropertyId = this.searchProperty.getPropertyId();
            this.editPropertyId = this.searchProperty.getPropertyId();
            initPropertyData(propertyDetailResponse);
        }
    }

    private void initPropertyData(PropertyDetailResponse propertyDetailResponse) {
        if (!TextUtils.isEmpty(propertyDetailResponse.getPropertyInfo().getName())) {
            this.propertyName.setText(propertyDetailResponse.getPropertyInfo().getName());
        }
        if (propertyDetailResponse.getPropertyInfo().getImagesList().size() > 0) {
            this.propertyImage.setText(String.format("%s %s", new Object[]{Integer.valueOf(propertyDetailResponse.getPropertyInfo().getImagesList().size()), getString(R.string.description_images)}));
        }
        if (!TextUtils.isEmpty(propertyDetailResponse.getPropertyInfo().getAreaInMeters())) {
            this.areaOfPropertyEd.setText(propertyDetailResponse.getPropertyInfo().getAreaInMeters());
        }
        if (!TextUtils.isEmpty(propertyDetailResponse.getFullInfo().getAboutProperty())) {
            this.aboutProperty.setText(Html.fromHtml(propertyDetailResponse.getFullInfo().getAboutProperty()));
        }
        if (TextUtils.isEmpty(this.propertyName.getText().toString()) || propertyDetailResponse.getPropertyInfo().getImagesList().size() <= 4 || TextUtils.isEmpty(this.aboutProperty.getText().toString()) != null || this.aboutProperty.getText().toString().length() <= 49) {
            updateVisibility(false);
        } else {
            updateVisibility(true);
        }
    }

    private void updateVisibility(boolean z) {
        this.saveAndExit.setVisibility(z ? 0 : 4);
        this.nextButton.setClickable(z);
        this.nextButton.setEnabled(z);
        if (z) {
            this.nextButton.setBackgroundResource(R.drawable.button_selected_background);
        } else {
            this.nextButton.setBackgroundResource(R.drawable.button_backgound);
        }
    }

    public void onError(APIError aPIError, int i) {
        dismissProgress();
        if (aPIError == null || aPIError.getSeken_errors() == 0) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onError(APIError aPIError) {
        dismissProgress();
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onDetachedFromWindow() {
        if (this.addYourPropertyPresenter != null) {
            this.addYourPropertyPresenter.unSubscribeDisposables();
        }
        if (this.propertyDetailPresenter != null) {
            this.propertyDetailPresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
