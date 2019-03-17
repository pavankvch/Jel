package com.jelsat.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import butterknife.BindView;
import com.businesslogic.feedback.ISubmitFeedbackView;
import com.businesslogic.feedback.SubmitFeedbackPresenter;
import com.data.feedback.FeedbackCategoryResponse;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.GuestFeedbackAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import com.jelsat.constants.ListItemModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubmitFeedbackFragment extends BaseFragment implements ISubmitFeedbackView {
    @BindView(2131362314)
    ExpandableListView expListView;
    private GuestFeedbackAdapter guestAdaptor;
    private SubmitFeedbackPresenter presenter = new SubmitFeedbackPresenter(this, RetrofitClient.getAPIService());

    public int getFragmentLayoutId() {
        return R.layout.feedback_guest;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onFeedbackSubmitSuccess() {
        showToast(getString(R.string.feedback_sumitted));
        getActivity().finish();
    }

    public void onDetach() {
        if (this.presenter != null) {
            this.presenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void setData(List<FeedbackCategoryResponse> list) {
        if (list != null) {
            ArrayList arrayList = new ArrayList();
            HashMap hashMap = new HashMap();
            String str = "";
            for (int i = 0; i < list.size(); i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(((FeedbackCategoryResponse) list.get(i)).getType());
                stringBuilder.append("#");
                stringBuilder.append(((FeedbackCategoryResponse) list.get(i)).getId());
                arrayList.add(new ListItemModel(stringBuilder.toString()));
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(((FeedbackCategoryResponse) list.get(i)).getType());
                stringBuilder2.append("#");
                stringBuilder2.append(((FeedbackCategoryResponse) list.get(i)).getId());
                hashMap.put(stringBuilder2.toString(), str);
            }
            this.guestAdaptor = new GuestFeedbackAdapter(getActivity(), arrayList, this.expListView, hashMap, this.presenter);
            this.expListView.setAdapter(this.guestAdaptor);
            this.expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
                int previousGroup = -1;

                public void onGroupExpand(int i) {
                    if (i != this.previousGroup) {
                        SubmitFeedbackFragment.this.expListView.collapseGroup(this.previousGroup);
                    }
                    this.previousGroup = i;
                }
            });
            this.guestAdaptor.setData(arrayList, hashMap);
        }
    }
}
