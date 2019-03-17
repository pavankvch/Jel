package com.jelsat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.businesslogic.help.HelpPresenter;
import com.data.help.GetAnswerRequest;
import com.jelsat.R;
import com.jelsat.compoundviews.ProfileCompoundView;
import java.util.HashMap;
import java.util.List;

public class HelpFaqAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private HelpPresenter helpGuestPresenter;
    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

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
        return true;
    }

    public HelpFaqAdapter(Context context, List<String> list, HashMap<String, List<String>> hashMap, HelpPresenter helpPresenter) {
        this._context = context;
        this.listDataHeader = list;
        this.listDataChild = hashMap;
        this.helpGuestPresenter = helpPresenter;
    }

    public Object getChild(int i, int i2) {
        return ((List) this.listDataChild.get(this.listDataHeader.get(i))).get(i2);
    }

    public View getChildView(final int i, final int i2, boolean z, View view, ViewGroup viewGroup) {
        String str = (String) getChild(i, i2);
        if (view == null) {
            view = ((LayoutInflater) this._context.getSystemService("layout_inflater")).inflate(R.layout.faq_list_text, null);
        }
        ProfileCompoundView profileCompoundView = (ProfileCompoundView) view.findViewById(R.id.question_view);
        profileCompoundView.setTitleTextView(str);
        profileCompoundView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = (String) HelpFaqAdapter.this.getGroup(i);
                GetAnswerRequest getAnswerRequest = new GetAnswerRequest();
                getAnswerRequest.setGroupId(str.split("#")[1]);
                view = new StringBuilder();
                view.append(i2);
                getAnswerRequest.setQuestionId(view.toString());
                HelpFaqAdapter.this.helpGuestPresenter.getGuestAnswer(HelpFaqAdapter.this._context.getString(R.string.please_wait), getAnswerRequest);
            }
        });
        return view;
    }

    public int getChildrenCount(int i) {
        return this.listDataChild != null ? ((List) this.listDataChild.get(this.listDataHeader.get(i))).size() : 0;
    }

    public Object getGroup(int i) {
        return this.listDataHeader.get(i);
    }

    public int getGroupCount() {
        return this.listDataHeader != null ? this.listDataHeader.size() : 0;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        String str = (String) getGroup(i);
        if (view == null) {
            view = ((LayoutInflater) this._context.getSystemService("layout_inflater")).inflate(R.layout.feedback_list_header, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.img_action);
        ((TextView) view.findViewById(R.id.lblListHeader)).setText(str.split("#")[0]);
        if (z) {
            imageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
        }
        if (!z) {
            imageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }
        return view;
    }

    public void setData(List<String> list, HashMap<String, List<String>> hashMap) {
        this.listDataHeader = list;
        this.listDataChild = hashMap;
        notifyDataSetChanged();
    }
}
