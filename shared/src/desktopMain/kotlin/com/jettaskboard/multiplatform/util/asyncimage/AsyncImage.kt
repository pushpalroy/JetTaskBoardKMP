package com.jettaskboard.multiplatform.util.asyncimage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.layout.ContentScale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.imageio.ImageIO

@Composable
actual fun AsyncImage(
    imageUrl: String?,
    loadingPlaceholder: @Composable BoxScope.() -> Unit,
    errorPlaceholder: @Composable BoxScope.() -> Unit,
    contentDescription: String?,
    modifier: Modifier,
    alignment: Alignment,
    contentScale: ContentScale,
    alpha: Float,
    colorFilter: ColorFilter?,
    filterQuality: FilterQuality,
) {
    val imageState by rememberImageState(imageUrl)

    Box(modifier = modifier) {
        when (val state = imageState) {
            ImageState.Error -> {
                errorPlaceholder()
            }

            ImageState.Loading -> {
                loadingPlaceholder()
            }

            is ImageState.Success -> {
                Image(
                    bitmap = state.imageBitmap,
                    contentDescription = contentDescription,
                    modifier = modifier,
                    alignment = alignment,
                    contentScale = contentScale,
                    alpha = alpha,
                    colorFilter = colorFilter,
                    filterQuality = filterQuality,
                )
            }
        }
    }
}

/**
 * Remembers the state of an image in the composition with [url]
 */
@Composable
private fun rememberImageState(url: String?): State<ImageState> {
    val initialState = if (url == null) ImageState.Error else ImageState.Loading
    return produceState(initialState, key1 = url) {
        if (url != null) {
            value = ImageState.Loading
            runCatching {
                value = ImageState.Success(ImageRepository.getImageBitmapByUrl(url))
            }.getOrElse {
                value = ImageState.Error
            }
        }
    }
}

/**
 * The single source of truth for images
 */
object ImageRepository {
    /**
     * Cache of images loaded in the current session.
     */
    private val inMemoryCache = mutableMapOf<String, ByteArray>()

    /**
     * Loads [ImageBitmap] from the specified [imageUrl].
     *
     * If image was already loaded with this [imageUrl] then it picks it from [inMemoryCache] otherwise
     * loads it from the network and stores it in the cache.
     */
    suspend fun getImageBitmapByUrl(imageUrl: String): ImageBitmap {
        val url = URL(imageUrl)
        val connection = withContext(Dispatchers.IO) { url.openConnection() } as HttpURLConnection
        withContext(Dispatchers.IO) { connection.connect() }

        val inputStream = connection.inputStream
        val bufferedImage = withContext(Dispatchers.IO) { ImageIO.read(inputStream) }

        val stream = ByteArrayOutputStream()
        withContext(Dispatchers.IO) { ImageIO.write(bufferedImage, "png", stream) }
        val byteArray = stream.toByteArray()

        return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
    }
}
