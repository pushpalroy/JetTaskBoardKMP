package com.jettaskboard.multiplatform.ui.components.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage

@Composable
fun WorkspaceItem(
    modifier: Modifier,
    title: String,
    imageUrl: String,
    isWorkshopStarred: Boolean = false,
    starPainterResource: ImageVector = Icons.Outlined.Star
) {
    Row(
        modifier = modifier
            .padding(vertical = 6.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            modifier = Modifier.size(42.dp).fillMaxWidth().clip(RoundedCornerShape(5)),
            imageUrl = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth
        )

        Text(
            text = title,
            style = MaterialTheme.typography.caption,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            fontSize = 14.sp
        )

        if (isWorkshopStarred) {
            Icon(
                imageVector = starPainterResource,
                modifier = modifier.size(18.dp),
                contentDescription = "star",
                tint = Color(0xFFFFEB3B)
            )
        }
    }
}
