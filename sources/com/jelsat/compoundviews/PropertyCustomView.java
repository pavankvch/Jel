package com.jelsat.compoundviews;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.businesslogic.propertyfavourite.IPropertyFavouriteView;
import com.businesslogic.propertyfavourite.PropertyFavouritePresenter;
import com.data.propertyfavourite.PropertyFavouriteRequest;
import com.data.propertyfavourite.PropertyFavouriteResponse;
import com.data.retrofit.RetrofitClient;
import com.data.searchproperty.SearchProperty;
import com.data.utils.APIError;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.activities.HomeActivity;
import com.jelsat.payfort.PayFortPayment;
import com.jelsat.utils.GlideApp;
import com.jelsat.utils.NetworkUtil;
import com.jelsat.utils.ProgressDialogUtil;
import java.util.Locale;

public class PropertyCustomView extends LinearLayout implements IPropertyFavouriteView {
    private AppCompatActivity context;
    @BindView(2131362163)
    ImageView favoriteImageView;
    private Dialog progressDialog;
    private PropertyFavouritePresenter propertyFavouritePresenter;
    @BindView(2131362447)
    ImageView propertyImageView;
    @BindView(2131362477)
    TextView propertyNameTextView;
    @BindView(2131362493)
    TextView rateTextView;
    private ViewToAdapterListener viewToAdapterListener;

    public interface ViewToAdapterListener {
        void changeFavourite(PropertyFavouriteResponse propertyFavouriteResponse, int i);
    }

    public PropertyCustomView(Context context) {
        super(context);
    }

    public PropertyCustomView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.context = (AppCompatActivity) context;
        initLayout();
        initPresenters();
    }

    private void initPresenters() {
        this.propertyFavouritePresenter = new PropertyFavouritePresenter(this, RetrofitClient.getAPIService());
    }

    private void initLayout() {
        setOrientation(1);
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        if (layoutInflater != null) {
            layoutInflater.inflate(R.layout.merge_horizontal_recyclerview_list_item, this, true);
        }
        setLayoutParams(new LayoutParams(-2, -2));
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void setProperty(final SearchProperty searchProperty, String str, final int i) {
        GlideApp.with(this.context).load(searchProperty.getImage()).placeholder((int) R.drawable.default_logo_dashboard).apply(new RequestOptions().transform(new RoundedCorners(3))).error((int) R.drawable.default_logo_dashboard).diskCacheStrategy(DiskCacheStrategy.DATA).into(this.propertyImageView);
        searchProperty.setPropertyFavourite(searchProperty.getFavourite() == 1 ? true : null);
        this.favoriteImageView.setImageResource(searchProperty.isPropertyFavourite() ? R.drawable.ic_favorite_red_24dp : R.drawable.ic_favourite);
        if (!(searchProperty.getCurrencyCode() == null || searchProperty.getCurrencyCode().equalsIgnoreCase(PayFortPayment.CURRENCY_TYPE) == null)) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                this.rateTextView.setText(Html.fromHtml(String.format(Locale.getDefault(), "<big>%.2f</big> %s/ %s", new Object[]{Float.valueOf(searchProperty.getPrice()), this.context.getString(R.string.saudi_currency), this.context.getString(R.string.per_night_label)})));
            } else {
                this.rateTextView.setText(Html.fromHtml(String.format(Locale.getDefault(), "%s <big>%.2f</big>/ %s", new Object[]{this.context.getString(R.string.saudi_currency), Float.valueOf(searchProperty.getPrice()), this.context.getString(R.string.per_night_label)})));
            }
        }
        this.propertyNameTextView.setText(searchProperty.getName());
        this.favoriteImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PropertyCustomView.this.isUserLoggedIn() != null) {
                    view = new PropertyFavouriteRequest();
                    view.setPropertyId(searchProperty.getPropertyId());
                    view.setStatus(String.valueOf(searchProperty.getFavourite() == 0 ? 1 : 0));
                    PropertyCustomView.this.propertyFavouritePresenter.setPropertyFavouriteToUser(PropertyCustomView.this.context.getString(R.string.please_wait), view, i);
                    return;
                }
                PropertyCustomView.this.context.startActivity(new Intent(PropertyCustomView.this.context, HomeActivity.class));
            }
        });
    }

    public void showProgressDialog(String str) {
        this.progressDialog = ProgressDialogUtil.showLoading(this.context, str);
    }

    public void showLoading() {
        this.progressDialog = ProgressDialogUtil.showLoading(this.context);
    }

    public void dismissProgress() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.cancel();
        }
        this.progressDialog = null;
    }

    public void showToast(String str) {
        if (isUsable()) {
            Toast.makeText(this.context, str, 0).show();
        }
    }

    public boolean isNetworkConnected() {
        if (!NetworkUtil.isConnectedToNetwork(this.context)) {
            if (!NetworkUtil.isNetworkConnectedThroughWifi(this.context)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUsable() {
        return this.context != null;
    }

    public void onSuccess(PropertyFavouriteResponse propertyFavouriteResponse, int i) {
        this.viewToAdapterListener.changeFavourite(propertyFavouriteResponse, i);
        if (Integer.parseInt(propertyFavouriteResponse.getFavourite()) == 1) {
            showToast(getResources().getString(R.string.property_got_saved));
        } else {
            showToast(getResources().getString(R.string.property_got_unsaved));
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getResources().getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void setViewToAdapterListener(ViewToAdapterListener viewToAdapterListener) {
        this.viewToAdapterListener = viewToAdapterListener;
    }

    private boolean isUserLoggedIn() {
        return (PrefUtils.getInstance().getUserAccessToken() == null || PrefUtils.getInstance().getCookie() == null) ? false : true;
    }

    protected void onDetachedFromWindow() {
        if (this.propertyFavouritePresenter != null) {
            this.propertyFavouritePresenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
