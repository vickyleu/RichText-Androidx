package zhou.demo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zzhoujay.richtext.ImageHolder
import com.zzhoujay.richtext.RichText
import com.zzhoujay.richtext.RichType
import com.zzhoujay.richtext.callback.ImageFixCallback
import com.zzhoujay.richtext.parser.external.MathTagHandler
import com.zzhoujay.richtext.parser.external.TableTagHandler
import kotlin.math.roundToInt

//import com.zzhoujay.okhttpimagedownloader.OkHttpImageDownloader;
class MainActivity : AppCompatActivity() {
    private val issue142 =
        "<p><img src=\"http://image.wangchao.net.cn/it/1233190350268.gif?size=528*388\" width=\"528\" height=\"388\" /></p>"
    private val issue143 =
        "<img src=\"file:///C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\ksohtml\\wpsB8DD.tmp.png\">"
    private val issue147 = """<div class="pictureBox"> 
 <img src="http://static.storeer.com/wlwb/productDetail/234be0ec-e90a-4eda-90bd-98d64b55280a_580x4339.jpeg">
</div>"""
    private val issue149: String? = null
    private val issue150 =
        "<img src='http://cuncxforum-10008003.image.myqcloud.com/642def77-373f-434f-8e68-42dedbd9f880'/><br><img src='http://cuncxforum-10008003.image.myqcloud.com/bf153d9f-e8c3-4dcc-a23e-bfe0358cb429'/>"
    var loading: Int = 0
    var failure: Int = 0
    var ready: Int = 0
    var init: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RichText.initCacheDir(this)
        RichText.debugMode = true

        val textView = findViewById<TextView>(R.id.text)
        //        textView.setBackgroundColor(Color.GRAY);
        val test_text_2 =
            "<B>Start</B> <img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' />" +
                    "<img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' /><img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' />" +
                    "<img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' /><img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' />" +
                    "<img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' /><img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' />" +
                    "<img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' /><img src='http://wx1.sinaimg.cn/mw690/eaaf2affly1fihvjpekzwj21el0qotfq.jpg' /><B>End</B>"

        //        "<p><img src=\"http://assets.uooconline.com/upload/2022/01/09/c75ece38fb59e73c.png\" title=\"image.png\" alt=\"image.png\"/></p>"+
        /*val text = """
                <p><img src="http://assets.uooconline.com/upload/2022/01/09/c75ece38fb59e73c.png" title="image.png" alt="image.png"/></p>
                <p>&nbsp;爸爸的爸爸叫什么？
                使用textView实现的html table标签和latex公式
                是是是试试看多行高度计算是否正常
                不信你看
                不信你看啊
                <table>
                                <tr>
                                    <th>Header 1</th>
                                    <th>Header 2</th>
                                </tr>
                                <tr>
                                    <td>Cell 1</td>
                                    <td>Cell 2</td>
                                </tr>
                                <tr>
                                    <td>?? 我这个可是超长的文本框哦,一换几十行啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊孤寡孤寡孤寡咕咕咕咕咕咕~神恶魔鬼东西哦math-tex完犊子没有嘿嘿额黑
                                    h哈哈哈😄收到货就是多好多好</td>
                                    <td>Cell 4</td>
                                </tr>
                                <tr>
                                    <td>Cell 3</td>
                                    <td>Cell 4</td>
                                </tr>
                </table>
                不信你看啊
                <p>我就不信了</p>
                <span class="math-tex">\(\frac {-b\pm \sqrt {{b}^{2}-4ac}} {2a}>\frac {dy} {dx}\)</span><p>你们好啊</p></p>
                
                """
            .trimIndent().trim { it <= ' ' }*/

        val text = """
            你好啊
            <p><img class="kfformula" src="" data-latex="\geq \mp \cong \equiv \propto \approx \forall \partial \surd \cup \cap \varnothing "/></p><p><img class="kfformula" src="" data-latex="\pm \infty =\sim \times \div !<\ll >\gg \leq "/></p>
            <p><img class="kfformula" src="" data-latex="\% \circ \exists \nexists \in \ni \gets \uparrow \to \to \downarrow \leftrightarrow \therefore \because +-\neg \ast \cdot \vdots \aleph \beth \blacksquare"/></p>
            <p><img class="kfformula" src="" data-latex="\frac {1} {2}{3/4}\frac {d5y} {d6x}\frac {\Delta 7y} {\Delta 8x}\frac {\delta y9} {\delta x10}\frac {\pi } {2}11"/></p>
            <p><img class="kfformula" src="" data-latex="{1}^{2}{3}_{4}{5}^{6}_{7}{^{8}_{9}10}"/></p>
            <p><img class="kfformula" src="" data-latex="\sqrt {4}\sqrt[{5}] {6}\sqrt[{2}] {7}\sqrt[{3}] {8}\sqrt[{35}] {9}"/></p>
            <p><img class="kfformula" src="nnanana" data-latex="\int  {5\int ^{7}_{9} {8}}\iint  {10}\iint ^{11}_{13} {12}\iiint  {14}\iiint ^{15}_{17} {16}"/></p>
            <p><img class="kfformula" src="" data-latex="\sum  {1}\sum ^{4}_{\placeholder } {5}\sum _{4} {5}"/></p>
            <p><img class="kfformula" src="" data-latex="\left ( {45} \right )\left [ {\propto } \right ]\left | {\downarrow } \right |\varnothing "/></p>
            <p><img class="kfformula" src="" data-latex="\sin {20}°\cos {25}，\tan {63}，\csc {56}、\sec {75}\left \{  {/\cot {20}0} \right \} \sin {\theta }\cos {2x}\tan {\theta }=\frac {\sin {\theta }} {\cos {\theta }}"/></p>
        """.trimIndent()

