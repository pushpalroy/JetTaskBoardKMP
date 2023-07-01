package com.jettaskboard.multiplatform.util.imageattachment

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.screens.carddetails.ItemRow

@OptIn(ExperimentalLayoutApi::class)
@RequiresApi(Build.VERSION_CODES.P)
@Composable
actual fun ImageAttachmentView(modifier: Modifier) {
    val imageAttachmentList = remember { mutableStateListOf<Uri>() }
    val context = LocalContext.current

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { imagesUri ->
            if (imagesUri != null) {
                Log.d("PhotoPicker", "Selected URI: $imagesUri")
                imageAttachmentList.addAll(imagesUri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    ItemRow(
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(16.dp),
                imageVector = Icons.Default.Attachment,
                contentDescription = "Leading Icon"
            )
        },
        text = "ATTACHMENTS",
        trailingIcon = Icons.Default.Add,
        onClick = {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
    )

    FlowRow(
        modifier = Modifier.padding(16.dp)
    ) {
        imageAttachmentList.forEach { image ->
            Surface(
                modifier = Modifier
                    .padding(4.dp)
                    .size(100.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Image(
                    bitmap = ImageDecoder.decodeBitmap(
                        ImageDecoder.createSource(
                            context.contentResolver,
                            image
                        )
                    ).asImageBitmap(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}