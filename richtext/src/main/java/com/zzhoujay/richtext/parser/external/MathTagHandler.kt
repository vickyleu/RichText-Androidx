package com.zzhoujay.richtext.parser.external

import android.text.SpannableStringBuilder
import android.util.Log
import com.zzhoujay.html.CustomTagHandler
import org.xml.sax.Attributes

class MathTagHandler: CustomTagHandler {
    override fun handleTag(
        tag: String,
        attributes: Attributes?,
        mSpannableStringBuilder: SpannableStringBuilder
    ): Boolean {
        Log.wtf("MathTagHandler","$tag");
        return false
    }
}