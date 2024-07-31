package com.zzhoujay.richtext.parser.external

import android.text.SpannableStringBuilder
import com.zzhoujay.html.CustomTagHandler
import com.zzhoujay.richtext.spans.TableSpan
import org.xml.sax.Attributes
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import org.xmlpull.v1.XmlSerializer
import java.io.ByteArrayOutputStream
import java.util.Stack


interface MaxWidthProvider {
    fun getMaxWidth(): Int
}

class TableTagHandler(private val maxWidthProvider: MaxWidthProvider): CustomTagHandler {
    //通过tag组合成一个xml的list,每一个非table标签都是从最后一个index拼装xml,以table标签为分界,开始一个标签用于组合,接受一个标签用于拼装,变成一个spannable
    private val xmlStack = Stack<Pair<XmlSerializer,ByteArrayOutputStream>>()
    private val splitTabRegex = "<TableTagHandler-table-function>"
    override fun handleTag(opening: Boolean, tag: String): Boolean {
        return when(tag){
            "table" -> true
            "tr" -> true
            "th" -> true
            "td" -> true
            else -> false
        }
    }

    override fun startTag(
        tag: String,
        attributes: Attributes?,
        ssb: SpannableStringBuilder
    ): Boolean {
        when(tag){
            "table" -> {
                val serializer = XmlPullParserFactory.newInstance().newSerializer()
                val outputStream = ByteArrayOutputStream()

                xmlStack.push(serializer to outputStream)//创建一个新的xml
                serializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true)
                serializer.setOutput(outputStream, "UTF-8")
                serializer.startDocument("UTF-8", null)
                serializer.startTag(null, "table")
                ssb.append("${splitTabRegex}")
                return true
            }
            "tr" -> {
                xmlStack.peek()?.first?.startTag(null, "tr")//拿到最后一个xml,创建一个tr元素
                return true
            }
            "th" -> {
                //拿到最后一个xml,从xml中最后一个tr元素中添加一个th元素
                xmlStack.peek()?.first?.startTag(null, "th")//拿到最后一个xml,创建一个tr元素
                return true
            }
            "td" -> {
                //拿到最后一个xml,从xml中最后一个tr元素中添加一个td元素
                xmlStack.peek()?.first?.startTag(null, "td")//拿到最后一个xml,创建一个td元素
                return true
            }
        }
        return false
    }

    override fun endTag(tag: String, ssb: SpannableStringBuilder): Boolean {
        when(tag){
            "table" -> {
                ssb.delete(ssb.length-"${splitTabRegex}".length,ssb.length)
                if((xmlStack.isEmpty())){
                    xmlStack.clear()
                    ssb.append("\n")
                    return false
                }
                val (xml,output) = xmlStack.pop()?:return false
                xml.endTag(null, "table")
                //将xml中的内容添加到ssb中
                xml.endDocument()
                output.flush()
                output.close()
                val xmlString = output.toString()

                val parser = XmlPullParserFactory.newInstance().newPullParser()
                parser.setInput(xmlString.reader())
                var eventType = parser.eventType
                var trCount = 0
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG && parser.name == "tr") {
                        trCount++
                    }
                    eventType = parser.next()
                }
                var trLine = ""
//                // 添加换行符
//                for (i in 0 until trCount) {
//                    trLine+="(tableRow)\n"
//                }
                if(trCount>0){
                    ssb.append("\n")
                    trLine ="(tableRow)"
                    ssb.append("(tableRow)")
                    ssb.setSpan(
                        TableSpan(xmlString,maxWidthProvider),
                        ssb.length - trLine.length,
                        ssb.length,
                        SpannableStringBuilder.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                ssb.append("\n")
                if(xmlStack.isNotEmpty()){
                    xmlStack.clear()
                }
                return true
            }
            "tr" -> {
                xmlStack.peek()?.first?.endTag(null, "tr")//拿到最后一个xml,创建一个tr元素
                return true
            }
            "th" -> {
                ssb.substring(ssb.lastIndexOf("${splitTabRegex}"), ssb.length).apply {
                    val text = this.replace("${splitTabRegex}", "")
                    ssb.delete(ssb.length-text.length,ssb.length)
                    xmlStack.peek()?.first?.text(text);//设置标签文本
                    xmlStack.peek()?.first?.endTag(null, "th")//拿到最后一个xml,从xml中最后一个tr元素中添加一个th元素
                }
                return true
            }
            "td" -> {
                ssb.substring(ssb.lastIndexOf("${splitTabRegex}"), ssb.length).apply {
                    val text = this.replace("${splitTabRegex}", "")
                    ssb.delete(ssb.length-text.length,ssb.length)
                    xmlStack.peek()?.first?.text(text);//设置标签文本
                    xmlStack.peek()?.first?.endTag(null, "td")//拿到最后一个xml,从xml中最后一个tr元素中添加一个th元素
                }
                return true
            }
        }
        return false
    }
}