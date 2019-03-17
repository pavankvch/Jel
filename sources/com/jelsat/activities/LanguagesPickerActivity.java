package com.jelsat.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.data.spokenlanguages.Language;
import com.jelsat.R;
import com.jelsat.adapters.LanguagePickerAdapter;
import com.jelsat.adapters.LanguagePickerAdapter.OnListItemClickListener;
import com.jelsat.baseuiframework.BaseAppCompactActivity;
import com.jelsat.constants.StringConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LanguagesPickerActivity extends BaseAppCompactActivity implements OnListItemClickListener {
    private List<Language> languageModelList;
    private String languages = "";
    private LanguagePickerAdapter languagesAdapter;
    @BindView(2131362273)
    RecyclerView lanuguagesRecyclerview;
    private List<String> selectedLanguagesList;

    public int getActivityLayout() {
        return R.layout.activity_language_picker;
    }

    @OnClick({2131361959})
    public void clickOnCancel(View view) {
        finish();
    }

    @OnClick({2131361943})
    public void clickOnSubmit(View view) {
        if (this.selectedLanguagesList.size() > null) {
            for (view = null; view < this.selectedLanguagesList.size(); view++) {
                if (view == null) {
                    this.languages = (String) this.selectedLanguagesList.get(view);
                } else {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(this.languages);
                    stringBuilder.append(",");
                    stringBuilder.append((String) this.selectedLanguagesList.get(view));
                    this.languages = stringBuilder.toString();
                }
            }
        } else {
            this.languages = "";
        }
        view = new Intent();
        view.putExtra(StringConstants.LANGUAGES, this.languages);
        setResult(102, view);
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.selectedLanguagesList = new ArrayList();
        this.languages = getIntent().getStringExtra(StringConstants.LANGUAGES);
        initLanguagesList();
        setData();
    }

    private void setData() {
        this.languageModelList = new ArrayList();
        try {
            this.languagesAdapter.setData(getlanguages());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initLanguagesList() {
        this.languagesAdapter = new LanguagePickerAdapter(this, this, null);
        this.lanuguagesRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        this.lanuguagesRecyclerview.setAdapter(this.languagesAdapter);
    }

    public void clickOnListItem(Language language, int i, boolean z) {
        if (z) {
            this.selectedLanguagesList.add(language.getLanguage());
        } else if (this.selectedLanguagesList.contains(language.getLanguage())) {
            this.selectedLanguagesList.remove(language.getLanguage());
        }
        this.languagesAdapter.setItemStatusChanged(i, z);
    }

    private List<Language> getlanguages() throws IOException {
        List<Language> arrayList = new ArrayList();
        if (!this.languages.equalsIgnoreCase("")) {
            Collections.addAll(this.selectedLanguagesList, this.languages.split(","));
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open("languages.txt"), "UTF-8"));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            Language language = new Language();
            if (this.selectedLanguagesList.contains(readLine)) {
                language.setSelected(true);
            } else {
                language.setSelected(false);
            }
            language.setLanguage(readLine);
            arrayList.add(language);
        }
    }
}
