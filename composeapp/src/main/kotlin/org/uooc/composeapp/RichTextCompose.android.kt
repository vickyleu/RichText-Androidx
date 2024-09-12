package org.uooc.composeapp

import android.R
import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.updateMargins
import coil3.compose.LocalPlatformContext
import com.zzhoujay.richtext.ImageHolder
import com.zzhoujay.richtext.RichText
import com.zzhoujay.richtext.RichTextConfig
import com.zzhoujay.richtext.RichType
import com.zzhoujay.richtext.callback.ImageFixCallback
import com.zzhoujay.richtext.parser.external.MathTagHandler
import com.zzhoujay.richtext.parser.external.MaxWidthProvider
import com.zzhoujay.richtext.parser.external.TableTagHandler
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun RichTextPlatformView(
    state: MutableState<RichTextCompose>,
    style: TextStyle,
    modifier: Modifier
) {
    with(LocalDensity.current) {
        BoxWithConstraints(modifier = modifier) {
            val context = LocalPlatformContext.current
            val mathTagHandler = remember {
                mutableStateOf(MathTagHandler(context, 25f))
            }

            val tagHandler = remember {
                mutableStateOf(TableTagHandler(context, 20f, object : MaxWidthProvider {
                    override fun getMaxWidth(): Int {
                        return maxWidth.roundToPx()
                    }
                }))
            }


            val imageFix = object : ImageFixCallback {
                override fun onInit(imageHolder: ImageHolder?) {
                    imageHolder?.apply {
                        this.source = convertImageSrc(this.source)
                    }
                }

                override fun onLoading(imageHolder: ImageHolder?) {
                    imageHolder?.apply {
                        this.placeHolder =
                            context.resources.getDrawable(R.drawable.stat_sys_download)
                    }
                }

                override fun onSizeReady(
                    imageHolder: ImageHolder?,
                    width: Int,
                    height: Int,
                    sizeHolder: ImageHolder.SizeHolder?
                ) {
                    imageHolder?.apply {
                        if (sizeHolder != null) {
                            val max = maxWidth.roundToPx()
                            val originalWidth = sizeHolder.width
                            val originalHeight = sizeHolder.height
                            if (originalWidth > max) {
                                // 计算等比例缩小后的高度
                                val newHeight = (originalHeight * max) / originalWidth
                                this.width = max
                                this.height = newHeight
                            } else {
                                this.width = originalWidth
                                this.height = originalHeight
                            }
                        }
                    }
                }

                override fun onImageReady(imageHolder: ImageHolder?, p1: Int, p2: Int) {
                }

                override fun onFailure(imageHolder: ImageHolder?, exception: java.lang.Exception?) {
                }
            }
            var rich by remember {
                mutableStateOf(
                    RichText
                        .fromHtml(state.value.content)
                        .resetSize(true)
                        .sync(false)
                        .type(RichType.html)
                        .customTagParser(mathTagHandler.value)
                        .customTagParser(tagHandler.value)
                        .fix(imageFix)
                        .setup(state.value)
                )
            }

            LaunchedEffect(Unit) {
                snapshotFlow { state.value.content }
                    .distinctUntilChanged()
                    .collect {
                        rich = RichText.fromHtml(state.value.content)
                            .resetSize(true)
                            .sync(false)
                            .type(RichType.html)
                            .customTagParser(mathTagHandler.value)
                            .customTagParser(tagHandler.value)
                            .fix(imageFix)
                            .setup(state.value)
                    }
            }
            DisposableEffect(Unit) {
                onDispose {
//                    rich.recycle()
                }
            }
            with(LocalDensity.current) {
                val listener = remember(state.value.content) {
                    object : View.OnLayoutChangeListener {
                        override fun onLayoutChange(
                            v: View?,
                            left: Int,
                            top: Int,
                            right: Int,
                            bottom: Int,
                            oldLeft: Int,
                            oldTop: Int,
                            oldRight: Int,
                            oldBottom: Int
                        ) {
                            (v ?: return).measure(
                                View.MeasureSpec.makeMeasureSpec(maxWidth.roundToPx(), View.MeasureSpec.EXACTLY),
                                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
                            )

                            state.value.width.value = v.measuredWidth.toDp()
                            state.value.height.value = v.measuredHeight.toDp()

                            println()
                        }
                    }
                }
                AndroidView(
                    factory = { context ->
                        TextView(context).apply {
                            layoutParams = FrameLayout.LayoutParams(
                                FrameLayout.LayoutParams.WRAP_CONTENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT
                            ).apply {
                                this.gravity = android.view.Gravity.TOP or android.view.Gravity.LEFT
                                this.updateMargins(0, 0, 0, 0)
                            }
                            style.let {
                                this.setTextColor(it.color.toArgb())
                                this.textSize = it.fontSize.value
                                try {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                                        this.lineHeight = it.lineHeight.roundToPx()
                                    }
                                } catch (ignored: Exception) {
                                }
                                val rawFw = it.fontWeight ?: FontWeight.Normal
                                val fw = rawFw.toFontWeight()
                                val family = it.fontFamily
                                family?.apply {
                                    setTypeface(this.toTypeface(context, rawFw), fw)
                                } ?: kotlin.run {
                                    setTypeface(null, fw)
                                }
                            }
                            this.addOnLayoutChangeListener(listener)
                            rich.into(this)
                        }
                    },
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .requiredWidthIn(
                            min = 10.dp,
                            max = max(maxWidth, state.value.width.value)
                        )
                        .requiredHeightIn(
                            min = 10.dp,
                            max = max(maxWidth, state.value.height.value)
                        ),
                    update = { view ->
                        rich.autoPlay(true)
                    }
                )
            }
        }
    }
}


private fun RichTextConfig.RichTextConfigBuild.setup(state: RichTextCompose): RichTextConfig.RichTextConfigBuild {
    return resetSize(true)
        .sync(false)
        .imageClick { mutableList, i ->
            state.clickImages.invoke(mutableList, i)
        }
        .type(RichType.html)
        .urlClick {
            if (it.contains("http")) {
                state.clickUrl.invoke(it)
                true
            } else false
        }
}

fun FontFamily.toTypeface(context: Context, fontWeight: FontWeight?): Typeface? {
    return when (this) {
        FontFamily.SansSerif -> Typeface.create("sans-serif", fontWeight.toFontWeight())
        FontFamily.Serif -> Typeface.create("serif", fontWeight.toFontWeight())
        FontFamily.Monospace -> Typeface.create("monospace", fontWeight.toFontWeight())
        FontFamily.Cursive -> Typeface.create("cursive", fontWeight.toFontWeight())
        // 添加对自定义字体的支持
        else -> Typeface.DEFAULT
    }
}

//COMPOSE中的FontWeight转换为Android中的Typeface
private fun FontWeight?.toFontWeight(): Int {
    return when (this) {
        FontWeight.Thin, FontWeight.ExtraLight, FontWeight.Light, FontWeight.Normal -> Typeface.NORMAL
        FontWeight.Medium, FontWeight.SemiBold, FontWeight.Bold, FontWeight.ExtraBold, FontWeight.Black -> Typeface.BOLD
        else -> Typeface.NORMAL
    }
}
