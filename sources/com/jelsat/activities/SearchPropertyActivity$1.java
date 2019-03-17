package com.jelsat.activities;

import com.jelsat.R;
import com.jelsat.fragments.SortDialogFragment.OnClickListener;

class SearchPropertyActivity$1 implements OnClickListener {
    final /* synthetic */ SearchPropertyActivity this$0;

    SearchPropertyActivity$1(SearchPropertyActivity searchPropertyActivity) {
        this.this$0 = searchPropertyActivity;
    }

    public void getSelectedIndex(int i) {
        SearchPropertyActivity.access$002(this.this$0, i);
        SearchPropertyActivity.access$100(this.this$0).setSort(String.valueOf(i));
        SearchPropertyActivity.access$200(this.this$0).getSearchPropertyResults(this.this$0.getString(R.string.please_wait), SearchPropertyActivity.access$100(this.this$0));
    }
}
