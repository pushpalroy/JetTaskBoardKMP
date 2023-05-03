package com.jettaskboard.multiplatform.ui.components.board.bg

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.ui.components.loading.TrelloComponentLoadingAnimation
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun PhotoCardComponent(
    title: String,
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    backgroundImageUrl: String,
    placeHolderImage: Painter = painterResource("placeholder_card.png"),
    imageSelectedState: Boolean = false,
    loadingAnimationState: Boolean = false
) {
    Card(
        modifier = modifier
            .height(height)
            .padding(4.dp),
        shape = RoundedCornerShape(5),
        border = if (imageSelectedState) BorderStroke(1.dp, Color.Yellow) else BorderStroke(
            0.dp,
            Color.Transparent
        ),
    ) {
        Box(
            modifier = modifier.fillMaxSize()
        ) {
            AsyncImage(
                imageUrl = backgroundImageUrl,
                loadingPlaceholder = {
                    Image(
                        painter = placeHolderImage,
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier.matchParentSize()
                    )
                },
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .height(height.div(4))
                    .align(Alignment.BottomCenter),
                color = Color.Black.copy(alpha = 0.4f)
            ) {
                if (loadingAnimationState && imageSelectedState) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize(0.8f),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TrelloComponentLoadingAnimation(
                            modifier = Modifier.padding(
                                bottom = 8.dp,
                                start = 8.dp
                            ),
                            widthOfCanvas = 20.dp,
                            removeBorderRectangle = true
                        )

                        Text(
                            modifier = modifier
                                .padding(start = 18.dp, top = 4.dp, bottom = 4.dp),
                            text = "Uploading...",
                            fontStyle = FontStyle.Normal,
                            fontSize = 12.sp
                        )
                    }
                } else {
                    Text(
                        modifier = modifier
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        text = title,
                        fontStyle = FontStyle.Normal,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}