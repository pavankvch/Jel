package com.jelsat.activities;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.utils.PrefUtils;
import com.jelsat.R;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.widgets.FancyButton;
import org.bouncycastle.i18n.TextBundle;

public class InviteActivity extends BaseAppCompactActivity {
    @BindView(2131362711)
    FancyButton codeTextView;
    @BindView(2131362192)
    TextView getRefferalAmount;
    @BindView(2131362500)
    LinearLayout referalBalanaceContentLayout;
    String refferalAmount;
    @BindView(2131362842)
    TextView withoutRefferalAmountDesc;

    public int getActivityLayout() {
        return R.layout.activity_invite;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131362299})
    public void clickOnLink() {
        showAlertDialog();
    }

    @OnClick({2131362711})
    public void clickToCopy() {
        if (!this.codeTextView.getText().toString().trim().equalsIgnoreCase("")) {
            ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(TextBundle.TEXT_ENTRY, this.codeTextView.getText().toString()));
            Toast.makeText(this, getString(R.string.promocode_copied), 0).show();
        }
    }

    @OnClick({2131362263})
    public void clickOnShareProperty() {
        String format = String.format("%s %s. %s", new Object[]{getString(R.string.promotional_code), this.codeTextView.getText().toString().trim(), getString(R.string.share_data)});
        String shareUrl = PrefUtils.getInstance().getSeedResponse().getShareUrl();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.share_promocode));
        intent.putExtra("android.intent.extra.TEXT", String.format("%s - %s", new Object[]{format, shareUrl}));
        startActivity(Intent.createChooser(intent, getString(R.string.share_via)));
    }

    public void SharingToSocialMedia(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.setType("text/plain");
        if (checkAppInstall(str)) {
            intent.setPackage(str);
            str = String.format("%s %s. %s", new Object[]{getString(R.string.promotional_code), this.codeTextView.getText().toString().trim(), getString(R.string.share_data)});
            String shareUrl = PrefUtils.getInstance().getSeedResponse().getShareUrl();
            intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.share_promocode));
            intent.putExtra("android.intent.extra.TEXT", String.format("%s - %s", new Object[]{str, shareUrl}));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
                return;
            } else {
                Toast.makeText(getApplicationContext(), "Install application first", 1).show();
                return;
            }
        }
        Toast.makeText(getApplicationContext(), "Install application first", 1).show();
    }

    private boolean checkAppInstall(java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
*/
        /*
        r2 = this;
        r0 = r2.getPackageManager();
        if (r0 == 0) goto L_0x000b;
    L_0x0006:
        r1 = 1;
        r0.getPackageInfo(r3, r1);	 Catch:{ NameNotFoundException -> 0x000b }
        return r1;
    L_0x000b:
        r3 = 0;
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jelsat.activities.InviteActivity.checkAppInstall(java.lang.String):boolean");
    }

    @OnClick({2131362266})
    public void clickOnwhatsappIV() {
        SharingToSocialMedia("com.whatsapp");
    }

    @OnClick({2131362264})
    public void clickOnFacebookIV() {
        SharingToSocialMedia("com.facebook.katana");
    }

    @OnClick({2131362265})
    public void clickOnGmailIV() {
        SharingToSocialMedia("com.google.android.gm");
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.codeTextView.setText(PrefUtils.getInstance().getUser().getReferralCode());
        this.refferalAmount = PrefUtils.getInstance().getSeedResponse().getRefferalAmount();
        if (Integer.parseInt(this.refferalAmount) > null) {
            this.referalBalanaceContentLayout.setVisibility(0);
            this.withoutRefferalAmountDesc.setVisibility(8);
            this.getRefferalAmount.setText(String.format("%s %s %s", new Object[]{getString(R.string.get_string), this.refferalAmount, getString(R.string.invite_desc2)}));
            return;
        }
        this.referalBalanaceContentLayout.setVisibility(8);
        this.withoutRefferalAmountDesc.setVisibility(0);
    }

    private void showAlertDialog() {
        AlertDialog show;
        if (Integer.parseInt(PrefUtils.getInstance().getSeedResponse().getRefferedAmount()) > 0) {
            Builder cancelable = new Builder(this).setCancelable(false);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.format("%s %s %s", new Object[]{getString(R.string.invite_every_time_new_jelsat), this.refferalAmount, getString(R.string.sar_string)}));
            stringBuilder.append("\n\n");
            stringBuilder.append(String.format("%s %s %s", new Object[]{getString(R.string.invite_once_they_book), r0, getString(R.string.invite_sar_to_your_wallet)}));
            stringBuilder.append("\n\n");
            stringBuilder.append(getString(R.string.invite_how_it_works_text3));
            show = cancelable.setMessage(stringBuilder.toString()).setPositiveButton(getText(R.string.ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        } else {
            Builder cancelable2 = new Builder(this).setCancelable(false);
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(String.format("%s %s %s", new Object[]{getString(R.string.invite_every_time_new_jelsat), this.refferalAmount, getString(R.string.sar_string)}));
            stringBuilder2.append("\n\n");
            stringBuilder2.append(getString(R.string.invite_how_it_works_text3));
            show = cancelable2.setMessage(stringBuilder2.toString()).setPositiveButton(getText(R.string.ok), new OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.copyFrom(show.getWindow().getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        show.getWindow().setAttributes(layoutParams);
        show.setCanceledOnTouchOutside(false);
        TextView textView = (TextView) show.findViewById(16908299);
        textView.setTextSize(16.0f);
        textView.setTypeface(ResourcesCompat.getFont(this, R.font.sf_ui_display_regular));
        textView.setLineSpacing(1.0f, 1.5f);
    }
}
