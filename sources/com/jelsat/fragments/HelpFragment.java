package com.jelsat.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.businesslogic.help.HelpPresenter;
import com.businesslogic.help.IHelpView;
import com.data.help.Answer;
import com.data.help.Faq;
import com.data.help.Question;
import com.data.retrofit.RetrofitClient;
import com.data.utils.APIError;
import com.jelsat.R;
import com.jelsat.adapters.HelpFaqAdapter;
import com.jelsat.baseuiframework.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelpFragment extends BaseFragment implements IHelpView {
    @BindView(2131362123)
    ExpandableListView faqElv;
    private HelpFaqAdapter guestAdaptor;
    private HelpPresenter helpPresenter = new HelpPresenter(this, RetrofitClient.getAPIService());

    public int getFragmentLayoutId() {
        return R.layout.fragment_help_faq;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        layoutInflater = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.guestAdaptor = new HelpFaqAdapter(getActivity(), null, null, this.helpPresenter);
        this.faqElv.setAdapter(this.guestAdaptor);
        this.faqElv.expandGroup(null);
        return layoutInflater;
    }

    public void onError(APIError aPIError) {
        if (aPIError == null || aPIError.getSeken_errors() == null) {
            showToast(getString(R.string.general_api_error_msg));
        } else {
            showToast(aPIError.getSeken_errors());
        }
    }

    public void onAnsweSuccess(List<Answer> list) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogue_faq_answer);
        TextView textView = (TextView) dialog.findViewById(R.id.tv_answer);
        ((ImageView) dialog.findViewById(R.id.back_arrow_IV)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        for (int i = 0; i < list.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(((Answer) list.get(i)).getAnswer());
            stringBuilder.append("\n");
            textView.setText(stringBuilder.toString());
        }
        dialog.setCancelable(false);
        dialog.show();
        list = new LayoutParams();
        list.copyFrom(dialog.getWindow().getAttributes());
        list.width = -1;
        list.height = -2;
        dialog.getWindow().setAttributes(list);
    }

    public void onDetach() {
        if (this.helpPresenter != null) {
            this.helpPresenter.unSubscribeDisposables();
        }
        super.onDetach();
    }

    public void setData(List<Faq> list) {
        List arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        for (int i = 0; i < list.size(); i++) {
            List arrayList2 = new ArrayList();
            Faq faq = (Faq) list.get(i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(faq.getType());
            stringBuilder.append("#");
            stringBuilder.append(faq.getGroupId());
            arrayList.add(stringBuilder.toString());
            for (int i2 = 0; i2 < ((Faq) list.get(i)).getQuestions().size(); i2++) {
                arrayList2.add(((Question) ((Faq) list.get(i)).getQuestions().get(i2)).getQuestions());
            }
            stringBuilder = new StringBuilder();
            stringBuilder.append(faq.getType());
            stringBuilder.append("#");
            stringBuilder.append(faq.getGroupId());
            hashMap.put(stringBuilder.toString(), arrayList2);
        }
        this.guestAdaptor.setData(arrayList, hashMap);
    }
}
