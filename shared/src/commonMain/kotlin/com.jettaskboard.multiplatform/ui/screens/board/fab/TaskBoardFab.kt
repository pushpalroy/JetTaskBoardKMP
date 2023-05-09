package com.jettaskboard.multiplatform.ui.screens.board.fab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun TaskBoardZoomOptions(
    modifier: Modifier,
    onZoomIn: () -> Unit,
    onZoomOut: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FAB(
            onClick = onZoomIn,
            painterResource("ic_zoom_in.xml")
        )
        FAB(
            onClick = onZoomOut,
            painterResource("ic_zoom_out.xml")
        )
    }
}

@Composable
fun FAB(
    onClick: () -> Unit,
    painter: Painter
) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            modifier = Modifier,
            painter = painter,
            tint = Color.White,
            contentDescription = null
        )
    }
}
