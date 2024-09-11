package com.zzhoujay.richtext.spans

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.style.ReplacementSpan
import com.zzhoujay.richtext.parser.external.MaxWidthProvider
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class TableSpan(
    private val tableXml: String,
    private val maxWidthProvider: MaxWidthProvider,
    private val context: Context,
    private val textSizeSp: Float
) :
    ReplacementSpan() {
    private val columnCount = mutableListOf<Int>()
    private val rows = mutableListOf<MutableList<Pair<Boolean, String>>>()
    private val rowHeights = mutableListOf<Int>()
    private var totalWidth = 0

    private fun getMeasuredSize(): Pair<Int, Int> {
        val totalHeight = rowHeights.sum() + convertDpToPixel(20f).toInt()
        return Pair(totalWidth, totalHeight)
    }

    private fun parseXml(xml: String, paint: Paint) {
        val parser = XmlPullParserFactory.newInstance().newPullParser()
        parser.setInput(xml.reader())
        var eventType = parser.eventType
        var currentRow = mutableListOf<Pair<Boolean, String>>()
        var currentText = ""

        while (eventType != XmlPullParser.END_DOCUMENT) {
            when (eventType) {
                XmlPullParser.START_TAG -> {
                    if (parser.name == "tr") {
                        currentRow = mutableListOf()
                    }
                }

                XmlPullParser.TEXT -> {
                    currentText = parser.text
                }

                XmlPullParser.END_TAG -> {
                    when (parser.name) {
                        "th", "td" -> {
                            currentRow.add((parser.name == "th") to currentText)
                        }

                        "tr" -> {
                            if (currentRow.isNotEmpty()) {
                                rows.add(currentRow)
                                columnCount.add(currentRow.size)
                            }
                        }
                    }
                }
            }
            eventType = parser.next()
        }

        val maxColumnCount = columnCount.maxOrNull() ?: 0
        totalWidth = maxWidthProvider.getMaxWidth() - convertDpToPixel(20f).toInt()
        val columnWidth = (totalWidth / maxColumnCount)- convertDpToPixel(20f).toInt()

        val fSize =
            context.resources.displayMetrics.scaledDensity * textSizeSp
        paint.textSize = fSize
        rows.forEach { row ->
            row.forEachIndexed { index, cell ->
                val wrappedText = wrapText(cell.second, paint, columnWidth)
                row[index] = cell.first to wrappedText
            }
        }

        rowHeights.clear()
        rows.forEach { row ->
            val rowHeight = ((row.maxOfOrNull { it.second.lines().size } ?: 0) *
                    (paint.fontMetrics.bottom - paint.fontMetrics.top).toInt())
            rowHeights.add(rowHeight)
        }
    }

    private fun wrapText(text: String, paint: Paint, maxWidth: Int): String {
        val currentLine = StringBuilder()
        text.split("\n").forEach { word ->
            if (paint.measureText(word) <= maxWidth) {
                currentLine.append(word)
            } else {
                val sb= StringBuilder()
                var oldWord = word
                var theWord = word
                var isFinished = false
                while (isFinished.not()){
                    if(theWord.length<=1){
                        break
                    }
                    //根据文字测量宽度,判断是否需要换行
                    theWord = theWord.substring(0, theWord.length-1)
                    val totalWidthSplitAfter = paint.measureText(theWord)
                    if(totalWidthSplitAfter<=maxWidth){
                        sb.append(theWord)
                        sb.append("\n")
                        oldWord = oldWord.substring(theWord.length)
                        if(oldWord.isNotEmpty()) {
                            theWord = oldWord
                        }else{
                            isFinished = true
                        }
                    }else{
                        oldWord = oldWord.substring(0,oldWord.length)
                    }
                }
                currentLine.append(sb.toString())
            }
        }
        println("试试看能不能正常换行: ${currentLine.toString()}")
        return currentLine.toString()
    }

    private var isParsed = false

    override fun getSize(paint: Paint, text: CharSequence?, start: Int, end: Int, fm: Paint.FontMetricsInt?): Int {
        if (!isParsed) {
            parseXml(tableXml, paint)
            isParsed = true
        }
        val (totalWidth, totalHeight) = getMeasuredSize()
        fm?.let {
            // 确保字体度量与实际行高一致
            val totalLineHeight = rowHeights.sum()
            it.ascent = -((totalHeight.toFloat()*0.05f).toInt())
            it.descent = ((totalHeight.toFloat()*0.95f).toInt())
//            it.descent = totalLineHeight / rowHeights.size
            it.top = it.ascent
            it.bottom = it.descent
        }
        return maxWidthProvider.getMaxWidth()
    }

    override fun draw(canvas: Canvas, text: CharSequence?, start: Int, end: Int, x: Float, top: Int, y: Int, bottom: Int, paint: Paint) {
        val (totalWidth, totalHeight) = getMeasuredSize()
        paint.color = Color.BLACK
        val fSize =
            context.resources.displayMetrics.scaledDensity * textSizeSp
        paint.textSize = fSize


        val left = x + convertDpToPixel(10f)
        var startX = left
        var startY =  y + convertDpToPixel(10f)
        var rectStartY = startY - paint.textSize.toInt()

        val maxColumnCount = columnCount.maxOrNull() ?: 0
        val columnWidth = totalWidth / maxColumnCount

        rows.forEachIndexed { index, row ->
            startX = left
            row.forEachIndexed { rowIndex, (bold, cellText) ->
                val cellRect = Rect(
                    startX.toInt(),
                    rectStartY.toInt(),
                    (startX + columnWidth).toInt(),
                    (rectStartY + rowHeights[index]).toInt()
                )
                val borderPaint = Paint(paint).apply {
                    style = Paint.Style.STROKE
                    strokeWidth = 2f
                    color = Color.BLACK
                }
                canvas.drawRect(cellRect, borderPaint)
                val boldPaint = Paint(paint).apply {
                    typeface = Typeface.create(Typeface.DEFAULT, if (bold) Typeface.BOLD else Typeface.NORMAL)
                }
                val lines = cellText.split("\n")
                lines.forEachIndexed { lineIndex, line ->
                    val textY = startY + lineIndex * (boldPaint.fontMetrics.bottom - boldPaint.fontMetrics.top)
                    canvas.drawText(line, startX + convertDpToPixel(10f), textY, boldPaint)
                }
                startX += columnWidth
            }
            startY += rowHeights[index]
            rectStartY += rowHeights[index]
        }
    }

    companion object {
        private fun convertDpToPixel(dp: Float): Float {
            val metrics = Resources.getSystem().displayMetrics
            return dp * (metrics.densityDpi / 160f)
        }
    }
}
