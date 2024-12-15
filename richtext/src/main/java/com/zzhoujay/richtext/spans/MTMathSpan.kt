package com.zzhoujay.richtext.spans

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.text.style.ReplacementSpan
import android.util.Size
import com.zzhoujay.richtext.mathdisplay.KDefaultFontSize
import com.zzhoujay.richtext.mathdisplay.MTFontManager
import com.zzhoujay.richtext.mathdisplay.parse.MTLineStyle
import com.zzhoujay.richtext.mathdisplay.parse.MTMathList
import com.zzhoujay.richtext.mathdisplay.parse.MTMathListBuilder
import com.zzhoujay.richtext.mathdisplay.parse.MTParseError
import com.zzhoujay.richtext.mathdisplay.parse.MTParseErrors
import com.zzhoujay.richtext.mathdisplay.render.MTFont
import com.zzhoujay.richtext.mathdisplay.render.MTMathListDisplay
import com.zzhoujay.richtext.mathdisplay.render.MTTypesetter
import com.zzhoujay.richtext.spans.TableSpan.Companion
import kotlin.io.path.Path
import kotlin.math.absoluteValue

class MTMathSpan : ReplacementSpan() {
    private var displayList: MTMathListDisplay? = null
    private var _mathList: MTMathList? = null

    /**
     * Holds the error status from the last parse of the LaTeX string.
     * The errorcode of this can be checked to determine if the string was well formatted.
     */
    val lastError = MTParseError()

    /**
     * Not normally used. Only if you are building a mathlist in code.
     * Standard usage is setting a String in latex property.
     */
    var mathList: MTMathList? = null
        set(value) {
            field = value
            if (value != null) {
                latex = MTMathListBuilder.toLatexString(value)
            }
        }

    /**
     * The LaTeX Math string to display in the view.
     *
     * Sample mathview.latex = "x = \frac{-b \pm \sqrt{b^2-4ac}}{2a}"
     */
    var latex: String = ""
        set(value) {
            field = value
            println("lastError.errordesc:: value::${value}")
            MTMathListBuilder.buildFromString(value, lastError).let {
                if(lastError.errorcode != MTParseErrors.ErrorNone) {
                    this._mathList = null
                }else{
                    this._mathList = it
                }
            }
            displayList = null
//            requestLayout()
//            invalidate()
        }

    /**
     * Different display styles supported by the `MTMathView`.
     *
     * The only significant difference between the two modes is how fractions
     * and limits on large operators are displayed.
     */
    enum class MTMathViewMode {
        /// Display mode. Equivalent to $$ in TeX
        KMTMathViewModeDisplay,

        /// Text mode. Equivalent to $ in TeX.
        KMTMathViewModeText
    }

    /**
     *  If view width is not measured to fit equation size this will specify placement within the view.
     * See **textAlignment**
     */
    enum class MTTextAlignment {
        /// Align left.
        KMTTextAlignmentLeft,

        /// Align center.
        KMTTextAlignmentCenter,

        /// Align right.
        KMTTextAlignmentRight
    }

    /**
     * If true the default parse errors will be drawn as text instead of math equation.
     * Default value is true
     */
    var displayErrorInline = true

    /**
     * Font used to draw the equation. See MTFontManager
     */
    var font: MTFont? = MTFontManager.defaultFont()
        set(value) {
            field = value
            displayList = null
//            requestLayout()
//            invalidate()
        }

    /**
     * This is in device pixels. Default value is see KDefaultFontSize
     */
    var fontSize = KDefaultFontSize // This is in device pixels.
        set(value) {
            field = value
            val of = this.font
            if (of != null) {
                val f = of.copyFontWithSize(value)
                this.font = f
            }
        }

    /**
     * Should display or text mode be used.
     */
    var labelMode = MTMathViewMode.KMTMathViewModeDisplay
        set(value) {
            field = value
            displayList = null
//            requestLayout()
//            invalidate()
        }

    /**
     * Color of the equation if not overridden with local color changes by TeX commands
     */
    var textColor = Color.BLACK
        set(value) {
            field = value
            val dl = displayList
            if (dl != null) {
                dl.textColor = value
            }
//            invalidate()
        }

    /**
     * Alignment within the view
     */
    var textAlignment = MTTextAlignment.KMTTextAlignmentLeft
        set(value) {
            field = value
//            requestLayout()
//            invalidate()
        }

