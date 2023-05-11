package com.jettaskboard.multiplatform.util.imageattachment

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.screens.carddetails.ItemRow

private sealed interface ImageAccess {
    object Undefined: ImageAccess
    object Defined: ImageAccess
    object Unauthorised: ImageAccess
}



@Composable
actual fun ImageAttachmentView(modifier: Modifier) {
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
//                if (galleryPermissionStatus.status != PermissionStatus.Granted) {
//                    galleryPermissionStatus.launchPermissionRequest()
//                } else {
//                    launcher.launch("image/*")
//                }
        }
    )
}