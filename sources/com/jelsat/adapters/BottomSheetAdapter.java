package com.jelsat.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.data.utils.BottomSheetData;
import com.jelsat.R;
import java.util.List;

public class BottomSheetAdapter extends Adapter<BottomSheetViewHolder> {
    private List<BottomSheetData> list;
    private BottomSheetListItemClickListener listener;

    public interface BottomSheetListItemClickListener {
        void clickOnItem(BottomSheetData bottomSheetData, int i);
    }

    public class BottomSheetViewHolder extends ViewHolder {
        @BindView(2131361924)
        TextView bottomSheetTextView;

        @OnClick({2131361924})
        public void clickOnTextView() {
            BottomSheetAdapter.this.listener.clickOnItem((BottomSheetData) BottomSheetAdapter.this.list.get(getAdapterPosition()), getAdapterPosition());
        }

        public BottomSheetViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class BottomSheetViewHolder_ViewBinding implements Unbinder {
        private BottomSheetViewHolder target;
        private View view2131361924;

        @UiThread
        public BottomSheetViewHolder_ViewBinding(final BottomSheetViewHolder bottomSheetViewHolder, View view) {
            this.target = bottomSheetViewHolder;
            view = Utils.findRequiredView(view, R.id.bottom_sheet_textView, "field 'bottomSheetTextView' and method 'clickOnTextView'");
            bottomSheetViewHolder.bottomSheetTextView = (TextView) Utils.castView(view, R.id.bottom_sheet_textView, "field 'bottomSheetTextView'", TextView.class);
            this.view2131361924 = view;
            view.setOnClickListener(new DebouncingOnClickListener() {
                public void doClick(View view) {
                    bottomSheetViewHolder.clickOnTextView();
                }
            });
        }

        @CallSuper
        public void unbind() {
            BottomSheetViewHolder bottomSheetViewHolder = this.target;
            if (bottomSheetViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            bottomSheetViewHolder.bottomSheetTextView = null;
            this.view2131361924.setOnClickListener(null);
            this.view2131361924 = null;
        }
    }

    public BottomSheetAdapter(List<BottomSheetData> list, BottomSheetListItemClickListener bottomSheetListItemClickListener) {
        this.list = list;
        this.listener = bottomSheetListItemClickListener;
    }

    @NonNull
    public BottomSheetViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BottomSheetViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bottom_sheet_list_item, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull BottomSheetViewHolder bottomSheetViewHolder, int i) {
        bottomSheetViewHolder.bottomSheetTextView.setText(((BottomSheetData) this.list.get(i)).getText());
    }

    public int getItemCount() {
        return this.list != null ? this.list.size() : 0;
    }
}
