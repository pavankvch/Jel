package com.jelsat.adapters.notificationsadapter;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.data.inbox.InboxNotificationData;
import com.jelsat.R;
import com.jelsat.adapters.Inboxmessagesadapter.SectionRecyclerViewAdapter;
import com.jelsat.utils.GlideApp;
import java.util.List;

public class NotificationsSectionRecyclerAdapter extends SectionRecyclerViewAdapter<NotificationSectionModel, InboxNotificationData, SectionViewHolder, ChildViewHolder> {
    private Context context;
    private ListItemClickListener listItemClickListener;

    public interface ListItemClickListener {
        void clickOnListItem(InboxNotificationData inboxNotificationData, int i);
    }

    public class ChildViewHolder extends ViewHolder {
        @BindView(2131362248)
        ImageView img_profile;
        View itemView;
        @BindView(2131362742)
        TextView tv_msg_data;
        @BindView(2131362743)
        TextView tv_msg_heading;
        @BindView(2131362744)
        TextView tv_msg_time;

        public ChildViewHolder(View view) {
            super(view);
            this.itemView = view;
            ButterKnife.bind(this, view);
        }
    }

    public class ChildViewHolder_ViewBinding implements Unbinder {
        private ChildViewHolder target;

        @UiThread
        public ChildViewHolder_ViewBinding(ChildViewHolder childViewHolder, View view) {
            this.target = childViewHolder;
            childViewHolder.tv_msg_heading = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_msg_heading, "field 'tv_msg_heading'", TextView.class);
            childViewHolder.tv_msg_data = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_msg_data, "field 'tv_msg_data'", TextView.class);
            childViewHolder.tv_msg_time = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_msg_time, "field 'tv_msg_time'", TextView.class);
            childViewHolder.img_profile = (ImageView) Utils.findRequiredViewAsType(view, R.id.img_profile, "field 'img_profile'", ImageView.class);
        }

        @CallSuper
        public void unbind() {
            ChildViewHolder childViewHolder = this.target;
            if (childViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            childViewHolder.tv_msg_heading = null;
            childViewHolder.tv_msg_data = null;
            childViewHolder.tv_msg_time = null;
            childViewHolder.img_profile = null;
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

    public NotificationsSectionRecyclerAdapter(Context context, List<NotificationSectionModel> list, ListItemClickListener listItemClickListener) {
        super(context, list);
        this.context = context;
        this.listItemClickListener = listItemClickListener;
    }

    public SectionViewHolder onCreateSectionViewHolder(ViewGroup viewGroup, int i) {
        return new SectionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_messages_date_section, viewGroup, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup, int i) {
        return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_messages_data, viewGroup, false));
    }

    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int i, NotificationSectionModel notificationSectionModel) {
        sectionViewHolder.section_label.setText(notificationSectionModel.getSectionLabel());
    }

    public void onBindChildViewHolder(ChildViewHolder childViewHolder, int i, final int i2, final InboxNotificationData inboxNotificationData) {
        childViewHolder.tv_msg_heading.setText(inboxNotificationData.getPayload().getTitle());
        childViewHolder.tv_msg_data.setText(inboxNotificationData.getPayload().getBody());
        i = inboxNotificationData.getTimestamp();
        String[] split = i.split("\\s+");
        if (TextUtils.isEmpty(i) == 0) {
            childViewHolder.tv_msg_time.setText(com.jelsat.utils.Utils.showTimeWithTwelveTimeStamp(split[1]));
        }
        if (Integer.parseInt(inboxNotificationData.getReadNotification()) == 1) {
            childViewHolder.tv_msg_data.setTextColor(applyColor(R.color.normal_text_color_65pr_opacity));
            childViewHolder.tv_msg_heading.setTextColor(applyColor(R.color.normal_text_color_65pr_opacity));
            childViewHolder.tv_msg_time.setTextColor(applyColor(R.color.normal_text_color_65pr_opacity));
        } else {
            childViewHolder.tv_msg_data.setTextColor(applyColor(R.color.normal_text_color));
            childViewHolder.tv_msg_heading.setTextColor(applyColor(R.color.normal_text_color));
            childViewHolder.tv_msg_time.setTextColor(applyColor(R.color.normal_text_color));
        }
        if (inboxNotificationData.getPayload().getData().getCode() == 1001) {
            childViewHolder.img_profile.setVisibility(8);
            return;
        }
        childViewHolder.img_profile.setVisibility(0);
        GlideApp.with(childViewHolder.img_profile.getContext()).asBitmap().load(inboxNotificationData.getPayload().getIcon()).placeholder((int) R.drawable.default_logo_small).error((int) R.drawable.default_logo_small).diskCacheStrategy(DiskCacheStrategy.DATA).into(childViewHolder.img_profile);
        childViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationsSectionRecyclerAdapter.this.listItemClickListener.clickOnListItem(inboxNotificationData, i2);
            }
        });
    }

    public int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }
}
