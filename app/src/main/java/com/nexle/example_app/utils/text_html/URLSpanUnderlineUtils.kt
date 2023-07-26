package com.nexle.example_app.utils.text_html

import android.text.TextPaint
import android.text.style.URLSpan

internal class URLSpanUnderlineUtils(url: String) : URLSpan(url) {

    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        ds.isUnderlineText = true
    }
}
