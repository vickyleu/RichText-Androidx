package com.zzhoujay.richtext.parser.external

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.util.Log
import com.zzhoujay.html.CustomTagHandler
import com.zzhoujay.richtext.mathdisplay.MTFontManager
import com.zzhoujay.richtext.spans.MTMathSpan
import org.xml.sax.Attributes

class MathTagHandler(context: Context) : CustomTagHandler {
    init {
        MTFontManager.setContext(context)
    }

    private val splitMathTex = "<math-tex>"
    override fun handleTag(
        opening: Boolean,
        tag: String,
    ): Boolean {
        return when (tag) {
            "span" -> true
            else -> false
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
                            ssb.append("\n")
                            ssb.append(splitMathTex)
                            ssb.append("\n")
                            return true
                        }
                    }
                }
                return false
            }

            else -> return false
        }
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
                            ssb.setSpan(
                                MTMathSpan().apply {
                                    this.latex = latex
                                    this.textColor = Color.BLACK
                                    this.fontSize = 40f
                                    this.labelMode = MTMathSpan.MTMathViewMode.KMTMathViewModeText
                                    this.textAlignment =
                                        MTMathSpan.MTTextAlignment.KMTTextAlignmentLeft
                                    this.displayErrorInline = true
                                },
                                ssb.length - latex.length,
                                ssb.length,
                                SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                            )
//                            ssb.append("\n")
//                            ssb.append("\n")
//                            ssb.append("\n")
//                            ssb.append("\n")
//                            ssb.append("\n")
//                            ssb.append("\n")
//                            ssb.append("\n")
//                            ssb.append("\n")
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