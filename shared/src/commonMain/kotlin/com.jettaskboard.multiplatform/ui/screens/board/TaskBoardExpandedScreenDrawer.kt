package com.jettaskboard.multiplatform.ui.screens.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TaskBoardExpandedScreenDrawer(
    navigateToChangeBackgroundRoute: (String) -> Unit,
    navigateToFilterRoute: () -> Unit = {},
    navigateToAutomationRoute: () -> Unit = {},
    navigateToPowerUpRoute: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Change Background Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Change Background",
            icon = painterResource("ic_baseline_wallpaper_24.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Filter Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Filter",
            icon = painterResource("ic_baseline_filter_list_24.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Automation Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Automation",
            icon = painterResource("ic_baseline_automation_icon.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Power's-up Item
        TaskBoardExpandedScreenDrawerItem(
            title = "Power's-up",
            icon = painterResource("ic_baseline_circle_notifications_24.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )
    }
}