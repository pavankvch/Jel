package com.jelsat.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.businesslogic.AddYourProperty.AddPropertyImagePresenter;
import com.businesslogic.AddYourProperty.DeletePropertyImagePresenter;
import com.businesslogic.AddYourProperty.IAddPropertyImageView;
import com.businesslogic.AddYourProperty.IDeletePropertyImageView;
import com.businesslogic.framework.ErrorCodes;
import com.data.addproperty.DeleteImagerequest;
import com.data.addproperty.FormData;
import com.data.addproperty.ImageUploadResponse;
import com.data.propertydetail.PropertyImage;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.AddPropertyOtherImage;
import com.jelsat.R;
import com.jelsat.adapters.OtherImagesAdapter;
import com.jelsat.adapters.OtherImagesAdapter$OnListItemClickListener;
import com.jelsat.customclasses.ItemOffsetDecoration;
import com.jelsat.events.ImageUploadResponseEvent;
import com.jelsat.events.ImageUploadingArraylistEvent;
import com.jelsat.events.SendImageToActivity;
import com.jelsat.utils.AlertDialogUtil;
import com.jelsat.utils.DialogUtil;
import com.jelsat.utils.GlideApp;
import com.jelsat.widgets.FancyButton;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AddingImagesActivity extends BaseImageActivity implements IAddPropertyImageView, IDeletePropertyImageView, OtherImagesAdapter$OnListItemClickListener {
    private OtherImagesAdapter adapter;
    private AddPropertyImagePresenter addPropertyImagePresenter = new AddPropertyImagePresenter(this, RetrofitClient.getAPIServiceForMultiPart());
    private AlertDialogUtil alertDialog;
    private boolean checkImageType;
    private DeletePropertyImagePresenter deletePropertyImagePresenter = new DeletePropertyImagePresenter(this, RetrofitClient.getAPIService());
    private DialogUtil dialogUtil;
    @BindView(2131362166)
    ImageView featuredImageIv;
    @BindView(2131362442)
    ProgressBar horizonatalProgressbar;
    @BindView(2131362242)
    TextView imageCount;
    private boolean isFeatureUploaded;
    private List<AddPropertyOtherImage> otherImageList;
    @BindView(2131362388)
    LinearLayout otherImagesLayout;
    @BindView(2131362498)
    RecyclerView recyclerView;
    private SearchProperty searchProperty;
    private int totalUploadImageCount = 0;
    @BindView(2131362804)
    FancyButton uploadButton;
    private int uploadingImageCount = 1;

    public int getActivityLayout() {
        return R.layout.add_images;
    }

    public void onFeatureimageUploaded(ImageUploadResponse imageUploadResponse) {
    }

    @OnClick({2131362166})
    public void selectImageForUpload() {
        this.checkImageType = true;
        selectImage(false, 1);
    }

    @OnClick({2131362388})
    public void selectotherImageForUpload() {
        this.checkImageType = false;
        selectImage(false, 1);
    }

    @OnClick({2131362015})
    public void clickOnClose(View view) {
        goToPreviousScreen();
    }

    @OnClick({2131362804})
    public void clickOnUploadButton(View view) {
        if (this.otherImageList.size() >= 4) {
            if (isAllImagesUploaded() != null) {
                goToPreviousScreen();
                return;
            }
            if (isNetworkConnected() == null) {
                this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
            } else if (this.otherImageList.size() > null) {
                view = null;
                while (view < this.otherImageList.size()) {
                    if (((AddPropertyOtherImage) this.otherImageList.get(view)).isUploaded()) {
                        view++;
                    } else {
                        showImageCountAndProgressBar(true);
                        FormData formData = new FormData();
                        formData.setPropertyId(this.searchProperty.getPropertyId());
                        formData.setType("Others");
                        formData.setTittle(((AddPropertyOtherImage) this.otherImageList.get(view)).getTitle());
                        this.addPropertyImagePresenter.addImageToServer(((AddPropertyOtherImage) this.otherImageList.get(view)).getFile(), formData, "Others", view);
                        return;
                    }
                }
            }
        }
    }

    private boolean isAllImagesUploaded() {
        for (int i = 0; i < this.otherImageList.size(); i++) {
            if (!((AddPropertyOtherImage) this.otherImageList.get(i)).isUploaded()) {
                return false;
            }
        }
        return true;
    }

    private void goToPreviousScreen() {
        Intent intent = new Intent();
        int i = 0;
        for (int i2 = 0; i2 < this.otherImageList.size(); i2++) {
            if (((AddPropertyOtherImage) this.otherImageList.get(i2)).isUploaded()) {
                i++;
            }
        }
        if (this.isFeatureUploaded) {
            i++;
        }
        intent.putExtra("noOfImages", i);
        setResult(102, intent);
        finish();
    }

    private void showImageCountAndProgressBar(boolean z) {
        int i = 8;
        this.imageCount.setVisibility(z ? 0 : 8);
        ProgressBar progressBar = this.horizonatalProgressbar;
        if (z) {
            i = 0;
        }
        progressBar.setVisibility(i);
        this.imageCount.setText(String.format("%s/%s", new Object[]{Integer.valueOf(this.uploadingImageCount), Integer.valueOf(this.totalUploadImageCount)}));
        this.horizonatalProgressbar.setMax(this.totalUploadImageCount);
        this.horizonatalProgressbar.setProgress(this.uploadingImageCount);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.horizonatalProgressbar.setProgress(0);
        this.dialogUtil = new DialogUtil(this);
        this.alertDialog = new AlertDialogUtil(this);
        this.otherImageList = new ArrayList();
        if (getIntent().getExtras() != null) {
            this.searchProperty = (SearchProperty) getIntent().getParcelableExtra("searchProperty");
        }
        this.otherImagesLayout.setEnabled(false);
        if (this.searchProperty.getImagesList() != null && this.searchProperty.getImagesList().size() > null) {
            this.otherImagesLayout.setEnabled(true);
            this.isFeatureUploaded = true;
            GlideApp.with(this).asBitmap().load(((PropertyImage) this.searchProperty.getImagesList().get(0)).getPropertyImage()).placeholder(R.drawable.ic_add_image).apply(new RequestOptions().transform(new RoundedCorners(8))).error(R.drawable.ic_add_image).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.featuredImageIv);
            for (bundle = true; bundle < this.searchProperty.getImagesList().size(); bundle++) {
                AddPropertyOtherImage addPropertyOtherImage = new AddPropertyOtherImage();
                addPropertyOtherImage.setUploaded(true);
                addPropertyOtherImage.setUrl(((PropertyImage) this.searchProperty.getImagesList().get(bundle)).getPropertyImage());
                addPropertyOtherImage.setId(((PropertyImage) this.searchProperty.getImagesList().get(bundle)).getFileId());
                addPropertyOtherImage.setTitle(((PropertyImage) this.searchProperty.getImagesList().get(bundle)).getTitle());
                this.otherImageList.add(addPropertyOtherImage);
            }
            if (this.searchProperty.getImagesList().size() > 4) {
                this.uploadButton.setText(getString(R.string.next));
                this.uploadButton.setClickable(true);
                this.uploadButton.setEnabled(true);
                this.uploadButton.setFocusable(true);
            } else {
                this.uploadButton.setText(getString(R.string.upload_image));
                this.uploadButton.setClickable(false);
                this.uploadButton.setEnabled(false);
                this.uploadButton.setFocusable(false);
            }
        }
        initCitiesRecyclerView(this.otherImageList);
    }

    private void initCitiesRecyclerView(List<AddPropertyOtherImage> list) {
        LayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        this.recyclerView.addItemDecoration(new ItemOffsetDecoration(this, R.dimen.item_diverder));
        this.recyclerView.setLayoutManager(gridLayoutManager);
        this.recyclerView.setNestedScrollingEnabled(false);
        this.adapter = new OtherImagesAdapter(this, list, this);
        this.recyclerView.setAdapter(this.adapter);
    }

    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    public void setProfileImage(Bitmap bitmap, File file, int i) {
        if (bitmap != null) {
            EventBus.getDefault().postSticky(new SendImageToActivity(bitmap, file, this.checkImageType != 0 ? "Featured" : "Others", this.searchProperty.getPropertyId()));
            goToActivity(ImagePreviewActivity.class);
        }
    }

    public void onOtherImageUploaded(ImageUploadResponse imageUploadResponse, int i) {
        if (imageUploadResponse != null) {
            ((AddPropertyOtherImage) this.otherImageList.get(i)).setUploaded(true);
            ((AddPropertyOtherImage) this.otherImageList.get(i)).setUrl(imageUploadResponse.getUrl());
            ((AddPropertyOtherImage) this.otherImageList.get(i)).setId(imageUploadResponse.getId());
            this.adapter.updateItem(imageUploadResponse, i);
            uploadImage(i + 1);
        }
    }

    private void uploadImage(int i) {
        if (isAllImagesUploaded()) {
            goToPreviousScreen();
            return;
        }
        if (!isNetworkConnected()) {
            this.dialogUtil.showOkDialog(getResources().getString(R.string.error_message_network));
        } else if (this.otherImageList.size() > 0) {
            while (i < this.otherImageList.size()) {
                if (((AddPropertyOtherImage) this.otherImageList.get(i)).isUploaded()) {
                    i++;
                } else {
                    this.uploadingImageCount++;
                    showImageCountAndProgressBar(true);
                    FormData formData = new FormData();
                    formData.setPropertyId(this.searchProperty.getPropertyId());
                    formData.setType("Others");
                    formData.setTittle(((AddPropertyOtherImage) this.otherImageList.get(i)).getTitle());
                    this.addPropertyImagePresenter.addImageToServer(((AddPropertyOtherImage) this.otherImageList.get(i)).getFile(), formData, "Others", i);
                    return;
                }
            }
        }
    }

    public void clickOnCloseImage(AddPropertyOtherImage addPropertyOtherImage, int i) {
        if (addPropertyOtherImage.isUploaded()) {
            DeleteImagerequest deleteImagerequest = new DeleteImagerequest();
            deleteImagerequest.setPropertyId(this.searchProperty.getPropertyId());
            deleteImagerequest.setFeatured(Boolean.valueOf(false));
            deleteImagerequest.setFid(addPropertyOtherImage.getId());
            this.deletePropertyImagePresenter.deletePropertyImage(getString(R.string.deleting_image), deleteImagerequest, i);
            return;
        }
        this.otherImageList.remove(i);
        this.adapter.setData(this.otherImageList);
        this.totalUploadImageCount--;
        updateButton();
    }

    private void updateButton() {
        this.uploadButton.setText(getString(R.string.upload_image));
        if (this.otherImageList.size() > 3) {
            this.uploadButton.setClickable(true);
            this.uploadButton.setEnabled(true);
            this.uploadButton.setFocusable(true);
            return;
        }
        this.uploadButton.setClickable(false);
        this.uploadButton.setEnabled(false);
        this.uploadButton.setFocusable(false);
    }

    public void deleteSuccess(int i) {
        showToast(getString(R.string.feature_delete_success));
        this.otherImageList.remove(i);
        this.adapter.setData(this.otherImageList);
        updateButton();
        this.uploadingImageCount--;
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
        this.uploadingImageCount--;
        showImageCountAndProgressBar(null);
        showErrorDialog(i2);
    }

    private void showErrorDialog(int i) {
        this.alertDialog.showAlertDialog(getString(R.string.upload_image), String.format("%s %s", new Object[]{getString(R.string.uploading_image_failed), getString(R.string.are_you_sure_to_upload)}), getString(R.string.cancel_popup_yes), getString(R.string.cancel_popup_no), new AddingImagesActivity$1(this, i), new AddingImagesActivity$2(this));
    }

    public void errorOccurred(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onBackPressed() {
        goToPreviousScreen();
        super.onBackPressed();
    }

    public void onStop() {
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.addPropertyImagePresenter != null) {
            this.addPropertyImagePresenter.unSubscribeDisposables();
        }
        if (this.deletePropertyImagePresenter != null) {
            this.deletePropertyImagePresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onImageUploadingArray(ImageUploadingArraylistEvent imageUploadingArraylistEvent) {
        if (imageUploadingArraylistEvent != null) {
            AddPropertyOtherImage addPropertyOtherImage = new AddPropertyOtherImage();
            addPropertyOtherImage.setBitmap(imageUploadingArraylistEvent.getBitmap());
            addPropertyOtherImage.setFile(imageUploadingArraylistEvent.getImageUploadFile());
            addPropertyOtherImage.setTitle(imageUploadingArraylistEvent.getImageUploadTitle());
            addPropertyOtherImage.setUploaded(false);
            this.otherImageList.add(addPropertyOtherImage);
            this.adapter.setData(this.otherImageList);
            this.recyclerView.smoothScrollToPosition(this.otherImageList.size() - 1);
            updateButton();
            this.totalUploadImageCount++;
            EventBus.getDefault().removeStickyEvent(imageUploadingArraylistEvent);
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onDataPlotting(ImageUploadResponseEvent imageUploadResponseEvent) {
        if (imageUploadResponseEvent == null || imageUploadResponseEvent.getImageUploadResponse().getUrl() == null) {
            showToast(getString(R.string.general_api_error_msg));
            return;
        }
        this.otherImagesLayout.setEnabled(true);
        this.isFeatureUploaded = true;
        this.featuredImageIv.setImageBitmap(imageUploadResponseEvent.getBitmap());
        EventBus.getDefault().removeStickyEvent(imageUploadResponseEvent);
    }
}
