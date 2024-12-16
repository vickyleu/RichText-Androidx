package com.zzhoujay.richtext

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import com.zzhoujay.richtext.drawable.DrawableBorderHolder
import com.zzhoujay.richtext.exceptions.ResetImageSourceException
import com.zzhoujay.richtext.ext.MD5

/**
 * Created by zhou on 16-5-28.
 * ImageHolder
 */
//@SuppressWarnings("ALL")
@Suppress("unused")
class ImageHolder(// 图片URL
    private var source: String, // 图片在在某个富文本中的位置
    @get:Suppress("unused") val position: Int, config: RichTextConfig, textView: TextView?
) {
    /**
     * ScaleType
     */
    //    @IntDef({ScaleType.NONE, ScaleType.CENTER, ScaleType.CENTER_CROP, ScaleType.CENTER_INSIDE, ScaleType.FIT_START,
    //            ScaleType.FIT_END, ScaleType.FIT_CENTER, ScaleType.FIT_XY, ScaleType.FIT_AUTO})
    //    @Retention(RetentionPolicy.SOURCE)
    enum class ScaleType(var value: Int) {
        none(0), center(1), center_crop(2), center_inside(3), fit_center(4), fit_start(5), fit_end(6),
        fit_xy(7), fit_auto(8);

        fun intValue(): Int {
            return value
        }

        companion object {
            @JvmStatic
            fun valueOf(value: Int): ScaleType {
                return entries[value]
            }
        }
    }

    /**
     * ImageState 图片的加载状态
     * INIT: 初始化加载，可以设置图片宽高给Glide
     * LOADING: 加载中，设置placeholder图片的宽高
     * READY: 图片加载成功，设置最终显示的图片的宽高
     * FAILED: 加载失败，设置加载失败的图片的宽高
     */
    @IntDef(
        ImageState.INIT,
        ImageState.LOADING,
        ImageState.READY,
        ImageState.FAILED,
        ImageState.SIZE_READY
    )
    @Retention(
        AnnotationRetention.SOURCE
    )
    annotation class ImageState {
        companion object {
            const val INIT: Int = 0
            const val LOADING: Int = 1
            const val READY: Int = 2
            const val FAILED: Int = 3
            const val SIZE_READY: Int = 4
        }
    }

    @Suppress("unused")
    class SizeHolder(private var width: Int,private  var height: Int) {
        var scale: Float = 1f



        fun setSize(width: Int, height: Int) {
            this.width = width
            this.height = height
        }

        fun setWidth(width:Int){
            this.width = width
        }

        fun setHeight(height:Int){
            this.height = height
        }

        fun getWidth(): Int {
            return (scale * width).toInt()
        }

        fun getHeight(): Int {
            return (scale * height).toInt()
        }

        val isInvalidateSize: Boolean
            get() = scale > 0 && width > 0 && height > 0
    }


    var key: String? = null
        private set

    @JvmField
    var width: Int = 0

    @JvmField
    var height: Int = 0 // 和scale属性共同决定holder宽高，开发者设置，内部获取值然后进行相应的设置

    @JvmField
    @set:Suppress("unused")
    var scaleType: ScaleType? = null

    @JvmField
    @get:ImageState
    @ImageState
    var imageState: Int = 0 // 图片加载的状态
    private var autoFix = false
    var isAutoPlay: Boolean
    var isShow: Boolean

    @JvmField
    var isGif: Boolean = false

    @JvmField
    val borderHolder: DrawableBorderHolder?

    @JvmField
    var placeHolder: Drawable?

    @JvmField
    var errorImage: Drawable?
    private val prefixCode: String =
        if (config.imageDownloader == null) "" else config.imageDownloader.javaClass.name

    private val configHashcode = config.key()

    init {
        generateKey()
        this.isAutoPlay = config.autoPlay
        if (config.autoFix) {
            width = MATCH_PARENT
            height = WRAP_CONTENT
            scaleType = ScaleType.fit_auto
        } else {
            scaleType = config.scaleType
            width = config.width
            height = config.height
        }
        this.isShow = !config.noImage
        this.borderHolder = DrawableBorderHolder(config.borderHolder)

        this.placeHolder = config.placeHolderDrawableGetter.getDrawable(this, config, textView)
        this.errorImage = config.errorImageDrawableGetter.getDrawable(this, config, textView)
    }

    private fun generateKey() {
        this.key = MD5.generate(prefixCode + configHashcode + source)
    }

    fun setSource(source: String) {
        if (imageState != ImageState.INIT) {
            throw ResetImageSourceException()
        }
        this.source = source
        generateKey()
    }

    @Suppress("unused")
    fun success(): Boolean {
        return imageState == ImageState.READY
    }

    @Suppress("unused")
    fun failed(): Boolean {
        return imageState == ImageState.FAILED
    }

    fun setSize(width: Int, height: Int) {
        this.width = width
        this.height = height
    }

    fun getSource(): String {
        return source
    }



    fun isAutoFix(): Boolean {
        return autoFix
    }

    fun setAutoFix(autoFix: Boolean) {
        this.autoFix = autoFix
        if (autoFix) {
            width = MATCH_PARENT
            height = WRAP_CONTENT
            scaleType = ScaleType.fit_auto
        } else {
            width = WRAP_CONTENT
            height = WRAP_CONTENT
            scaleType = ScaleType.none
        }
        //        checkSize();
    }

    val isInvalidateSize: Boolean
        get() = width > 0 && height > 0

    fun setShowBorder(showBorder: Boolean) {
        borderHolder!!.setShowBorder(showBorder)
    }

    fun setBorderSize(borderSize: Float) {
        borderHolder!!.setBorderSize(borderSize)
    }

    fun setBorderColor(@ColorInt borderColor: Int) {
        borderHolder!!.setBorderColor(borderColor)
    }

    fun setBorderRadius(radius: Float) {
        borderHolder!!.setRadius(radius)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ImageHolder) return false

        val that = other

        if (position != that.position) return false
        if (width != that.width) return false
        if (height != that.height) return false
        if (scaleType != that.scaleType) return false
        if (imageState != that.imageState) return false
        if (autoFix != that.autoFix) return false
        if (isAutoPlay != that.isAutoPlay) return false
        if (isShow != that.isShow) return false
        if (isGif != that.isGif) return false
        if (prefixCode != that.prefixCode) return false
        if (source != that.source) return false
        if (key != that.key) return false
        if (borderHolder != that.borderHolder) return false
        if (if (placeHolder != null) (placeHolder != that.placeHolder) else that.placeHolder != null) return false
        return if (errorImage != null) (errorImage == that.errorImage) else that.errorImage == null
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + key.hashCode()
        result = 31 * result + position
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + scaleType.hashCode()
        result = 31 * result + imageState
        result = 31 * result + (if (autoFix) 1 else 0)
        result = 31 * result + (if (isAutoPlay) 1 else 0)
        result = 31 * result + (if (isShow) 1 else 0)
        result = 31 * result + (if (isGif) 1 else 0)
        result = 31 * result + (borderHolder?.hashCode() ?: 0)
        result = 31 * result + (if (placeHolder != null) placeHolder.hashCode() else 0)
        result = 31 * result + (if (errorImage != null) errorImage.hashCode() else 0)
        result = 31 * result + prefixCode.hashCode()
        return result
    }

    override fun toString(): String {
        return "ImageHolder{" +
                "source='" + source + '\'' +
                ", key='" + key + '\'' +
                ", position=" + position +
                ", width=" + width +
                ", height=" + height +
                ", scaleType=" + scaleType +
                ", imageState=" + imageState +
                ", autoFix=" + autoFix +
                ", autoPlay=" + isAutoPlay +
                ", show=" + isShow +
                ", isGif=" + isGif +
                ", borderHolder=" + borderHolder +
                ", placeHolder=" + placeHolder +
                ", errorImage=" + errorImage +
                ", prefixCode=" + prefixCode +
                '}'
    }

    companion object {
        const val WRAP_CONTENT: Int = Int.MIN_VALUE
        const val MATCH_PARENT: Int = Int.MAX_VALUE
    }
}
