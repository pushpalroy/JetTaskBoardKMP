@file:Suppress("UNUSED_EXPRESSION")

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.screens.carddetails.IconCard
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage
import kotlinx.coroutines.launch
import moe.tlaster.kfilepicker.FilePicker
import moe.tlaster.kfilepicker.PlatformFile

@OptIn(ExperimentalLayoutApi::class)
@Composable
actual fun ImageAttachmentView(modifier: Modifier) {
    val files = remember { mutableStateOf<List<PlatformFile>>(emptyList()) }
    val coroutineScope = rememberCoroutineScope()


    Column {
        IconCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            leadingIcon = Icons.Default.Attachment,
            cardText = "Attachment",
            textPaddingValues = PaddingValues(8.dp),
            onCardClick = {
                coroutineScope.launch {
                    files.value = FilePicker.pickFiles(
                        allowedExtensions = listOf(".jpg", ".png"),
                        allowMultiple = true,
                    )
                }
            }
        )

        FlowRow {
            if (files.value.isNotEmpty()) {
                files.value.forEach {
                    Surface(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(80.dp),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        AsyncImage(
                            imageUrl = it.path,
                            contentDescription = "${it.name} image"
                        )
                    }
                }
            }
        }
    }
}