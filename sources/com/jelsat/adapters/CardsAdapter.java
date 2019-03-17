package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.propertybook.Card;
import com.jelsat.R;
import com.jelsat.adapters.RecyclerViewSectionAdapter.GetObject;
import java.util.List;

public class CardsAdapter extends Adapter<ViewHolder> implements GetObject<Card> {
    private List<Card> cardList;
    private Context context;
    private CardsAdapter$OnListItemClickListener listItemClickListener;

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131361973)
        ImageView cardImage;
        @BindView(2131361974)
        TextView creditCardNumberTV;
        @BindView(2131362303)
        LinearLayout listItemLayout;
        @BindView(2131362491)
        RadioButton radioButton;
        @BindView(2131362697)
        FrameLayout transparentLayout;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.cardImage = (ImageView) Utils.findRequiredViewAsType(view, R.id.card_image, "field 'cardImage'", ImageView.class);
            viewHolder.creditCardNumberTV = (TextView) Utils.findRequiredViewAsType(view, R.id.card_number_TV, "field 'creditCardNumberTV'", TextView.class);
            viewHolder.radioButton = (RadioButton) Utils.findRequiredViewAsType(view, R.id.radio_button, "field 'radioButton'", RadioButton.class);
            viewHolder.transparentLayout = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.transparent_layout, "field 'transparentLayout'", FrameLayout.class);
            viewHolder.listItemLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.list_item_layout, "field 'listItemLayout'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            viewHolder.cardImage = null;
            viewHolder.creditCardNumberTV = null;
            viewHolder.radioButton = null;
            viewHolder.transparentLayout = null;
            viewHolder.listItemLayout = null;
        }
    }

    public CardsAdapter(Context context, List<Card> list, CardsAdapter$OnListItemClickListener cardsAdapter$OnListItemClickListener) {
        this.context = context;
        this.cardList = list;
        this.listItemClickListener = cardsAdapter$OnListItemClickListener;
        setHasStableIds(true);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(this.context).inflate(R.layout.card_list_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        final Card card = (Card) this.cardList.get(i);
        viewHolder.cardImage.setImageResource(card.getCardDrawable());
        viewHolder.creditCardNumberTV.setText(card.getCardNo());
        if (card.isClickable()) {
            viewHolder.radioButton.setChecked(card.getChecked());
        } else {
            viewHolder.radioButton.setChecked(false);
        }
        if (card.isClickable()) {
            viewHolder.listItemLayout.setClickable(true);
            viewHolder.listItemLayout.setEnabled(true);
            viewHolder.listItemLayout.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (card.isClickable() != null) {
                        CardsAdapter.this.listItemClickListener.clickOnListItem(card, i, card.getChecked() ^ 1);
                    }
                }
            });
            viewHolder.transparentLayout.setVisibility(8);
            return;
        }
        viewHolder.listItemLayout.setClickable(false);
        viewHolder.listItemLayout.setEnabled(false);
        viewHolder.transparentLayout.setVisibility(0);
    }

    public int getItemCount() {
        return this.cardList != null ? this.cardList.size() : 0;
    }

    public void setData(List<Card> list) {
        this.cardList = list;
        notifyDataSetChanged();
    }

    public Card getObject(int i) {
        return (this.cardList == null || this.cardList.size() <= 0) ? 0 : (Card) this.cardList.get(i);
    }

    public void setItemStatusChanged(int i, int i2, boolean z) {
        if (i == i2) {
            ((Card) this.cardList.get(i)).setChecked(z);
        } else {
            ((Card) this.cardList.get(i)).setChecked(false);
            ((Card) this.cardList.get(i2)).setChecked(1);
        }
        notifyDataSetChanged();
    }
}
