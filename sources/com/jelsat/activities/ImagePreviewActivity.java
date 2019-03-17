package com.jelsat.activities;

import android.graphics.Bitmap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.AddYourProperty.AddPropertyImagePresenter;
import com.businesslogic.AddYourProperty.IAddPropertyImageView;
import com.businesslogic.framework.ErrorCodes;
import com.data.addproperty.FormData;
import com.data.addproperty.ImageUploadResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.events.ImageUploadResponseEvent;
import com.jelsat.events.ImageUploadingArraylistEvent;
import com.jelsat.events.SendImageToActivity;
import java.io.File;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ImagePreviewActivity extends BaseAppCompactActivity implements IAddPropertyImageView {
    private AddPropertyImagePresenter addPropertyImagePresenter = new AddPropertyImagePresenter(this, RetrofitClient.getAPIServiceForMultiPart());
    @BindView(2131361849)
    TextView add_item;
    private Bitmap bitmap;
    private File imageFile;
    private String imageType;
    @BindView(2131362389)
    ImageView otherImageIv;
    @BindView(2131362390)
    EditText otherImageTitleEt;
    private String propertyId;

    public int getActivityLayout() {
        return R.layout.other_image_dialog;
    }

    public void onOtherImageUploaded(ImageUploadResponse imageUploadResponse, int i) {
    }

    @OnClick({2131362095})
    public void finishScreen() {
        finish();
    }

    @OnClick({2131361894})
    public void backfinishScreen() {
        finish();
    }

    @OnClick({2131361849})
    public void clickOnAdd() {
        String trim = this.otherImageTitleEt.getText().toString().trim();
        if (this.imageType.equalsIgnoreCase("Featured")) {
            FormData formData = new FormData();
            formData.setPropertyId(this.propertyId);
            formData.setTittle(trim);
            formData.setType(this.imageType);
            this.addPropertyImagePresenter.addImageToServer(this.imageFile, formData, this.imageType, 0);
            return;
        }
        EventBus.getDefault().postSticky(new ImageUploadingArraylistEvent(this.bitmap, this.imageFile, trim));
        finish();
    }

    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void onFeatureimageUploaded(ImageUploadResponse imageUploadResponse) {
        if (imageUploadResponse != null) {
            EventBus.getDefault().postSticky(new ImageUploadResponseEvent(imageUploadResponse, this.imageType, this.bitmap, this.imageFile));
            finish();
        }
    }

    public void onError(APIError aPIError, int i, int i2) {
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

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void prepareData(SendImageToActivity sendImageToActivity) {
        if (sendImageToActivity != null) {
            this.bitmap = sendImageToActivity.getBitmap();
            this.imageFile = sendImageToActivity.getFile();
            this.propertyId = sendImageToActivity.getPropertyId();
            this.imageType = sendImageToActivity.getImageType();
            this.otherImageIv.setImageBitmap(this.bitmap);
            EventBus.getDefault().removeStickyEvent(sendImageToActivity);
        }
    }

    protected void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.addPropertyImagePresenter != null) {
            this.addPropertyImagePresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
