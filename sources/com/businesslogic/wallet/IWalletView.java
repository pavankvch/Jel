package com.businesslogic.wallet;

import com.businesslogic.framework.IBaseView;
import com.data.utils.APIError;
import com.data.wallet.WalletResponse;

public interface IWalletView extends IBaseView {
    void onCardDeleted();

    void onError(APIError aPIError);

    void onRefundSuccess();

    void onSuccess(WalletResponse walletResponse);

    void showSwipeToRefresh(boolean z);
}
