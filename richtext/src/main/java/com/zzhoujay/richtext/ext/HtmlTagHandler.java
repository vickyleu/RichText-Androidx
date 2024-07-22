package com.zzhoujay.richtext.ext;

import android.graphics.Color;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.TextView;

import com.zzhoujay.markdown.style.CodeSpan;
import com.zzhoujay.markdown.style.MarkDownBulletSpan;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import java.lang.ref.SoftReference;
import java.util.Stack;

/**
 * Created by zhou on 16-10-20.
 * 自定义标签的处理
 */
public class HtmlTagHandler implements Html.TagHandler {

    private static final int code_color = Color.parseColor("#F0F0F0");
    private static final int h1_color = Color.parseColor("#333333");


    private Stack<Integer> stack;
    private Stack<Boolean> list;
    private int index = 0;
    private SoftReference<TextView> textViewSoftReference;

    public HtmlTagHandler(TextView textView) {
        stack = new Stack<>();
        list = new Stack<>();
        this.textViewSoftReference = new SoftReference<>(textView);
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        if (opening) {
            startTag(tag, output, xmlReader);
            stack.push(output.length());
        } else {
            int len;
            if (stack.isEmpty()) {
                len = 0;
            } else {
                len = stack.pop();
            }

            reallyHandler(len, output.length(), tag.toLowerCase(), output, xmlReader);
        }
    }

    @SuppressWarnings("unused")
    private void startTag(String tag, Editable out, XMLReader reader) {
        Log.w("startTag","=="+out.toString()+"==");
        switch (tag.toLowerCase()) {
            case "ul":
                list.push(true);
                out.append('\n');
                break;
            case "ol":
                list.push(false);
                out.append('\n');
                break;
            case "pre":
                break;
        }
    }

    @SuppressWarnings("unused")
    private void reallyHandler(int start, int end, String tag, Editable out, XMLReader reader) {
        switch (tag.toLowerCase()) {
            case "body":
                Log.wtf("subSequence","=="+out.charAt(end-1)+"==");
                if(out.length()>2 &&
                        start>=0 && end>start &&
                        out.charAt(end-1) == '\n'){
                    try {
                        Log.wtf("handleTag","tag="+tag+out.toString()+"==="+"=="+"start:"+start+" end:"+end);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    out.delete(end-1,end);
                    if(out.charAt(end-2) == '\n'){
                        out.delete(end-2,end-1);
                    }
                }
                break;
            case "code":
                CodeSpan cs = new CodeSpan(code_color,Color.BLACK);
                out.setSpan(cs, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
            case "ol":
            case "ul":
                out.append('\n');
                if (!list.isEmpty())
                    list.pop();
                break;
            case "li":
                boolean isUl = list.peek();
                int i;
                if (isUl) {
                    index = 0;
                    i = -1;
                } else {
                    i = ++index;
                }
                out.append('\n');
                TextView textView = textViewSoftReference.get();
                if (textView == null) {
                    return;
                }
                MarkDownBulletSpan bulletSpan = new MarkDownBulletSpan(list.size() - 1, h1_color, i);//, textView);
                out.setSpan(bulletSpan, start, out.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                break;
        }
    }

}
