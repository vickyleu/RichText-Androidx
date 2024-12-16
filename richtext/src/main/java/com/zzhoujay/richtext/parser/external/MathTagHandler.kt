package com.zzhoujay.richtext.parser.external

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import com.zzhoujay.html.CustomTagHandler
import com.zzhoujay.richtext.mathdisplay.MTFontManager
import com.zzhoujay.richtext.spans.MTMathSpan
import org.xml.sax.Attributes

class MathTagHandler(
    private val context: Context,
    private val textSizeSp: Float = 30f
) : CustomTagHandler {
    init {
        MTFontManager.setContext(context)
    }

    private val splitMathTex = "<math-tex>"
    private val splitMathTex2 = "<math-tex2>"
    override fun handleTag(
        opening: Boolean,
        tag: String,
        output: Editable
    ): Boolean {
        return when (tag) {
            "span" -> true
            else -> {
                if (tag == "img") {
                    true
                } else false
            }
        }
    }

    override fun prepereTag( opening: Boolean,tag: String, output: SpannableStringBuilder): Boolean {
        return when (tag) {
            "span" -> true
            else -> {
                if (tag == "img") {
                    if (output.contains(splitMathTex2)) {
                        return true
                    }
                    false
                } else false
            }
        }
    }

    override fun startTag(
        tag: String,
        attributes: Attributes?,
        ssb: SpannableStringBuilder
    ): Boolean {
        when (tag) {
            "span" -> {
                if (attributes != null) {
                    val clazz = attributes.getValue("class")
                    if (clazz != null) {
                        if (clazz.contains("math-tex")) {
//                            ssb.append("\n")
                            ssb.append(splitMathTex)
//                            ssb.append("\n")
                            return true
                        }
                    }
                }
                return false
            }
            "img" ->{
                val clazz = attributes?.getValue("class")?.trim()
                    ?.replace("\"","")
                if (clazz == "kfformula") {
                    if (attributes.getValue("data-latex") != null) {
                       /* ssb.append(splitMathTex2)
                        val latex = attributes.getValue("data-latex")
                        ssb.append(" $latex ")
                        return true*/
                        return false
                    }
                }
                return false
            }
            else -> return false
        }
    }
    private fun convertToLatex(input: String): String {
        return input.replace("\\\\", "\\")

    }
    override fun endTag(
        tag: String,
        ssb: SpannableStringBuilder
    ): Boolean {
        when (tag) {
            "span" -> {
                if (ssb.contains(splitMathTex)) {
                    ssb.substring(ssb.lastIndexOf(splitMathTex), ssb.length).apply {
                        val text = this.replace(splitMathTex, "")
                        text.trim().removeSurrounding("\\(", "\\)").apply {
                            val latex = this
//                            val oringLatex = "\\frac12rt {{b}^{2} {2a}>\\frac {dy} {dx}"
                            ssb.replace(ssb.indexOf(splitMathTex), ssb.length, latex)
                            val span = MTMathSpan().apply {
                                this.latex = latex.apply {
                                    println("latex===>>>==${this}==")
                                }
                                this.textColor = Color.BLACK
                                val fSize =
                                    context.resources.displayMetrics.scaledDensity * textSizeSp
                                this.fontSize = fSize
                                this.font = MTFontManager.latinModernFontWithSize(fSize)
                                this.labelMode = MTMathSpan.MTMathViewMode.KMTMathViewModeText
                                this.textAlignment =
                                    MTMathSpan.MTTextAlignment.KMTTextAlignmentLeft

                                this.displayErrorInline = true
                            }
                            ssb.setSpan(
                                span,
                                ssb.length - latex.length,
                                ssb.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            // 给latex添加span,设置一个固定大小
                            // 设置固定大小的 span
                            val height = span.getMeasuredSize().height
                            val fixedSizeSpan =
                                AbsoluteSizeSpan(height, true) // 第二个参数 true 表示使用 sp 作为单位
                            ssb.setSpan(
                                fixedSizeSpan,
                                ssb.length - latex.length,
                                ssb.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            return true
                        }
                    }
                }
                return false
            }
            "img" ->{
                if (ssb.contains(splitMathTex2)) {
                    ssb.substring(ssb.lastIndexOf(splitMathTex2), ssb.length).apply {
                        val text = this.replace(splitMathTex2, "")
                        text.trim().removeSurrounding("\\(", "\\)").apply {
                            val latex = convertToLatex(this)
                            ssb.replace(ssb.indexOf(splitMathTex2), ssb.length, latex)
                            val span = MTMathSpan().apply {
                                this.latex = latex.apply {
                                    println("img latex===>>>==${this}==")
                                }
                                this.textColor = Color.BLACK
                                val fSize =
                                    context.resources.displayMetrics.scaledDensity * textSizeSp
                                this.fontSize = fSize
                                this.font = MTFontManager.latinModernFontWithSize(fSize)
                                this.labelMode = MTMathSpan.MTMathViewMode.KMTMathViewModeText
                                this.textAlignment =
                                    MTMathSpan.MTTextAlignment.KMTTextAlignmentLeft

                                this.displayErrorInline = true
                            }
                            ssb.setSpan(
                                span,
                                ssb.length - latex.length,
                                ssb.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            // 给latex添加span,设置一个固定大小
                            // 设置固定大小的 span
                            val height = span.getMeasuredSize().height
                            val fixedSizeSpan =
                                AbsoluteSizeSpan(height, true) // 第二个参数 true 表示使用 sp 作为单位
                            ssb.setSpan(
                                fixedSizeSpan,
                                ssb.length - latex.length,
                                ssb.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
                            return true
                        }
                    }
                }
                return false
            }
            else -> return false
        }
    }
}