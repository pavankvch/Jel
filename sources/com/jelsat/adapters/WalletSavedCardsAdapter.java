package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.propertybook.Card;
import com.jelsat.R;
import com.payfort.sdk.android.dependancies.commons.Constants.CREDIT_CARDS_TYPES;
import java.util.List;

public class WalletSavedCardsAdapter extends Adapter<WalletSavedCardsHolder> {
    private Context context;
    private OnListItemClickListener listItemClickListener;
    private List<Card> savedCardsList;

    public interface OnListItemClickListener {
        void clickOnListItem(int i, String str);
    }

    public class WalletSavedCardsHolder extends ViewHolder {
        @BindView(2131362315)
        CardView MainCardView;
        @BindView(2131361973)
        ImageView cardImage;
        @BindView(2131361974)
        TextView cardNumberTV;
        @BindView(2131362084)
        ImageView deleteImageView;

        public WalletSavedCardsHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class WalletSavedCardsHolder_ViewBinding implements Unbinder {
        private WalletSavedCardsHolder target;

        @UiThread
        public WalletSavedCardsHolder_ViewBinding(WalletSavedCardsHolder walletSavedCardsHolder, View view) {
            this.target = walletSavedCardsHolder;
            walletSavedCardsHolder.cardNumberTV = (TextView) Utils.findRequiredViewAsType(view, R.id.card_number_TV, "field 'cardNumberTV'", TextView.class);
            walletSavedCardsHolder.MainCardView = (CardView) Utils.findRequiredViewAsType(view, R.id.main_card_view, "field 'MainCardView'", CardView.class);
            walletSavedCardsHolder.cardImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.card_image, "field 'cardImage'", ImageView.class);
            walletSavedCardsHolder.deleteImageView = (ImageView) Utils.findRequiredViewAsType(view, R.id.delete, "field 'deleteImageView'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            WalletSavedCardsHolder walletSavedCardsHolder = this.target;
            if (walletSavedCardsHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            walletSavedCardsHolder.cardNumberTV = null;
            walletSavedCardsHolder.MainCardView = null;
            walletSavedCardsHolder.cardImage = null;
            walletSavedCardsHolder.deleteImageView = null;
        }
    }

    public void setCards(List<Card> list) {
        this.savedCardsList = list;
        notifyDataSetChanged();
    }

    public WalletSavedCardsAdapter(List<Card> list, Context context, OnListItemClickListener onListItemClickListener) {
        this.savedCardsList = list;
        this.context = context;
        this.listItemClickListener = onListItemClickListener;
    }

    public void notifyCardRemoved(int i) {
        this.savedCardsList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.savedCardsList.size());
    }

    public WalletSavedCardsHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new WalletSavedCardsHolder(LayoutInflater.from(this.context).inflate(R.layout.wallet_saved_card_list_item, viewGroup, false));
    }

    public void onBindViewHolder(WalletSavedCardsHolder walletSavedCardsHolder, final int i) {
        walletSavedCardsHolder.cardNumberTV.setText(((Card) this.savedCardsList.get(i)).getCardNo());
        String cardNo = ((Card) this.savedCardsList.get(i)).getCardNo();
        String cardType = ((Card) this.savedCardsList.get(i)).getCardType();
        if (cardType.equalsIgnoreCase(CREDIT_CARDS_TYPES.VISA)) {
            walletSavedCardsHolder.cardImage.setImageResource(R.drawable.ic_visa);
        } else {
            if (!cardNo.startsWith("34")) {
                if (!cardNo.startsWith("37")) {
                    if (cardType.equalsIgnoreCase(CREDIT_CARDS_TYPES.MASTERCARD)) {
                        walletSavedCardsHolder.cardImage.setImageResource(R.drawable.ic_mastercard);
                    } else if (cardType.equalsIgnoreCase(CREDIT_CARDS_TYPES.MADA)) {
                        walletSavedCardsHolder.cardImage.setImageResource(R.drawable.ic_mada_card);
                    }
                }
            }
            walletSavedCardsHolder.cardImage.setImageResource(R.drawable.ic_american_express);
        }
        walletSavedCardsHolder.deleteImageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                WalletSavedCardsAdapter.this.listItemClickListener.clickOnListItem(i, ((Card) WalletSavedCardsAdapter.this.savedCardsList.get(i)).getToken());
            }
        });
    }

    public int getItemCount() {
        return this.savedCardsList != null ? this.savedCardsList.size() : 0;
    }

    public void setData(List<Card> list) {
        this.savedCardsList = list;
        notifyDataSetChanged();
    }
}
