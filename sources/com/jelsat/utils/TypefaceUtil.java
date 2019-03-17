package com.jelsat.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import com.jelsat.R;
import java.lang.reflect.Field;

public class TypefaceUtil {
    public static void overrideFont(Context context) {
        try {
            context = ResourcesCompat.getFont(context, R.font.sf_ui_display_regular);
            Field declaredField = Typeface.class.getDeclaredField("SERIF");
            declaredField.setAccessible(true);
            declaredField.set(null, context);
        } catch (Context context2) {
            context2.printStackTrace();
        }
    }
}
