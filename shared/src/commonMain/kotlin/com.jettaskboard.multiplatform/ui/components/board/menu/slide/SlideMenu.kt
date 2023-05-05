package com.jettaskboard.multiplatform.ui.components.board.menu.slide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.screens.board.ExpandedBoardDrawerState
import com.jettaskboard.multiplatform.ui.screens.board.changeBg.ChangeBoardBackgroundRoute

@Composable
fun SlideMenu(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    navigateToChangeBackgroundRoute: (String) -> Unit,
    changeToDrawerScreenState: () -> Unit,
    updateBoardBg: (String) -> Unit,
    expandedScreenState: ExpandedBoardDrawerState
) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.36f)
            .drawWithCache {
                onDrawBehind {
                    drawRect(color = backgroundColor)
                }
            }
            .shadow(elevation = 0.dp)
    ) {
        when (expandedScreenState) {
            ExpandedBoardDrawerState.DRAWER_SCREEN_STATE -> {
                SlideMenuContent(
                    navigateToChangeBackgroundRoute = navigateToChangeBackgroundRoute
                )
            }

            ExpandedBoardDrawerState.CHANGE_BACKGROUND_SCREEN_STATE -> {
                ChangeBoardBackgroundRoute(
                    onBackClick = { changeToDrawerScreenState() },
                    onImageSelected = { updateBoardBg(it) }
                )
            }

            ExpandedBoardDrawerState.FILTER_SCREEN_STATE -> {
                // Do Nothing
            }
        }
    }
}