        val imageFix = object : ImageFixCallback {
            fun convertImageSrc(input: String): String {
                val regex = """src="data:image/\w+?;base64,(.*?)"""".toRegex()
                return regex.replace(input) { matchResult ->
                    val base64Data = matchResult.groupValues[1]
                    """src="^data:image/png;base64,$base64Data""""
                }
            }
            override fun onInit(imageHolder: ImageHolder?) {
                imageHolder?.apply {
                    this.source = convertImageSrc(this.source)
                }
            }

            override fun onLoading(imageHolder: ImageHolder?) {
                /*imageHolder?.apply {
                    this.placeHolder =
                        this@MainActivity.resources.getDrawable(R.drawable.stat_sys_download)
                }*/
            }

            override fun onSizeReady(
                imageHolder: ImageHolder?,
                width: Int,
                height: Int,
                sizeHolder: ImageHolder.SizeHolder?
            ) {
                    println("标记高度变化: onSizeReady:$width,$height")
                imageHolder?.apply {
                    if (sizeHolder != null) {
                        val max = textView.measuredWidth

                        val originalWidth = sizeHolder.width.toFloat()
                        val originalHeight = sizeHolder.height.toFloat()
                        println("max:::${max}  originalWidth::$originalWidth  originalHeight::$originalHeight")
                        if (originalWidth > max) {
                            // 计算等比例缩小后的高度
                            val newHeight = (originalHeight * max) / originalWidth
                            this.width = max
                            this.height = newHeight.roundToInt()
                        } else {
                            this.width = originalWidth.roundToInt()
                            this.height = originalHeight.roundToInt()
                        }
                    }
                }
            }

            override fun onImageReady(imageHolder: ImageHolder?, width: Int, height: Int) {

            }

            override fun onFailure(imageHolder: ImageHolder?, exception: java.lang.Exception?) {
            }
        }
        RichText.from(text)
            .resetSize(true)
            .sync(false)
            .type(RichType.html)
            .customTagParser(MathTagHandler(this, 16f))
            .customTagParser(
                TableTagHandler(
                    this,
                    15f
                ) { textView.measuredWidth }
            )
            .urlClick { url: String ->
                if (url.startsWith("code://")) {
                    Toast.makeText(
                        this@MainActivity,
                        url.replaceFirst("code://".toRegex(), ""),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@urlClick true
                }
                false
            }
            .fix(imageFix)
            .into(textView)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, 0, 0, "RecyclerView")
        menu.add(0, 1, 1, "ListView")
        menu.add(0, 2, 2, "Gif")
        menu.add(0, 3, 3, "Test")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == 0) {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        } else if (item.itemId == 1) {
            startActivity(Intent(this, ListViewActivity::class.java))
        } else if (item.itemId == 2) {
            startActivity(Intent(this, GifActivity::class.java))
        } else if (item.itemId == 3) {
            startActivity(Intent(this, TestActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        RichText.recycle()
    }

    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    companion object {
        private const val IMAGE =
            "<img title=\"\" src=\"http://g.hiphotos.baidu.com/image/pic/item/241f95cad1c8a7866f726fe06309c93d71cf5087.jpg\"  style=\"cursor: pointer;\"><br><br>" +
                    "<img src=\"http://img.ugirls.com/uploads/cooperate/baidu/20160519menghuli.jpg\" width=\"1080\" height=\"1620\"/><a href=\"http://www.baidu.com\">baidu</a>" +
                    "hello asdkjfgsduk <a href=\"http://www.jd.com\">jd</a>"
        private const val IMAGE1 =
            "<h1>RichText</h1><p>Android平台下的富文本解析器</p><img title=\"\" src=\"http://g.hiphotos.baidu.com/image/pic/item/241f95cad1c8a7866f726fe06309c93d71cf5087.jpg\"  style=\"cursor: pointer;\"><br><br>" +
                    "<h3>点击菜单查看更多Demo</h3><img src=\"http://ww2.sinaimg.cn/bmiddle/813a1fc7jw1ee4xpejq4lj20g00o0gnu.jpg\" /><p><a href=\"http://www.baidu.com\">baidu</a>" +
                    "hello asdkjfgsduk <a href=\"http://www.jd.com\">jd</a></p><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>bottom"


        private const val GIF_TEST =
            "<img src=\"http://ww4.sinaimg.cn/large/5cfc088ejw1f3jcujb6d6g20ap08mb2c.gif\">"

        private const val markdown_test =
            "image:![image](http://image.tianjimedia.com/uploadImages/2015/129/56/J63MI042Z4P8.jpg)\n[link](https://github.com/zzhoujay/RichText/issues)"

        private const val gif_test =
            "<h3>Test1</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/1.gif\" />" +
                    "            <h3>Test2</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/2.gif\" />" +
                    "            <h3>Test3</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/3.gif\" />" +
                    "            <h3>Test4</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/4.gif\" />" +
                    "            <h3>Test5</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/5.gif\" />" +
                    "            <h3>Test6</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/6.gif\" />" +
                    "            <h3>Test7</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/7.gif\" />" +
                    "            <h3>Test8</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/8.gif\" />" +
                    "            <h3>Test9</h3><img src=\"http://www.aikf.com/ask/resources/images/facialExpression/qq/9.gif\" />"

        private const val list_test = "<ol>\n" +
                "   <li>Coffee</li>\n" +
                "   <li>Tea</li>\n" +
                "   <li>Milk</li>\n" +
                "</ol>\n" +
                "\n" +
                "<ul>\n" +
                "   <li>Coffee</li>\n" +
                "   <li>Tea</li>\n" +
                "   <li>Milk</li>\n" +
                "</ul>"

        private const val large_image =
            "<img src=\"http://static.tme.im/article_1_1471686391302fue\"/>"

        private const val text = ""
        private const val TAG = "MainActivityTest"
        private const val assets_image_test =
            "<h1>Assets Image Test</h1><img src=\"file:///android_asset/doge.jpg\">"
        private const val html =
            "<article class=\"markdown-body entry-content\" itemprop=\"text\"><h1><a href=\"#richtext\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-richtext\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>RichText</h1>\n" +
                    "<blockquote>\n" +
                    "<p style=\"background-color:rgba(255,0,0,1);\">Android平台下的富文本解析器</p>\n" +
                    "</blockquote>\n" +
                    "<ul>\n" +
                    "<p><img src=\"http://assets.uooconline.com/upload/2022/01/09/c75ece38fb59e73c.png\" title=\"image.png\" alt=\"image.png\"/></p>" +
                    "<li>流式操作</li>\n" +
                    "<li>低侵入性</li>\n" +
                    "<li>依赖少，只依赖了<code>disklrucache</code>和<code>support v4</code></li>\n" +
                    "<li>支持Html和Markdown格式文本</li>\n" +
                    "<li>支持图片点击和长按事件</li>\n" +
                    "<li>链接点击事件和长按事件</li>\n" +
                    "<li>支持设置加载中和加载错误时的图片</li>\n" +
                    "<li>支持自定义超链接的点击回调</li>\n" +
                    "<li>支持修正图片宽高</li>\n" +
                    "<li>支持GIF图片</li>\n" +
                    "<li>支持Base64编码、本地图片和Assets目录图片</li>\n" +
                    "<li>自持自定义图片加载器、图片加载器</li>\n" +
                    "<li>支持内存和磁盘双缓存</li>\n" +
                    "</ul>\n" +
                    "<h3><a href=\"#效果\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-效果\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>效果</h3>\n" +
                    "<p><a href=\"/zzhoujay/RichText/blob/master/image/image.jpg\" target=\"_blank\"><img src=\"/zzhoujay/RichText/raw/master/image/image.jpg\" alt=\"演示\" title=\"演示\" style=\"max-width:100%;\"></a></p>\n" +
                    "<h3><a href=\"#gradle中引用的方法\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-gradle中引用的方法\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>gradle中引用的方法</h3>\n" +
                    "<pre><code>implementation 'com.zzhoujay.richtext:richtext:3.0.5'\n" +
                    "</code></pre>\n" +
                    "<h3><a href=\"#关于issue\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-关于issue\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>关于issue</h3>\n" +
                    "<p style=\"text-indent:50px;\">最近一段时间会比较忙，issue不能及时处理，一般会定时抽空集中解决issue，但时间有限解决速度上不敢保证。</p>\n" +
                    "<p>欢迎提交pull request帮助完善这个项目</p>\n" +
                    "<h3><a href=\"#注意\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-注意\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>注意</h3>\n" +
                    "<p>在第一次调用RichText之前先调用<code>RichText.initCacheDir()</code>方法设置缓存目录</p>\n" +
                    "<p>ImageFixCallback的回调方法不一定是在主线程回调，注意不要进行UI操作</p>\n" +
                    "<p>本地图片由根路径<code>\\</code>开头，Assets目录图片由<code>file:///android_asset/</code>开头</p>\n" +
                    "<p>Gif图片播放不支持硬件加速，若要使用Gif图片请先关闭TextView的硬件加速</p>\n" +
                    "<pre><code>textView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);\n" +
                    "</code></pre>\n" +
                    "<h3><a href=\"#使用方式\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-使用方式\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>使用方式</h3>\n" +
                    "<p><a href=\"https://github.com/zzhoujay/RichText/wiki\">多看wiki</a>、<a href=\"https://github.com/zzhoujay/RichText/wiki\">多看wiki</a>、<a href=\"https://github.com/zzhoujay/RichText/wiki\">多看wiki</a>，重要的事情说三遍</p>\n" +
                    "<h3><a href=\"#后续计划\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-后续计划\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>后续计划</h3>\n" +
                    "<ul>\n" +
                    "<li><del>添加自定义标签的支持</del> (已添加对少部分自定义标签的支持)</li>\n" +
                    "</ul>\n" +
                    "<h3><a href=\"#关于markdown\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-关于markdown\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>关于Markdown</h3>\n" +
                    "<p>Markdown源于子项目：<a href=\"https://github.com/zzhoujay/Markdown\">Markdown</a></p>\n" +
                    "<p>若在markdown解析过程中发现什么问题可以在该项目中反馈</p>\n" +
                    "<h3><a href=\"#富文本编辑器\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-富文本编辑器\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>富文本编辑器</h3>\n" +
                    "<p>编辑功能目前正在开发中，<a href=\"https://github.com/zzhoujay/RichEditor\">RichEditor</a></p>\n" +
                    "<h3><a href=\"#具体使用请查看demo\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-具体使用请查看demo\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>具体使用请查看demo</h3>\n" +
                    "<p><a href=\"https://github.com/zzhoujay/RichText/blob/master/app/src/main/java/zhou/demo/ListViewActivity.java\">ListView Demo</a>、\n" +
                    "<a href=\"https://github.com/zzhoujay/RichText/blob/master/app/src/main/java/zhou/demo/RecyclerViewActivity.java\">RecyclerView Demo</a>、\n" +
                    "<a href=\"https://github.com/zzhoujay/RichText/blob/master/app/src/main/java/zhou/demo/GifActivity.java\">Gif Demo</a></p>\n" +
                    "<h3><a href=\"#license\" aria-hidden=\"true\" class=\"anchor\" id=\"user-content-license\"><svg aria-hidden=\"true\" class=\"octicon octicon-link\" height=\"16\" version=\"1.1\" viewBox=\"0 0 16 16\" width=\"16\"><path fill-rule=\"evenodd\" d=\"M4 9h1v1H4c-1.5 0-3-1.69-3-3.5S2.55 3 4 3h4c1.45 0 3 1.69 3 3.5 0 1.41-.91 2.72-2 3.25V8.59c.58-.45 1-1.27 1-2.09C10 5.22 8.98 4 8 4H4c-.98 0-2 1.22-2 2.5S3 9 4 9zm9-3h-1v1h1c1 0 2 1.22 2 2.5S13.98 12 13 12H9c-.98 0-2-1.22-2-2.5 0-.83.42-1.64 1-2.09V6.25c-1.09.53-2 1.84-2 3.25C6 11.31 7.55 13 9 13h4c1.45 0 3-1.69 3-3.5S14.5 6 13 6z\"></path></svg></a>License</h3>\n" +
                    "<pre><code>The MIT License (MIT)\n" +
                    "\n" +
                    "Copyright (c) 2016 zzhoujay\n" +
                    "\n" +
                    "Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
                    "of this software and associated documentation files (the \"Software\"), to deal\n" +
                    "in the Software without restriction, including without limitation the rights\n" +
                    "to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
                    "copies of the Software, and to permit persons to whom the Software is\n" +
                    "furnished to do so, subject to the following conditions:\n" +
                    "\n" +
                    "The above copyright notice and this permission notice shall be included in all\n" +
                    "copies or substantial portions of the Software.\n" +
                    "\n" +
                    "THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
                    "\n" +
                    "IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
                    "FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
                    "AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
                    "LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
                    "OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE\n" +
                    "SOFTWARE.\n" +
                    "</code></pre>\n" +
                    "<p><em>by zzhoujay</em></p>\n" +
                    "</article>"
    }
}
