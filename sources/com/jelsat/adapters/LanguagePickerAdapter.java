package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.spokenlanguages.Language;
import com.jelsat.R;
import java.util.List;

public class LanguagePickerAdapter extends Adapter<LanguagesViewHolders> {
    private Context context;
    private OnListItemClickListener itemClickListener;
    private List<Language> languagesList;

    public interface OnListItemClickListener {
        void clickOnListItem(Language language, int i, boolean z);
    }

    public class LanguagesViewHolders extends ViewHolder {
        View itemView;
        @BindView(2131361991)
        CheckedTextView languageCheckBox;

        public LanguagesViewHolders(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class LanguagesViewHolders_ViewBinding implements Unbinder {
        private LanguagesViewHolders target;

        @UiThread
        public LanguagesViewHolders_ViewBinding(LanguagesViewHolders languagesViewHolders, View view) {
            this.target = languagesViewHolders;
            languagesViewHolders.languageCheckBox = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.check_language, "field 'languageCheckBox'", CheckedTextView.class);
        }

        @CallSuper
        public void unbind() {
            LanguagesViewHolders languagesViewHolders = this.target;
            if (languagesViewHolders == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            languagesViewHolders.languageCheckBox = null;
        }
    }

    public LanguagePickerAdapter(Context context, OnListItemClickListener onListItemClickListener, List<Language> list) {
        this.context = context;
        this.itemClickListener = onListItemClickListener;
        this.languagesList = list;
    }

    public LanguagesViewHolders onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new LanguagesViewHolders(LayoutInflater.from(this.context).inflate(R.layout.language_picker_list_item, viewGroup, false));
    }

    public void onBindViewHolder(LanguagesViewHolders languagesViewHolders, final int i) {
        final Language language = (Language) this.languagesList.get(i);
        languagesViewHolders.languageCheckBox.setText(language.getLanguage());
        languagesViewHolders.languageCheckBox.setChecked(language.isSelected());
        languagesViewHolders.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                LanguagePickerAdapter.this.itemClickListener.clickOnListItem(language, i, language.isSelected() ^ 1);
            }
        });
    }

    public int getItemCount() {
        return this.languagesList != null ? this.languagesList.size() : 0;
    }

    public void setItemStatusChanged(int i, boolean z) {
        ((Language) this.languagesList.get(i)).setSelected(z);
        notifyItemChanged(i);
    }

    public void setData(List<Language> list) {
        this.languagesList = list;
        notifyDataSetChanged();
    }
}