    private var currentStyle = MTLineStyle.KMTLineStyleDisplay
        get() {
            return when (labelMode) {
                MTMathViewMode.KMTMathViewModeDisplay -> MTLineStyle.KMTLineStyleDisplay
                MTMathViewMode.KMTMathViewModeText -> MTLineStyle.KMTLineStyleText
            }
        }

    private fun displayError(): Boolean {
        return (lastError.errorcode != MTParseErrors.ErrorNone &&
                this.displayErrorInline)
    }

    /**
     * When parsing errors are drawn this will control the size of the resulting error text and therefore view measured size.
     * In device pixels
     */
    val errorFontSize = 20.0f

    companion object {
        /**
         * Utility function to convert device independent pixel values to device pixels
         */
        private  fun convertDpToPixel(dp: Float): Float {
            val metrics = Resources.getSystem().displayMetrics
            val px = dp * (metrics.densityDpi / 160f)
            return Math.round(px).toFloat()
        }
    }

    private fun drawError(canvas: Canvas, x: Float, y: Float): Boolean {
        if (!displayError()) {
            return false
        }
        val paint = Paint()
        paint.typeface = Typeface.DEFAULT
        val r = errorBounds()
        val path = android.graphics.Path()
        val left =x
        val top = y-r.height()
        val right = left+r.width()
        val bottom =top+convertDpToPixel(errorFontSize)*1.2f
        path.moveTo(left,top)
        path.lineTo(right,top)
        path.lineTo(right,bottom)
        path.lineTo(left,bottom)
        path.close()
        canvas.drawPath(path,paint)
//        canvas.drawPaint(paint)
        paint.color = Color.RED
        paint.textSize = convertDpToPixel(errorFontSize)

        println("lastError.errordesc::${lastError.errordesc}")

        canvas.drawText("公式错误,请仔细检查", x, y, paint)
        return true
    }

    private fun errorBounds(): Rect {
        if (displayError()) {
            val paint = Paint()
            paint.typeface = Typeface.DEFAULT// your preference here
            paint.textSize = convertDpToPixel(errorFontSize)
            val bounds = Rect()
            paint.getTextBounds(lastError.errordesc, 0, lastError.errordesc!!.length, bounds)
            return bounds
        } else {
            return Rect(0, 0, 0, 0)
        }
    }

    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        val size =getMeasuredSize()
        fm?.let {
            var dl = displayList
            val ml = this._mathList
            if (ml != null && dl == null) {
                displayList = MTTypesetter.createLineForMathList(ml, font!!, currentStyle)
                dl = displayList
            }
            val ascent = dl?.ascent?:0f
            val descent = dl?.descent?:0f
            // 设置 FontMetricsInt 参数
            it.ascent = -(ascent.toInt().absoluteValue+10)
            it.descent = descent.toInt().absoluteValue+10
            it.top = it.ascent// 根据需要调整顶部距离
            it.bottom = it.descent // 根据需要调整底部距离
            it.leading = 0
        }
        //下面是view种计算宽高的方法.如何将这个应用到ReplacementSpan 的 getSize中
        return size.width
    }

    fun getMeasuredSize(): Size {
        var dl = displayList
        val ml = this._mathList
        if (ml != null && dl == null) {
            displayList = MTTypesetter.createLineForMathList(ml, font!!, currentStyle)
            dl = displayList
        }
        var height = 0.0f
        var width = 0.0f
        if (dl != null) {
            height = dl.ascent + dl.descent
            width = dl.width
        }
        val r = errorBounds()
        height = maxOf(height, r.height().toFloat())
        width = maxOf(width, r.width().toFloat())
        return Size((width + 1.0f).toInt(), (height + 1.0f).toInt())
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
        // call the super method to keep any drawing from the parent side.
        if (drawError(canvas,x, y+top.toFloat())) {
            return
        }

        val size = getMeasuredSize()
        val width = size.width
        val height = size.height
        var dl = displayList
        val ml = this._mathList
        if (ml != null && dl == null) {
            displayList = MTTypesetter.createLineForMathList(ml, font!!, currentStyle)
            dl = displayList
        }
        if (dl != null) {
            dl.textColor = this.textColor
            // Determine x position based on alignment

            dl.position.x =0f//textX.toFloat()
            dl.position.y =0f// textY
            canvas.save()
            canvas.translate(x, y.toFloat())
            canvas.scale(1.0f, -1.0f)
            dl.draw(canvas)
            canvas.restore()
        }

    }
}