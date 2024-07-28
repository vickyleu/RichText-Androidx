package com.zzhoujay.richtext.spans

import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.ReplacementSpan

class TableSpannable : ReplacementSpan() {
    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        return 300
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence?,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        canvas.drawText("你好啊", x, y.toFloat(), paint)
    }
}