package com.jelsat.adapters;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.data.amenitiesandhouserules.HouseRuleSafetyPolicy;
import com.jelsat.R;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private Map<String, List<HouseRuleSafetyPolicy>> expandableListDetail;
    private List<String> expandableListTitle;
    private boolean isReviewBooking;

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public boolean hasStableIds() {
        return false;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    public CustomExpandableListAdapter(Context context, List<String> list, Map<String, List<HouseRuleSafetyPolicy>> map, boolean z) {
        this.context = context;
        this.expandableListTitle = list;
        this.expandableListDetail = map;
        this.isReviewBooking = z;
    }

    public Object getChild(int i, int i2) {
        return ((List) this.expandableListDetail.get(this.expandableListTitle.get(i))).get(i2);
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        HouseRuleSafetyPolicy houseRuleSafetyPolicy = (HouseRuleSafetyPolicy) getChild(i, i2);
        i2 = houseRuleSafetyPolicy.getName();
        if (view == null) {
            view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.expandable_list_item_child, null);
        }
        TextView textView = (TextView) view.findViewById(true);
        if (this.isReviewBooking != null) {
            textView.setTextColor(applyColor(R.color.white));
        } else {
            textView.setTextColor(applyColor(R.color.normal_text_color));
        }
        if (houseRuleSafetyPolicy.isNotPolicy() == null) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        } else if (houseRuleSafetyPolicy.isChecked() != 0) {
            if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
                if (this.isReviewBooking != 0) {
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_yes_white, 0, 0, 0);
                } else {
                    textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_yes, 0, 0, 0);
                }
            } else if (this.isReviewBooking != 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_yes_white, 0);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_yes, 0);
            }
        } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != 0) {
            if (this.isReviewBooking != 0) {
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_no_white, 0, 0, 0);
            } else {
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_no, 0, 0, 0);
            }
        } else if (this.isReviewBooking != 0) {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_no_white, 0);
        } else {
            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_no, 0);
        }
        textView.setText(i2);
        textView.setTextSize(1097859072);
        return view;
    }

    public int getChildrenCount(int i) {
        return ((List) this.expandableListDetail.get(this.expandableListTitle.get(i))).size();
    }

    public Object getGroup(int i) {
        return this.expandableListTitle.get(i);
    }

    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        String str = (String) getGroup(i);
        if (view == null) {
            view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.expandable_list_group, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.listTitle);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);
        if (this.isReviewBooking) {
            textView.setTextColor(applyColor(R.color.white));
        } else {
            textView.setTextColor(applyColor(R.color.normal_text_color));
        }
        textView.setText(str);
        if (z) {
            if (this.isReviewBooking != 0) {
                imageView.setImageResource(R.drawable.ic_expand_less_white_24dp);
                textView.setTypeface(ResourcesCompat.getFont(this.context, R.font.sf_ui_display_semibold));
            } else {
                imageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
                textView.setTypeface(ResourcesCompat.getFont(this.context, R.font.sf_ui_display_semibold));
            }
        } else if (this.isReviewBooking != 0) {
            imageView.setImageResource(R.drawable.ic_expand_more_white_24dp);
            textView.setTypeface(ResourcesCompat.getFont(this.context, R.font.sf_ui_display_regular));
        } else {
            imageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
            textView.setTypeface(ResourcesCompat.getFont(this.context, R.font.sf_ui_display_regular));
        }
        return view;
    }

    private int applyColor(@ColorRes int i) {
        return ContextCompat.getColor(this.context, i);
    }
}
