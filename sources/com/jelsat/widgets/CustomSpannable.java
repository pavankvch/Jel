package com.jelsat.widgets;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

public class CustomSpannable extends ClickableSpan {
    private boolean isUnderline = true;

    public void onClick(View view) {
    }

    public CustomSpannable(boolean z) {
        this.isUnderline = z;
    }

    public void updateDrawState(TextPaint textPaint) {
        textPaint.setUnderlineText(this.isUnderline);
        textPaint.setColor(Color.parseColor("#1b76d3"));
    }
}
