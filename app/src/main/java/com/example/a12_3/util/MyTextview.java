package com.example.a12_3.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.hp.hpl.sparta.Text;

public class MyTextview extends androidx.appcompat.widget.AppCompatTextView {
    public MyTextview(Context context) {
        super(context);
    }

    public MyTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
