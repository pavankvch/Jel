package com.jelsat.customclasses;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import com.jelsat.R;
import java.util.Locale;

public class CustomEditText extends AppCompatEditText {
    public CustomEditText(Context context) {
        super(context);
    }

    public CustomEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initAttrs(context, attributeSet);
    }

    public CustomEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initAttrs(context, attributeSet);
    }

    void initAttrs(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            attributeSet = context.obtainStyledAttributes(attributeSet, R.styleable.CustomTextView);
            try {
                Drawable drawable;
                Drawable drawable2;
                Drawable drawable3;
                Drawable drawable4 = null;
                if (VERSION.SDK_INT >= 21) {
                    context = attributeSet.getDrawable(1);
                    Drawable drawable5 = attributeSet.getDrawable(2);
                    drawable = attributeSet.getDrawable(0);
                    drawable4 = attributeSet.getDrawable(3);
                    drawable2 = drawable5;
                    drawable3 = drawable;
                    drawable = context;
                } else {
                    int resourceId = attributeSet.getResourceId(1, -1);
                    int resourceId2 = attributeSet.getResourceId(2, -1);
                    int resourceId3 = attributeSet.getResourceId(0, -1);
                    int resourceId4 = attributeSet.getResourceId(3, -1);
                    drawable = resourceId != -1 ? AppCompatResources.getDrawable(context, resourceId) : null;
                    drawable2 = resourceId2 != -1 ? AppCompatResources.getDrawable(context, resourceId2) : null;
                    drawable3 = resourceId3 != -1 ? AppCompatResources.getDrawable(context, resourceId3) : null;
                    if (resourceId4 != -1) {
                        drawable4 = AppCompatResources.getDrawable(context, resourceId4);
                    }
                }
                if (Locale.getDefault().getLanguage().equalsIgnoreCase("ar") != null) {
                    setCompoundDrawablesWithIntrinsicBounds(drawable2, drawable4, drawable, drawable3);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(drawable, drawable4, drawable2, drawable3);
                }
                attributeSet.recycle();
            } catch (Throwable th) {
                attributeSet.recycle();
            }
        }
    }
}
