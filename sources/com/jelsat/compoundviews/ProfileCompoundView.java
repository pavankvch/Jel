package com.jelsat.compoundviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jelsat.R;
import java.util.Locale;

public class ProfileCompoundView extends LinearLayout {
    private static String TAG = "ProfileCompoundView";
    private Drawable drawableLeft;
    private boolean isLanguageTextVisible;
    private boolean isTextViewLeftIconVisible;
    private String language;
    private TextView languageText;
    private String title;
    private TextView titleTextView;

    public ProfileCompoundView(Context context) {
        this(context, null);
    }

    public ProfileCompoundView(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProfileCompoundView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.drawableLeft = null;
        init(context, attributeSet, i, 0);
    }

    @RequiresApi(api = 21)
    public ProfileCompoundView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.drawableLeft = null;
        init(context, attributeSet, i, i2);
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        populateAttributes(context, attributeSet, i, i2);
        initializeViews(context);
    }

    private void populateAttributes(Context context, AttributeSet attributeSet, int i, int i2) {
        attributeSet = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ProfileCompoundView, i, i2);
        try {
            this.isLanguageTextVisible = attributeSet.getBoolean(0, false);
            this.isTextViewLeftIconVisible = attributeSet.getBoolean(2, true);
            this.language = attributeSet.getString(1);
            this.title = attributeSet.getString(4);
            if (VERSION.SDK_INT >= 21) {
                this.drawableLeft = attributeSet.getDrawable(3);
            } else {
                i2 = attributeSet.getResourceId(3, -1);
                if (i2 != -1) {
                    this.drawableLeft = AppCompatResources.getDrawable(context, i2);
                }
            }
            attributeSet.recycle();
        } catch (Throwable th) {
            attributeSet.recycle();
        }
    }

    private void initializeViews(Context context) {
        setOrientation(0);
        inflate(context, R.layout.profile_compound_view, this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.titleTextView = (TextView) findViewById(R.id.title_textView);
        this.languageText = (TextView) findViewById(R.id.language_textView);
        ImageView imageView = (ImageView) findViewById(R.id.arrow_imageView);
        this.titleTextView.setText(this.title);
        this.languageText.setText(this.language);
        if (!this.isTextViewLeftIconVisible) {
            this.titleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar")) {
            this.titleTextView.setCompoundDrawablesWithIntrinsicBounds(null, null, this.drawableLeft, null);
        } else {
            this.titleTextView.setCompoundDrawablesWithIntrinsicBounds(this.drawableLeft, null, null, null);
        }
        if (this.isLanguageTextVisible) {
            imageView.setVisibility(8);
            this.languageText.setVisibility(0);
            return;
        }
        imageView.setVisibility(0);
        this.languageText.setVisibility(8);
    }

    public void setTitleTextView(String str) {
        this.titleTextView.setText(str);
    }

    public void setlanguageText(String str) {
        this.languageText.setText(str);
    }

    public TextView getLanguageText() {
        return this.languageText;
    }

    public void setLanguageText(TextView textView) {
        this.languageText = textView;
    }

    public void setTextDrawableLeft(Drawable drawable) {
        this.titleTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public void setTextDrawableLeft(@DrawableRes int i) {
        if (i != -1) {
            this.drawableLeft = AppCompatResources.getDrawable(getContext(), i);
        }
        this.titleTextView.setCompoundDrawablesWithIntrinsicBounds(this.drawableLeft, null, null, null);
    }
}
