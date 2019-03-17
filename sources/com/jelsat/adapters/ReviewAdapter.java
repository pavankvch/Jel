package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.data.propertydetail.Feed;
import com.jelsat.R;
import com.jelsat.customclasses.ExpandableTextView;
import com.jelsat.utils.GlideApp;
import java.util.List;

public class ReviewAdapter extends Adapter<ReviewViewHolder> {
    private Context context;
    private boolean isFromPublicProfile;
    private List<Feed> reviews;

    public static class ReviewViewHolder extends ViewHolder {
        @BindView(2131362520)
        RatingBar review_rating_bar;
        @BindView(2131362521)
        ImageView reviewer_image;
        @BindView(2131362734)
        TextView tv_hostname;
        @BindView(2131362768)
        ExpandableTextView tv_review;
        @BindView(2131362769)
        TextView tv_reviewdate;

        public ReviewViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class ReviewViewHolder_ViewBinding implements Unbinder {
        private ReviewViewHolder target;

        @UiThread
        public ReviewViewHolder_ViewBinding(ReviewViewHolder reviewViewHolder, View view) {
            this.target = reviewViewHolder;
            reviewViewHolder.tv_hostname = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_hostname, "field 'tv_hostname'", TextView.class);
            reviewViewHolder.tv_reviewdate = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_reviewdate, "field 'tv_reviewdate'", TextView.class);
            reviewViewHolder.tv_review = (ExpandableTextView) Utils.findRequiredViewAsType(view, R.id.tv_review, "field 'tv_review'", ExpandableTextView.class);
            reviewViewHolder.reviewer_image = (ImageView) Utils.findRequiredViewAsType(view, R.id.reviewer_image, "field 'reviewer_image'", ImageView.class);
            reviewViewHolder.review_rating_bar = (RatingBar) Utils.findRequiredViewAsType(view, R.id.review_rating_bar, "field 'review_rating_bar'", RatingBar.class);
        }

        @CallSuper
        public void unbind() {
            ReviewViewHolder reviewViewHolder = this.target;
            if (reviewViewHolder == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            reviewViewHolder.tv_hostname = null;
            reviewViewHolder.tv_reviewdate = null;
            reviewViewHolder.tv_review = null;
            reviewViewHolder.reviewer_image = null;
            reviewViewHolder.review_rating_bar = null;
        }
    }

    public ReviewAdapter(List<Feed> list, Context context, boolean z) {
        this.reviews = list;
        this.context = context;
        this.isFromPublicProfile = z;
    }

    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ReviewViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cust_review_show, viewGroup, false));
    }

    public void onBindViewHolder(ReviewViewHolder reviewViewHolder, int i) {
        if (this.isFromPublicProfile) {
            reviewViewHolder.tv_hostname.setText(((Feed) this.reviews.get(i)).getHostname());
        } else {
            reviewViewHolder.tv_hostname.setText(((Feed) this.reviews.get(i)).getGuestname());
        }
        reviewViewHolder.tv_reviewdate.setText(com.jelsat.utils.Utils.reviewsDateFormatter(((Feed) this.reviews.get(i)).getSubmitted()));
        reviewViewHolder.review_rating_bar.setRating(((Feed) this.reviews.get(i)).getRating());
        GlideApp.with(this.context).load(((Feed) this.reviews.get(i)).getGuestImage()).placeholder((int) R.drawable.ic_profile_image_1).error((int) R.drawable.ic_profile_image_1).into(reviewViewHolder.reviewer_image);
        reviewViewHolder.tv_review.setText(((Feed) this.reviews.get(i)).getComment());
    }

    public int getItemCount() {
        return this.reviews != null ? this.reviews.size() : 0;
    }

    public void setData(List<Feed> list) {
        this.reviews = list;
        notifyDataSetChanged();
    }
}
