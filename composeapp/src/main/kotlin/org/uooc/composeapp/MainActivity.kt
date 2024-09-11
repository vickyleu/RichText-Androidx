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
                                            """<p><a href="http://www.baidu.com" target="_self">百度文本<img class="kfformula" src="data:image/png;base64,ivborw0kggoaaaansuheugaaab8aaaancayaaad3h5p5aaaaaxnsr0iars4c6qaaaaljrefuweft171rveeuxueniktqqkyheaudxkqlm1gpol3kfbywgqcqiisiojw9sbqx8amrsrclvaer/q9etbc72imdqqqejigf98aewhbunb27swh7m1vcm/ob95w5m+8d0sdnqi9sa3hfst+nti/ghn7iz46kpvayfxmncrvbjefn8ayrpeh7mzeujpwgx8yphes1l5b245jby1wpcd+hbszjk66whf/eoqspkhefn8lhps11cafxhf9snmp1xnm+l658v9uwzujgpxoud/ghontwa8lmckvvtvr343i23hqm+l27+bz4vlvjwr6vtivpgbebk/t7p/dzuiz76xkjs+ygnub33vu0gb/eonaq92s8wgzdcmq1gr/fug6snfa/hufdp2mhg/iipzmz6fr5tn0fpmiv3erm8wkjecsjfrnyxafx8a3uajprhmmopj2921ytacehw3nfs/gjvmj9vemgycpivqh6fh6fegkpy3yncjnr3++pxcnkrervyqux8jg77pjsunke8csutra2kwhyynqv98+4tjzcv8ad5v1ly+5efa35x/u6rcjfnebcaaaaaelftksuqmcc" data-latex="\frac {4} {5}"/><img src="http://assets-beta.uooconline.com/upload/2024/09/11/fc59c016a5919e61.png" title="snipaste_2023-02-21_10-15-10.png" alt="snipaste_2023-02-21_10-15-10.png"/></a><br/></p>
                                            """.trimIndent(),
                                            """<p><img class="kfformula" src="data:image/png;base64,ivborw0kggoaaaansuheugaaadyaaaancayaaabez7vjaaaaaxnsr0iars4c6qaaaoljrefuaept2euotvecx/hprr4jkqmlj5jeqjexjkqppqgreswpzeauieqierqte0xlymbaky+ukissiqedjvlky/9r3tpd173rnhu5++zortopc9baa33x7/9cbsr6tfwuswus2zrtkfyffruavricx5ijg3hfzx7kqnhidme1blixe7atx3phygi2dxoxdv+seruwam+agwwg4hnq8qtuhgwuvpvuzgc1ex+nuzihu7lqma6mphj7gofpch97ibv4wqwwdoy4+b0peozbt1y4sipwu//pkukux/1mbruhkwnkqokyjos4kr6z2mqm2mjcl/bjnn4mgvlfldianuxof51fvdlgmrzhdpgzvmmuqr0nuvbljor1mhq6tmym2gog2nda6msgpxjne6bidvkno93knovpwqgi/+xpvlgowhdjhs7iozyudr8wz4qs6haxbwuusfgp14itqjpwvnfjgwwlvuiktieodn+dncai30rv3reaj6r9bm7jcc3mznw30q7/187tvr+lu/+my528nfjo/p4iwx1my/phzveilckwnl9onvzhjsg2gidsin36l6um4szgdzdvmlmncd/mefox1cbcomz8ha85e3ospaiszoxs4wpvulhsaeqq4xftweuohqtoogefydrhsagexqzsesz88rhmffcrqwiwlrimuwittuequ3sus2lho/nscxmmgng36cei8cd9x1umsx10jcbcqqhqnwu4sb1xndd0yhhhebkuenh0y5ubg5xuotptwzgif21n1cxlpmopndzrsiloew9vjammyruhmivvhxedhb30vcjmx+wewnkvy+x4y1wlrogj66ojlcx66oabxrayiv0gsjjokn4pc9oaaaaasuvork5cyii=" data-latex="\sim \frac {3} {4}"/></p>
                                        """.trimIndent(),
                                            """<p><a href=\"http://www.baidu.com\" target=\"_self\">百度文本</a><br/></p>""".trimIndent()
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
                        ImageMessage(content as ImageCache, maxWidth) // 显示图片消息
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
            Coil(
                data = imageCache.data,
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