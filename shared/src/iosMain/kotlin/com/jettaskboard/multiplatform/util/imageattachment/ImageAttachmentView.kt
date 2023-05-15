package com.jettaskboard.multiplatform.util.imageattachment

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Attachment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.screens.carddetails.ItemRow
import platform.Foundation.NSCoder
import platform.UIKit.UIImage

@Composable
actual fun ImageAttachmentView(modifier: Modifier) {
    var image by remember { mutableStateOf<UIImage?>(null) }

    val viewController = remember {
        mutableStateOf(
            ViewController(NSCoder())
        )
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
            viewController.value.chooseImage()
        }
    )
}