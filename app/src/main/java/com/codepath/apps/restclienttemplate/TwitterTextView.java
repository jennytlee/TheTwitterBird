package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jennytlee on 6/28/16.
 */
public class TwitterTextView extends TextView {

    public TwitterTextView(Context context) {
        super(context);

        applyCustomFont(context);
    }


    public TwitterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TwitterTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface GothamNarrowBook = FontCache.getTypeface("GothamNarrowBook.ttf", context);

        setTypeface(GothamNarrowBook);

    }
}
