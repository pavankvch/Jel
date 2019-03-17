package com.jelsat.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.businesslogic.wallet.IWalletView;
import com.businesslogic.wallet.WalletPresenter;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.data.wallet.DeleteSavedCardRequest;
import com.data.wallet.WalletResponse;
import com.jelsat.R;
import com.jelsat.adapters.WalletSavedCardsAdapter;
import com.jelsat.adapters.WalletSavedCardsAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.widgets.FancyButton;
import java.util.Locale;

public class WalletActivity extends BaseAppCompactActivity implements IWalletView, OnListItemClickListener {
    private int position;
    private WalletPresenter presenter = new WalletPresenter(this, RetrofitClient.getAPIService());
    @BindView(2131362752)
    public TextView promoAmountTv;
    @BindView(2131361975)
    public RecyclerView recyclerView;
    @BindView(2131361938)
    public FancyButton refundButton;
    @BindView(2131362766)
    public TextView refundamountTv;
    @BindView(2131362636)
    SwipeRefreshLayout swipeContainer;
    @BindView(2131362781)
    public TextView totalBalanceTv;
    private WalletSavedCardsAdapter walletSavedCardsAdapter;

    public int getActivityLayout() {
        return R.layout.activity_wallet;
    }

    @OnClick({2131361892})
    public void clickOnBack() {
        finish();
    }

    @OnClick({2131361938})
    public void clickOnRefund() {
        this.presenter.refundAmount();
    }

    @OnClick({2131361937})
    public void clickOnPaymentHistory() {
        goToActivity(PaymentHistoryActivity.class);
    }

    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initSwipeToRefresh();
        this.walletSavedCardsAdapter = new WalletSavedCardsAdapter(null, this, this);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.walletSavedCardsAdapter);
        this.presenter.getWalletData(getString(R.string.please_wait), true);
    }

    private void initSwipeToRefresh() {
        this.swipeContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                WalletActivity.this.presenter.getWalletData(WalletActivity.this.getString(R.string.please_wait), false);
            }
        });
        this.swipeContainer.setColorSchemeResources(new int[]{17170459, 17170452, 17170456, 17170454});
    }

    public void showSwipeToRefresh(boolean z) {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(z);
        }
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onSuccess(WalletResponse walletResponse) {
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            this.promoAmountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(walletResponse.getOfferMoney()), getString(R.string.wallet_priceType)}));
            this.refundamountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(walletResponse.getRefundMoney()), getString(R.string.wallet_priceType)}));
            this.totalBalanceTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Float.valueOf(walletResponse.getTotal()), getString(R.string.wallet_priceType)}));
        } else {
            this.promoAmountTv.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{getString(R.string.wallet_priceType), Float.valueOf(walletResponse.getOfferMoney())}));
            this.refundamountTv.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{getString(R.string.wallet_priceType), Float.valueOf(walletResponse.getRefundMoney())}));
            this.totalBalanceTv.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{getString(R.string.wallet_priceType), Float.valueOf(walletResponse.getTotal())}));
        }
        if (walletResponse.getRefundMoney() > 0.0f) {
            this.refundButton.setVisibility(0);
        } else {
            this.refundButton.setVisibility(8);
        }
        this.walletSavedCardsAdapter.setCards(walletResponse.getCards());
    }

    public void onRefundSuccess() {
        showToast(getString(R.string.cancel_refund_message));
        if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            this.refundamountTv.setText(String.format(Locale.getDefault(), "%.2f %s", new Object[]{Double.valueOf(0.0d), getString(R.string.wallet_priceType)}));
        } else {
            this.refundamountTv.setText(String.format(Locale.getDefault(), "%s %.2f", new Object[]{getString(R.string.wallet_priceType), Double.valueOf(0.0d)}));
        }
        this.refundButton.setVisibility(8);
    }

    public void onCardDeleted() {
        showToast(getString(R.string.wallet_card_deleted));
        this.walletSavedCardsAdapter.notifyCardRemoved(this.position);
    }

    public void clickOnListItem(int i, String str) {
        this.position = i;
        i = new DeleteSavedCardRequest();
        i.setToken(str);
        str = new Builder(this);
        str.setTitle(getString(R.string.addYourProperty_confirmation));
        str.setMessage(getString(R.string.are_you_sure_want_to_delete_saved_card));
        str.setCancelable(false);
        str.setPositiveButton(getString(R.string.addYourProperty_ok), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                WalletActivity.this.presenter.deleteCard(i, WalletActivity.this.getString(R.string.please_wait));
            }
        });
        str.setNegativeButton(getString(17039360), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        str.show();
    }

    protected void onStop() {
        if (this.swipeContainer != null) {
            this.swipeContainer.setRefreshing(false);
        }
        super.onStop();
    }

    public void onDetachedFromWindow() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetachedFromWindow();
    }
}
