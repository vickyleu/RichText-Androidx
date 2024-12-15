package org.uooc.composeapp

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEach
import coil3.compose.AsyncImage
import com.fleeksoft.ksoup.Ksoup
import org.uooc.composeapp.ui.theme.RichTextComposeTheme
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalEncodingApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RichTextComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(top = 50.dp)
                            .fillMaxSize()
                            .background(Color(0xFFF7F7F7)),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Column(
                            Modifier.fillMaxSize()
                        ) {
                            with(LocalDensity.current) {

                                val reverseMessageList = remember {
                                    mutableStateListOf(
                                        *listOf(
//                                            "<p><img class=\"kfformula\" src=\"data:image\\/png;base64,ivborw0kggoaaaansuheugaaaimaaaaecayaaaapza37aaaaaxnsr0iars4c6qaabwvjrefuaeptmnxibuuuxx\\/pdlrmeferfqnbebtbebs7sbu7uxxs7vxdlfqs7ebsuexolawsfrjhroctc+53l\\/d9cgceh7w7z2bonjvrr7xnjghyhhgiciwzrmiygrsbirigwpg3akmwdmhqmzbmcmwcrav8atwcpdyg+e4ebaysd\\/wg3ag8cvw6opu47btavrgm14crgvd6uj4zgw2bbybxgeubn4c\\/upljjmwwb3az8a5wipbznwvowzo+0ylaecbtwghat32yp2nizyclgtuae4efmh4ywe8talsaxwh7ajd1a4huwtaucbswa7ao8ezfi6st4d9vgtmbb2kj3uvy5v2z834fl102jh32ay4gvgce73kubh+tmc8h5gi2bd7tdqawz00zh3niyevgqxbp\\/to1wza44vnaysdgwjsle88cm2y\\/awabb9gl2bnyhhi+7yijwdcapwhx1tzv+nfgianrj7bsl2aurwo+cdgf+ldimak71uy6vs4ceytdcvakdvx+gsgtuwac0jc7vie73aw8abwc+t03cl7wscb2wc49asmrmbkum9nmj+cce1ycasdocwb3wbgitdxpgsd7wnpafcbdox0maw4nlj21snixggxbx8z5mnyz1aymou3q3g\\/4jwchoo\\/gmiwc1itmgdqy3gapxyrbngpwu+oearbjnbvworb9aa\\/xnng2hw+sbkpvhiushoknvydmthmadyh7gxh+d2bqw3jcxw9afdfy2jhadqymsendjp5cerrrb4ykpjckkfnxjhlgc1q+hva5yyyjhqxjcmt2aqjpk2kxcihrgvrtzvod9wdw5c2hh4qaeyetchzbdpggubviawa0g2f64mpghgdlymg8vgwobu4om6x+kenk+g+cgftzfp3ertny+cj12fd06y7awc1tqyjrq2a9ibcmuhwlt\\/rvojfrt83wezcggqdydgjax23gl4uftq3gde9wx0+g89pow0rtrbebrbczgcvrhf7t+evwteiftxeprcgpjlbkjlysqdofhqgvy5gdz7m+henuvbq\\/5ijhducauoqwxaoqkf0awwphawx+mcafjcfqwc9dqvumqe23lgpkskkyms8suso8bzcfrm0au2qr1hyminyh4atgo6juta08kgtdpncdshxvwcqql3ru1hlbyca8fdpabaub8mljdhojbrwnqjdw74m\\/vzxhmjak6zhrakgoqzra4pwehsgk2lytql+ugqqngcy2ujg1app92egf29bbtuwgs09dvx1uae8oxf6g0tzgelzspivpwei+ej+ddrzxyjgnqh0v4e7giuclmqmpua+pu2y10hqozquemuvoxvkeskxzmkhtgtfvahyl53vx7n1rgzckyusfnys6ovhsiy6ejsjjmy34sgqjunyuyvb3fwsb1xobzxvrxvp8f5t8y6gfqzhbdpoewhmv9xqpeargmmx6vmxlitmbwbgqnowcacky9e5xwvagiu4b+ufooidi7lk02yq4nspdf3kpjnckwqqvmihvwau1b+wk9ksd8nriuwam8+w0lwlju9\\/nvueum8giuruizdxdxwclbhbkqiarm+yglfcnqyymqalmhb5nwspmy1ljls0vjh+kzmlzafp8mjua7b1mssxm2lxhvqgsen0wg4m12rjbsn9uzypkwor7lev\\/e7rklhydgnpbkhs3owctqsavkfy4hmo1wlc0wjfpqlpoodrxrdoai3tdi8hyxwaqs7svqxjgza1pt2pzib1gy6esdjktw2lckw4oe10lheuhnggtky2lfuwplf5hb9k09qzpvrcxvqncnrz1gaucurtgfwbw543unh\\/yvayasrmyoq3jkccykc5mp36dizmyk12bh8rueedbwrs3jwdpave6x8yqjp0p1o10kv8nqh9hkar9gca1rommhqwyjedi0ypajl48pqzrj5ik3bhfurhlmkn6zlh407prrmh04xqs+gnazkvzbhqtgbzbw09tipeqhh6gohaz0ct7kxjvrc57d8zhomm9ghjzx2c42d7wqsnqtnzumg3bkdtutr8mmosm0bbp\\/wym6cxxdrfzxgxtgzmeq8ui9ttntfmojgde9jvknuxqqjmgyggt\\/rf86ssirjorcjcauqm45ft\\/6a4wthrnt4tl5du2y+tuvmxat+jip+14bfppapwtwlhqduqjnuo06au+ufzq1srsfuny3tkda6t0vuutch6c0xtpulcyc6xq2xpa1vvu48t0dldoywag+ugsdugqijeuiud4tnnhzr45hcdgsuccbgd3e9ep4tfboxoh\\/an8yvhwbkt6xkwxeh3qojvy8kghpgvwfrufn8rrtnq9fgazke\\/l+xs\\/4bcdisbimwfq6x7o24ci\\/a1\\/wooutixavaaaaabjru5erkjggg==\" data-latex=\"\\left \\{  {3,4} \\right \\} \\cup \\left \\{  {\\varnothing } \\right \\} \"\\/><\\/p>".trimIndent(),
                                            """<p><img src=\"http:\/\/assets.uooconline.com\/upload\/2022\/01\/09\/c75ece38fb59e73c.png\" title=\"image.png\" alt=\"image.png\"\/><\/p>""".trimIndent()
                                        ).toTypedArray()
                                    )
                                }


                                LazyColumn(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                    reverseLayout = true,
                                    verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top)
                                ) {
                                    item {
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }

                                    itemsIndexed(reverseMessageList, { index, item ->
                                        item
                                    }) { index, item ->
                                        val kSoup = remember(index) {
                                            Ksoup.parse(
                                                item
                                                    .replace("p+style", "p style")
                                                    .replace("img+style", "img style")
                                                    .replace("a+style", "a style")
                                                    .replace("\"+src=\"", "\" src=\"")
                                                    .replace("\"+href=\"", "\" href=\"")
                                                    .replace("\"+title=\"", "\" title=\"")
                                            )
                                        }
                                        val (textPair, images) = remember(index) {
                                            kSoup.let {
                                                val response = it
                                                var forceShowingText = false
                                                val images =
                                                    (response.select("img").mapNotNull { node ->
                                                        if (node.hasAttr("src")) {
                                                            node.attr("src").let {
                                                                val clazz = node.attr("class").let {
                                                                    if(it.isNotEmpty()){
                                                                        it.trim()
                                                                            .replace("\"","")
                                                                    }else null
                                                                }
                                                                if (clazz!=null && clazz == "kfformula" && node.hasAttr("data-latex")) {
                                                                    forceShowingText = true
                                                                    return@mapNotNull null
                                                                }
                                                                else if (it.startsWith("data:image")) {
                                                                    return@mapNotNull Base64.decode(
                                                                        it
                                                                    ).let {
                                                                        val bitmap =
                                                                            BitmapFactory.decodeByteArray(
                                                                                it,
                                                                                0,
                                                                                it.size
                                                                            )
                                                                        val width =
                                                                            bitmap.width.toDp()
                                                                        val height =
                                                                            bitmap.height.toDp()
                                                                        node.remove()
                                                                        ImageCache(
                                                                            bitmap,
                                                                            width.value,
                                                                            height.value
                                                                        )
                                                                    }
                                                                }
                                                                else if (it.isNotEmpty()) {
                                                                    val width =
                                                                        node.hasAttr("width").let {
                                                                            if (it) node.attr("width")
                                                                                .toFloat().dp else 100.dp
                                                                        }
                                                                    val height =
                                                                        node.hasAttr("height").let {
                                                                            if (it) node.attr("height")
                                                                                .toFloat().dp else 100.dp
                                                                        }
                                                                    node.remove()
                                                                    ImageCache(
                                                                        it,
                                                                        width.value,
                                                                        height.value
                                                                    )
                                                                } else null
                                                            }
                                                        } else null
                                                    })
                                                (response.outerHtml() to forceShowingText) to mutableStateListOf(
                                                    *images.toTypedArray()
                                                )
                                            }
                                        }

                                        val (text, forceShowingText) = textPair

                                        if (forceShowingText || kSoup.text().isNotEmpty()) {
                                            if (images.isNotEmpty()) {
                                                Column(
                                                    modifier = Modifier
                                                        .fillMaxWidth(),
                                                    verticalArrangement = Arrangement.spacedBy(
                                                        30.dp,
                                                        Alignment.Top
                                                    )
                                                ) {
                                                    ChatMessageItem(
                                                        index = index,
                                                        content = text,
                                                        isImage = false,
                                                    )
                                                    images.fastForEach {
                                                        ChatMessageItem(
                                                            index = index,
                                                            content = it,
                                                            isImage = true,
                                                        )
                                                    }
                                                }
                                            } else {
                                                ChatMessageItem(
                                                    index = index,
                                                    content = text,
                                                    isImage = false,
                                                )
                                            }
                                        } else {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth(),
                                                verticalArrangement = Arrangement.spacedBy(
                                                    30.dp,
                                                    Alignment.Top
                                                )
                                            ) {
                                                images.fastForEach {
                                                    ChatMessageItem(
                                                        index = index,
                                                        content = it,
                                                        isImage = true,
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    item {
                                        Spacer(modifier = Modifier.height(20.dp))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ChatMessageItem(
        index: Int, // 头像 URL
        content: Any, // 消息内容
        isImage: Boolean, // 是否为图片消息
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize()
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.Top
        ) {
            Spacer(modifier = Modifier.width(66.dp))
            BoxWithConstraints(
                modifier = Modifier
                    .weight(1f)
                    .wrapContentHeight()
            ) {
                val maxWidth = maxWidth
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.End
                ) {
                    if (isImage) {
                        ImageMessage((content as ImageCache).apply {
                            println("width:$width,height:$height  this:$this")
                        }, maxWidth) // 显示图片消息
                    } else {
                        TextMessage(content as String) // 显示文本消息
                    }
                }
            }
            Spacer(modifier = Modifier.width(66.dp))
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    @Composable
    fun ImageMessage(content: ImageCache, maxWidth: Dp) {
        with(LocalDensity.current) {
            val imageCache = remember(content) {
                val src = content.data as String
                val width = content.width.dp
                val height = content.height.dp
                if (width <= 0.dp || height <= 0.dp) {
                    return@remember ImageCache(src, 100.dp.value, 100.dp.value)
                }
                if (width > maxWidth) {
                    val scale = maxWidth / width
                    ImageCache(src, maxWidth.value, (height.value * scale))
                } else {
                    ImageCache(src, width.value, height.value)
                }
            }
            var src = (imageCache.data as String)
            src=src.replace("\\\"","")
            Coil(
                data = src,
                modifier = Modifier
                    .clickable {

                    }
                    .size(imageCache.width.dp, imageCache.height.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.White, shape = RoundedCornerShape(6.dp))
            )
        }

    }

    @Composable
    fun Coil(
        modifier: Modifier, data: Any,
        contentScale: ContentScale = ContentScale.Crop,
        placeHolder: Painter? = ColorPainter(Color.Black),
        error: Painter? = ColorPainter(Color.Red),
        colorFilter: ColorFilter? = null,
        contentDescription: String = "${data.hashCode()}"
    ) {
        AsyncImage(
            model = data,
            contentDescription = contentDescription,
            placeholder = placeHolder,
            contentScale = contentScale,
            error = error,
            colorFilter = colorFilter,
            modifier = modifier
        )
    }

    @Composable
    fun TextMessage(content: String) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .let {
                    it.background(Color(0xFFDFEDFF), shape = RoundedCornerShape(6.dp))
                }
                .padding(10.dp),
        ) {
            val state = remember {
                mutableStateOf(RichTextCompose(content, clickImages = { _, _ -> }) { url ->

                })
            }
            RichTextPlatformView(
                state = state, modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .requiredHeightIn(min = 10.dp),
                style = TextStyle(fontSize = 14.sp, color = Color.Black)
            )
        }
    }

}

data class ImageCache(
    val data: Any,
    val width: Float,
    val height: Float
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}