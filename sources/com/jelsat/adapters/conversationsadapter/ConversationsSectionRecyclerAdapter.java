package com.jelsat.adapters.conversationsadapter;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.inbox.MessageData;
import com.jelsat.R;
import com.jelsat.adapters.Inboxmessagesadapter.SectionRecyclerViewAdapter;
import com.jelsat.constants.StringConstants;
import java.util.List;

public class ConversationsSectionRecyclerAdapter extends SectionRecyclerViewAdapter<ConversationsSectionModel, MessageData, SectionViewHolder, ChildViewHolder> {
    private Context context;

    public class ChildViewHolder extends ViewHolder {
        @BindView(2131361986)
        LinearLayout chat_row_lin;
        @BindView(2131362738)
        TextView message;
        @BindView(2131362583)
        TextView sent_or_received;

        public ChildViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder_ViewBinding implements Unbinder {
        private ChildViewHolder target;

        @UiThread
        public ChildViewHolder_ViewBinding(ChildViewHolder childViewHolder, View view) {
            this.target = childViewHolder;
            childViewHolder.message = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_message, "field 'message'", TextView.class);
            childViewHolder.sent_or_received = (TextView) Utils.findRequiredViewAsType(view, R.id.sent_or_received, "field 'sent_or_received'", TextView.class);
            childViewHolder.chat_row_lin = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.chat_row_lin, "field 'chat_row_lin'", LinearLayout.class);
        }

        @CallSuper
        public void unbind() {
            ChildViewHolder childViewHolder = this.target;
            if (childViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            childViewHolder.message = null;
            childViewHolder.sent_or_received = null;
            childViewHolder.chat_row_lin = null;
        }
    }

    public class SectionViewHolder extends ViewHolder {
        @BindView(2131362574)
        TextView section_label;

        public SectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class SectionViewHolder_ViewBinding implements Unbinder {
        private SectionViewHolder target;

        @UiThread
        public SectionViewHolder_ViewBinding(SectionViewHolder sectionViewHolder, View view) {
            this.target = sectionViewHolder;
            sectionViewHolder.section_label = (TextView) Utils.findRequiredViewAsType(view, R.id.section_label, "field 'section_label'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            SectionViewHolder sectionViewHolder = this.target;
            if (sectionViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            sectionViewHolder.section_label = null;
        }
    }

    public ConversationsSectionRecyclerAdapter(Context context, List<ConversationsSectionModel> list) {
        super(context, list);
        this.context = context;
    }

    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_messages_date_section, viewGroup, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_list_row, viewGroup, false));
    }

    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int i, ConversationsSectionModel conversationsSectionModel) {
        sectionViewHolder.section_label.setText(com.jelsat.utils.Utils.inboxDateFormatter(conversationsSectionModel.getSectionLabel()));
        sectionViewHolder.section_label.setBackgroundColor(applyColor(this.context, R.color.white));
        sectionViewHolder.section_label.setGravity(17);
        if (VERSION.SDK_INT >= 17) {
            sectionViewHolder.section_label.setTextAlignment(4);
        }
    }

    private int applyColor(@NonNull Context context, @ColorRes int i) {
        return ContextCompat.getColor(context, i);
    }

    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int i, int i2, MessageData messageData) {
        if (messageData.getStatus().equalsIgnoreCase(StringConstants.MESSAGE_RECEIVER) != 0) {
            childViewHolder.message.setBackground(this.context.getResources().getDrawable(R.drawable.message_recived_bg));
            childViewHolder.chat_row_lin.setGravity(GravityCompat.START);
            i = new LayoutParams(-1, -2);
            i.setMargins(10, 10, 80, 0);
            childViewHolder.chat_row_lin.setLayoutParams(i);
        } else {
            childViewHolder.message.setBackground(this.context.getResources().getDrawable(R.drawable.message_send_bg));
            childViewHolder.chat_row_lin.setGravity(GravityCompat.END);
            i = new LayoutParams(-1, -2);
            i.setMargins(80, 10, 10, 0);
            childViewHolder.chat_row_lin.setLayoutParams(i);
        }
        childViewHolder.message.setText(messageData.getMessage());
        i = messageData.getTime();
        if (TextUtils.isEmpty(i) == 0) {
            Log.e("msg time : ", com.jelsat.utils.Utils.showTimeWithTwelveTimeStamp(i));
        }
        childViewHolder.sent_or_received.setText(com.jelsat.utils.Utils.showTimeWithTwelveTimeStamp(i));
    }

    public int getItemCount() {
        return super.getItemCount();
    }
}
