package com.jettaskboard.multiplatform.util.imageattachment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import com.jettaskboard.multiplatform.ui.screens.carddetails.IconCard
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage
import moe.tlaster.kfilepicker.PlatformFile

@OptIn(ExperimentalLayoutApi::class)
@Composable
actual fun ImageAttachmentView(modifier: Modifier) {
    val files = remember { mutableStateOf<List<PlatformFile>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()
    var filePath by remember { mutableStateOf("") }

    var showFilePicker by remember { mutableStateOf(false) }

    val fileTypes = listOf("jpg", "png")
    FilePicker(showFilePicker, initialDirectory = null, fileExtensions = fileTypes) { path ->
        showFilePicker = false
        filePath = path?.path.orEmpty()
    }

    Column {
        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Attachment,
            cardText = "Attachment",
            textPaddingValues = PaddingValues(8.dp),
            onCardClick = {
                showFilePicker = true
            }
        )

        if (filePath.isNotEmpty()) {
            FlowRow {
                Surface(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(80.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    AsyncImage(
                        imageUrl = filePath,
                        contentDescription = "$filePath image"
                    )
                }
            }
        }
    }
}