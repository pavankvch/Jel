package com.jelsat.adapters;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.businesslogic.feedback.SubmitFeedbackPresenter;
import com.data.feedback.SubmitFeedbackRequest;
import com.jelsat.R;
import com.jelsat.constants.EdittextValues;
import com.jelsat.constants.ListItemModel;
import java.util.ArrayList;
import java.util.HashMap;

public class GuestFeedbackAdapter extends BaseExpandableListAdapter {
    private Context _context;
    private HashMap<String, String> _listDataChild;
    private ArrayList<ListItemModel> _listDataHeader;
    private ExpandableListView expListView;
    private SubmitFeedbackPresenter presenter;

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public int getChildrenCount(int i) {
        return 1;
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

    public GuestFeedbackAdapter(Context context, ArrayList<ListItemModel> arrayList, ExpandableListView expandableListView, HashMap<String, String> hashMap, SubmitFeedbackPresenter submitFeedbackPresenter) {
        this._context = context;
        this._listDataHeader = arrayList;
        this.expListView = expandableListView;
        this._listDataChild = hashMap;
        this.presenter = submitFeedbackPresenter;
    }

    public Object getChild(int i, int i2) {
        return this._listDataChild.get(this._listDataHeader.get(i));
    }

    public View getChildView(final int i, final int i2, boolean z, View view, ViewGroup viewGroup) {
        this.expListView.setDividerHeight(2);
        if (view == null) {
            view = ((LayoutInflater) this._context.getSystemService("layout_inflater")).inflate(R.layout.feedback_list_child, null);
        }
        final EditText editText = (EditText) view.findViewById(true);
        editText.setImeOptions(6);
        editText.setRawInputType(1);
        TextView textView = (TextView) view.findViewById(R.id.btn_submit);
        Log.d("groupPosition", String.valueOf(i));
        Log.d("childPosition", String.valueOf(i2));
        if (this._listDataHeader.size() > 0) {
            ((ListItemModel) this._listDataHeader.get(i)).getArrayList().add(new EdittextValues(""));
            if (((EdittextValues) ((ListItemModel) this._listDataHeader.get(i)).getArrayList().get(i2)).getValue().equals("")) {
                editText.setText("");
            } else {
                editText.setText(((EdittextValues) ((ListItemModel) this._listDataHeader.get(i)).getArrayList().get(i2)).getValue());
            }
        }
        editText.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (!z) {
                    ((EdittextValues) ((ListItemModel) GuestFeedbackAdapter.this._listDataHeader.get(i)).getArrayList().get(i2)).setValue(((EditText) view).getText().toString());
                }
            }
        });
        editText.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() == R.id.et_flblListItem) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    if ((motionEvent.getAction() & 255) == 1) {
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (editText.getText().toString().length() > null) {
                    String str = (String) GuestFeedbackAdapter.this.getGroup(i);
                    SubmitFeedbackRequest submitFeedbackRequest = new SubmitFeedbackRequest();
                    submitFeedbackRequest.setId(str.split("#")[1]);
                    submitFeedbackRequest.setComments(editText.getText().toString().trim());
                    GuestFeedbackAdapter.this.presenter.submitFeedback(GuestFeedbackAdapter.this._context.getString(R.string.please_wait), submitFeedbackRequest);
                    return;
                }
                Toast.makeText(GuestFeedbackAdapter.this._context, GuestFeedbackAdapter.this._context.getString(R.string.please_enter_your_meassage), 0).show();
            }
        });
        return view;
    }

    public Object getGroup(int i) {
        return ((ListItemModel) this._listDataHeader.get(i)).getTitle();
    }

    public int getGroupCount() {
        return this._listDataHeader != null ? this._listDataHeader.size() : 0;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        i = ((ListItemModel) this._listDataHeader.get(i)).getTitle();
        this.expListView.setDividerHeight(2);
        if (view == null) {
            view = ((LayoutInflater) this._context.getSystemService("layout_inflater")).inflate(R.layout.feedback_list_header, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.lblListHeader);
        ImageView imageView = (ImageView) view.findViewById(R.id.img_action);
        textView.setText(i.split("#")[0]);
        if (z) {
            imageView.setImageResource(R.drawable.ic_expand_less_black_24dp);
            textView.setTypeface(ResourcesCompat.getFont(this._context, R.font.sf_ui_display_semibold));
        }
        if (!z) {
            imageView.setImageResource(R.drawable.ic_expand_more_black_24dp);
            textView.setTypeface(ResourcesCompat.getFont(this._context, true));
        }
        return view;
    }

    public void setData(ArrayList<ListItemModel> arrayList, HashMap<String, String> hashMap) {
        this._listDataHeader = arrayList;
        this._listDataChild = hashMap;
        notifyDataSetChanged();
    }
}
