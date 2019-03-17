package com.jelsat.compoundviews;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.data.searchproperty.SearchProperty;
import com.facebook.appevents.AppEventsConstants;
import com.jelsat.R;
import com.jelsat.activities.SearchPropertyActivity;
import com.jelsat.adapters.HorizontalPropertyAdapter;
import com.jelsat.events.SearchPropertyEvent;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class PropertySectionView extends LinearLayout {
    protected Context context;
    private TextView emptyNote;
    private RecyclerView horizontalRecyclerView;
    private int index;
    private TextView moreOption;
    private List<SearchProperty> propertyList;
    private HorizontalPropertyAdapter propertyListAdapter;
    private String sectionHeader;
    private TextView sectionName;

    public PropertySectionView(Context context, List<SearchProperty> list, String str, int i) {
        this(context);
        initLayout(list, str, i);
    }

    public PropertySectionView(Context context) {
        super(context);
        this.context = context;
    }

    protected void initLayout(List<SearchProperty> list, String str, int i) {
        setOrientation(1);
        setBackground(getResources().getDrawable(R.drawable.white_background));
        this.propertyList = list;
        this.sectionHeader = str;
        this.index = i;
        list = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.merge_property_section_view, this, true);
        i = new LayoutParams(-1, -2);
        i.setMargins(0, 0, 0, 15);
        setLayoutParams(i);
        setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up)));
        this.sectionName = (TextView) list.findViewById(R.id.category);
        this.sectionName.setText(str);
        this.moreOption = (TextView) list.findViewById(R.id.moreOption);
        this.moreOption.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                switch (PropertySectionView.this.index) {
                    case null:
                        PropertySectionView.this.clickOnViewAll("");
                        return;
                    case 1:
                        PropertySectionView.this.clickOnViewAll(AppEventsConstants.EVENT_PARAM_VALUE_YES);
                        return;
                    default:
                        PropertySectionView.this.clickOnViewAll("");
                        return;
                }
            }
        });
        this.emptyNote = (TextView) list.findViewById(R.id.noteText);
        this.emptyNote.setText(setEmptyNoteText());
        this.horizontalRecyclerView = (RecyclerView) list.findViewById(R.id.recycler_view);
        this.propertyListAdapter = getPropertyListAdapter();
        this.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this.context, 0, false) {
            public boolean canScrollVertically() {
                return false;
            }
        });
        this.horizontalRecyclerView.setNestedScrollingEnabled(false);
        this.horizontalRecyclerView.setAdapter(this.propertyListAdapter);
        setStreamsData(this.propertyList);
    }

    protected HorizontalPropertyAdapter getPropertyListAdapter() {
        return new HorizontalPropertyAdapter(this.context, this.sectionHeader);
    }

    private String setEmptyNoteText() {
        return getResources().getString(R.string.property_section_nothing_label);
    }

    public void setStreamsData(List<SearchProperty> list) {
        if (list == null) {
            this.emptyNote.setVisibility(0);
            return;
        }
        if (list.size() == 0) {
            this.emptyNote.setVisibility(0);
        } else {
            this.emptyNote.setVisibility(8);
        }
        this.propertyListAdapter.setData(list);
    }

    private void clickOnViewAll(String str) {
        EventBus.getDefault().postSticky(new SearchPropertyEvent(str));
        this.context.startActivity(new Intent(this.context, SearchPropertyActivity.class));
    }
}
