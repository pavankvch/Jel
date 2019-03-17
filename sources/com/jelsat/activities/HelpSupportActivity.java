package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.support.ISupportView;
import com.businesslogic.support.SupportPresenter;
import com.data.retrofit.RetrofitClient;
import com.data.support.SupportRequest;
import com.data.utils.APIError;
import com.google.common.net.HttpHeaders;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import com.jelsat.utils.DialogUtil;

public class HelpSupportActivity extends BaseAppCompactActivity implements ISupportView {
    @BindView(2131362011)
    TextView clickSupportMail;
    private DialogUtil dialogUtil;
    @BindView(2131362733)
    TextView headingTv;
    @BindView(2131362143)
    EditText messageEt;
    private SupportPresenter presenter = new SupportPresenter(this, RetrofitClient.getAPIService());
    private String userType;

    public int getActivityLayout() {
        return R.layout.activity_help_support;
    }

    @OnClick({2131362011})
    public void supportMail() {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.support_email_link)});
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "Send mail"));
    }

    @OnClick({2131361892})
    public void goBack() {
        finish();
    }

    @OnClick({2131361940})
    public void submitmessage() {
        if (this.messageEt.getText().toString().trim().length() > 0) {
            SupportRequest supportRequest = new SupportRequest();
            supportRequest.setMessage(this.messageEt.getText().toString().trim());
            supportRequest.setUserRole(this.userType);
            this.presenter.support(getString(R.string.please_wait), supportRequest);
            return;
        }
        showToast(getResources().getString(R.string.support_toast));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.messageEt.setImeOptions(6);
        this.messageEt.setRawInputType(1);
        if (getIntent().getBooleanExtra(StringConstants.USER_TYPE, false) != null) {
            this.userType = HttpHeaders.HOST;
        } else {
            this.userType = "Guest";
        }
        this.headingTv.setText(getResources().getString(R.string.help_support));
        this.dialogUtil = new DialogUtil(this);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess() {
        this.messageEt.setText("");
        this.dialogUtil.showOkDialog(getResources().getString(R.string.our_backend_team_approaches_you));
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
