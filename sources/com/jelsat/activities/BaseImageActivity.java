package com.jelsat.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.utils.BitmapUtils;
import com.jelsat.utils.Utils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView.CropShape;
import com.theartofdev.edmodo.cropper.CropImageView.Guidelines;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public abstract class BaseImageActivity extends BaseAppCompactActivity {
    private int deviceHeight;
    private int deviceWidth;
    private int imageCount;
    private boolean isFromEdit;
    private Uri mCropImageUri;

    public abstract void setProfileImage(Bitmap bitmap, File file, int i);

    public boolean isDeviceSupportCamera() {
        return getPackageManager().hasSystemFeature("android.hardware.camera");
    }

    public void selectImage(boolean z, int i) {
        this.isFromEdit = z;
        this.imageCount = i;
        this.deviceWidth = Utils.getScreenWidthInPixels(getApplicationContext());
        this.deviceHeight = Utils.getScreenHeightInPixels(getApplicationContext());
        if (CropImage.isExplicitCameraPermissionRequired(this)) {
            requestPermissionsSafely(true, new String[]{"android.permission.CAMERA"});
            return;
        }
        CropImage.startPickImageActivity(this);
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (i != CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (iArr.length > 0 && iArr[0] == 0) {
            CropImage.startPickImageActivity(this);
        } else if (iArr[0] == -1) {
            Log.i("xxx", "User interaction was cancelled.");
            if (VERSION.SDK_INT >= 23) {
                if (shouldShowRequestPermissionRationale("android.permission.CAMERA")) {
                    showLocationPermissionDeniedAlert(false);
                } else {
                    showLocationPermissionDeniedAlert(true);
                }
            }
        } else {
            showLocationPermissionDeniedAlert(true);
        }
        if (i != CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE) {
            super.onRequestPermissionsResult(i, strArr, iArr);
        } else if (this.mCropImageUri != 0 && iArr.length > 0 && iArr[0] == 0) {
            startCropImageActivity(this.mCropImageUri);
        } else if (iArr[0] == -1) {
            Log.i("xxx", "User interaction was cancelled.");
            if (VERSION.SDK_INT >= 23) {
                if (shouldShowRequestPermissionRationale("android.permission.READ_EXTERNAL_STORAGE") != 0) {
                    showLocationPermissionDeniedAlert(false);
                } else {
                    showLocationPermissionDeniedAlert(true);
                }
            }
        } else {
            showLocationPermissionDeniedAlert(true);
        }
    }

    private void showLocationPermissionDeniedAlert(boolean z) {
        hideKeyboard();
        if (z) {
            showPermissionDeniedDialogIfCheckNeverAskAgain(this, getString(true));
        } else {
            showToast(getString(true));
        }
    }

    private void startCropImageActivity(Uri uri) {
        CropImage.activity(uri).setGuidelines(Guidelines.ON).setMultiTouchEnabled(true).setFixAspectRatio(true).setAllowRotation(true).setAspectRatio(4, 3).setActivityTitle(getString(R.string.image_crop)).setCropShape(CropShape.RECTANGLE).start(this);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 200 && i2 == -1) {
            Uri pickImageResultUri = CropImage.getPickImageResultUri(this, intent);
            if (!isImageFile(pickImageResultUri)) {
                showToast(getString(R.string.select_image_file));
                return;
            } else if (CropImage.isReadExternalStoragePermissionsRequired(this, pickImageResultUri)) {
                this.mCropImageUri = pickImageResultUri;
                requestPermissionsSafely(CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE, "android.permission.READ_EXTERNAL_STORAGE");
            } else {
                startCropImageActivity(pickImageResultUri);
            }
        } else {
            Log.e("qqq", "crop failed");
        }
        if (i == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            i = CropImage.getActivityResult(intent);
            if (i2 == -1) {
                if (i != 0) {
                    i2 = BitmapUtils.decodeSampledBitmapFromPath(getPath(this, i.getUri()), this.deviceWidth / 2, this.deviceHeight / 4);
                    intent = new ByteArrayOutputStream();
                    i2.compress(CompressFormat.JPEG, 50, intent);
                    i2 = intent.toByteArray();
                    intent = BitmapFactory.decodeByteArray(i2, 0, i2.length);
                    File file = new File(getApplicationContext().getFilesDir(), i.getUri().getLastPathSegment());
                    try {
                        i = new FileOutputStream(file);
                        i.write(i2);
                        i.flush();
                        i.close();
                    } catch (int i3) {
                        Log.e(getClass().getSimpleName(), "Error writing bitmap", i3);
                    }
                    setProfileImage(intent, file, this.imageCount);
                }
            } else if (i2 == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                if (!(i3 == 0 || i3.getError() == 0)) {
                    i3.getError().printStackTrace();
                }
                showToast(getString(R.string.editprofile_cropping_failed));
            } else {
                Log.e("qqq", "crop failed");
                if (!(i3 == 0 || i3.getError() == 0)) {
                    i3.getError().printStackTrace();
                }
            }
        }
    }

    private boolean isImageFile(Uri uri) {
        if (uri.getScheme().equals(Param.CONTENT)) {
            uri = getApplicationContext().getContentResolver().getType(uri);
        } else {
            uri = MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(uri.toString()).toLowerCase());
        }
        Log.e("azx", uri);
        return (uri == null || uri.startsWith(MessengerShareContentUtility.MEDIA_IMAGE) == null) ? null : true;
    }

    private void showPermissionDeniedDialogIfCheckNeverAskAgain(Context context, String str) {
        context = new Builder(context).setTitle(R.string.settings_label).setIcon(R.mipmap.ic_app_icon).setMessage(str).setPositiveButton(17039370, new BaseImageActivity$1(this)).create();
        context.setCanceledOnTouchOutside(null);
        context.show();
    }

    @SuppressLint({"NewApi"})
    public static String getPath(Context context, Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : null) == null || !DocumentsContract.isDocumentUri(context, uri)) {
            if (Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
                return getDataColumn(context, uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme()) != null) {
                return uri.getPath();
            }
        } else if (isExternalStorageDocument(uri)) {
            context = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(context[0]) != null) {
                uri = new StringBuilder();
                uri.append(Environment.getExternalStorageDirectory());
                uri.append("/");
                uri.append(context[1]);
                return uri.toString();
            }
        } else if (isDownloadsDocument(uri)) {
            return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (isMediaDocument(uri)) {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if (MessengerShareContentUtility.MEDIA_IMAGE.equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            return getDataColumn(context, uri2, "_id=?", new String[]{uri[1]});
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        try {
            context = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (context != null) {
                try {
                    if (context.moveToFirst() != null) {
                        uri = context.getString(context.getColumnIndexOrThrow("_data"));
                        if (context != null) {
                            context.close();
                        }
                        return uri;
                    }
                } catch (Throwable th) {
                    uri = th;
                    if (context != null) {
                        context.close();
                    }
                    throw uri;
                }
            }
            if (context != null) {
                context.close();
            }
            return null;
        } catch (Throwable th2) {
            uri = th2;
            context = null;
            if (context != null) {
                context.close();
            }
            throw uri;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
