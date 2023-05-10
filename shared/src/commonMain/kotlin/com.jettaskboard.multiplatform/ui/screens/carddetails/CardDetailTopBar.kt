package com.jettaskboard.multiplatform.ui.screens.carddetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.ui.theme.LabelBlue

@Composable
fun CardDetailTopBar(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    closeIcon: ImageVector = Icons.Default.Close,
    onMenuClick: () -> Unit,
    menuIcon: ImageVector = Icons.Default.MoreVert
) {
    Box(
        modifier = modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(LabelBlue),
    ) {

        Column(
            modifier = Modifier.matchParentSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onClose
                ) {
                    Icon(imageVector = closeIcon, contentDescription = "Close Details")
                }

                IconButton(
                    onClick = onMenuClick
                ) {
                    Icon(imageVector = menuIcon, contentDescription = "More Options")
                }

            }

            Card(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(16.dp),
                backgroundColor = Color.Black,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(18.dp),
                        imageVector = Icons.Default.CreditCard,
                        contentDescription = "Change Cover"
                    )
                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text = "Cover",
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}