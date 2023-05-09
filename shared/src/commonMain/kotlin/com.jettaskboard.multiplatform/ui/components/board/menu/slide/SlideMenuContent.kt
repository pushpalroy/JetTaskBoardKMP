package com.jettaskboard.multiplatform.ui.components.board.menu.slide

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SlideMenuContent(
    navigateToChangeBackgroundRoute: (String) -> Unit,
    navigateToFilterRoute: () -> Unit = {},
    navigateToAutomationRoute: () -> Unit = {},
    navigateToPowerUpRoute: () -> Unit = {},
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SlideMenuItem(
            title = "Change Background",
            icon = painterResource("ic_baseline_wallpaper_24.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Filter Item
        SlideMenuItem(
            title = "Filter",
            icon = painterResource("ic_baseline_filter_list_24.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )

        // Automation Item
        SlideMenuItem(
            title = "Automation",
            icon = painterResource("ic_baseline_automation_icon.xml"),
            onItemClickListener = navigateToChangeBackgroundRoute
        )
    }
}