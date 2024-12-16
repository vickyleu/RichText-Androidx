package org.uooc.composeapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class RichTextCompose(
    private var text: String,
    val clickImages: (images: List<String>, currentIndex: Int) -> Unit = { _, _ -> },
    val clickUrl: (url: String) -> Unit = {},
) {

    val content: String by lazy {
        HtmlFormater.replaceLatexHtmlContent(text)
    }

    val height: MutableState<Dp> = mutableStateOf(0.dp)
    val width: MutableState<Dp> = mutableStateOf(0.dp)
}
fun convertToLatex(input: String): String {
    return input
        .replace("\\\\", "\\")
}

fun convertImageSrc(input: String): String {
    val regex = """src="data:image/\w+?;base64,(.*?)"""".toRegex()
    return regex.replace(input) { matchResult ->
        val base64Data = matchResult.groupValues[1]
        """src="^data:image/png;base64,$base64Data"""".apply {
            println("convertImageSrc: $this")
        }
    }
}
