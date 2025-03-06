package com.zzhoujay.richtext.parser;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;

import com.zzhoujay.html.CustomTagHandler;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Created by zhou on 16-7-27.
 * Html2SpannedParser
 */
public class Html2SpannedParser implements SpannedParser {

    private static final String TAG = "Html2SpannedParser";

    private static final String Z_HTML_CLASS_NAME = "com.zzhoujay.html.Html";
    private static final Method Z_FROM_HTML_METHOD;

    static {
        Method fromHtml = null;
        try {
            Class  htmlClass = com.zzhoujay.html.Html.class;
            fromHtml = htmlClass.getMethod("fromHtml", String.class,Integer.class, Html.ImageGetter.class,  Html.TagHandler.class,List.class);
//            fromHtml = Class.forName(Z_HTML_CLASS_NAME).getMethod("fromHtml", String.class, Html.ImageGetter.class,  Html.TagHandler.class,List.class);
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
        Z_FROM_HTML_METHOD = fromHtml;
    }

    private Html.TagHandler tagHandler;
    private List<CustomTagHandler> customTagHandlers;

    public Html2SpannedParser(Html.TagHandler tagHandler, List<CustomTagHandler> customTagHandlers) {
        this.tagHandler = tagHandler;
        this.customTagHandlers = customTagHandlers;
    }

    @SuppressLint({"ObsoleteSdkInt", "DEPRECATION"})
    @Override
    public Spanned parse(String source) {
        if (Z_FROM_HTML_METHOD != null) {
            try {
                return (Spanned) Z_FROM_HTML_METHOD.invoke(null, source,255, null, tagHandler,customTagHandlers);
            } catch (Exception e) {
                Log.d(TAG, "Z_FROM_HTML_METHOD invoke failure", e);
            }
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY, null, tagHandler);
        } else {
            return Html.fromHtml(source, null, tagHandler);
        }
    }
}
