package com.zzhoujay.richtext.spans

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import android.text.SpannableStringBuilder
import android.text.SpannedString
import android.text.style.ReplacementSpan
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory


class TableSpan(private val tableXml: String) : ReplacementSpan() {
    private val columnWidths = mutableListOf<Int>()
    private val rows = mutableListOf<List<Pair<Boolean,String>>>()
    private var maxRowHeight = 0


    private fun getMeasuredSize(): Pair<Int, Int> {
        val totalWidth = columnWidths.sum()+convertDpToPixel(20f).toInt()
        val totalHeight = (rows.size * (maxRowHeight))+convertDpToPixel(20f).toInt()
        println("第第第 总宽度:$totalWidth,总高度:$totalHeight 最大行高:$maxRowHeight")
        return Pair(totalWidth, totalHeight)
    }

    private fun parseXml(xml: String,paint: Paint) {
        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(xml.reader())
        var eventType = parser.eventType
        var currentRow = mutableListOf<Pair<Boolean,String>>()
        var currentText = ""

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    if (parser.name == "tr") {
                        currentRow = mutableListOf()
                    }
                }
                XmlPullParser.TEXT -> {
                    currentText = parser.text.trim() // Trim whitespace
                }
                XmlPullParser.END_TAG -> {
                    when (parser.name) {
                        "th", "td" -> {
                            currentRow.add((parser.name=="th") to currentText )
                            val textWidth = convertDpToPixel(10+(Paint().measureText(currentText).toInt() + 20).toFloat()+10).toInt()
                            if (columnWidths.size < currentRow.size) {
                                columnWidths.add(textWidth)
                            } else {
                                columnWidths[currentRow.size - 1] = maxOf(columnWidths[currentRow.size - 1], textWidth)
                            }
                        }
                        "tr" -> {
                            if (currentRow.isNotEmpty()) {
                                rows.add(currentRow)
                                val p =
                                    paint.textSize.toInt()+(paint.fontMetrics.bottom.toInt()-paint.fontMetrics.top.toInt())
                                maxRowHeight = maxOf(maxRowHeight, p)
                            }
                        }
                    }
                }
            }
            eventType = parser.next()
        }
        rows.forEachIndexed { index, strings ->
            println("第${index+1}行: ${strings.mapIndexed { i, s -> "第${i+1}个:$s" }}")
        }
        columnWidths.forEachIndexed { index, int ->
            println("第${index+1}列: 宽度:$int")
        }
    }

    private var isParsed = false

    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        if(isParsed.not()){
            parseXml(tableXml,paint)
        }
        val (totalWidth, totalHeight) = getMeasuredSize()
        // 设置字体度量高度
        fm?.let {
            it.ascent = (maxRowHeight.toFloat()*rows.size.toFloat()).toInt()//(totalHeight.toFloat() / (rows.size-1).toFloat()).toInt()

            it.descent =(maxRowHeight.toFloat()*rows.size.toFloat()).toInt()// (totalHeight.toFloat() / (rows.size-1).toFloat()).toInt()
            it.top = it.ascent
            it.leading = 0

            it.bottom = it.descent
        }
        return totalWidth
    }
    companion object {
        /**
         * Utility function to convert device independent pixel values to device pixels
         */
       private fun convertDpToPixel(dp: Float): Float {
            val metrics = Resources.getSystem().displayMetrics
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px).toFloat()
        }
    }

    private var isDraw = false
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
        val (totalWidth, totalHeight) = getMeasuredSize()

        // Draw the background color
        paint.color = Color.BLACK

        val left = x.toFloat()+convertDpToPixel(10f)

        var startX = left
        var startY = y.toFloat()+convertDpToPixel(10f)
        var rectStartY = startY-paint.textSize.toInt()
        rows.forEachIndexed { index, row ->
            startX = left
            row.forEachIndexed { rowIndex, (bold,s) ->
                val cellRect = Rect(
                    startX.toInt(), rectStartY.toInt(),
                    (startX + columnWidths[rowIndex].toFloat()).toInt(), (rectStartY + maxRowHeight).toInt())
                // Draw cell border
                val borderPaint = Paint(paint)
                borderPaint.style = Paint.Style.STROKE
                borderPaint.strokeWidth = 2f
                borderPaint.color = Color.BLACK
                canvas.drawRect(cellRect, borderPaint)
                val boldPaint = Paint(paint).apply {
                    typeface = Typeface.create(Typeface.DEFAULT, if (bold)  Typeface.BOLD else Typeface.NORMAL)
                }
                val rowHeight = maxRowHeight
                val textHeight = boldPaint.fontMetrics.bottom - boldPaint.fontMetrics.top
                canvas.drawText(s,startX+(convertDpToPixel(10f)),startY+((rowHeight-textHeight)/2f),boldPaint)
                startX += columnWidths[rowIndex]
            }
            startY += maxRowHeight
            rectStartY += maxRowHeight
        }
    }
}