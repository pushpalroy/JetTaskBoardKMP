package com.jettaskboard.multiplatform.ui.components.dashboard

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jettaskboard.multiplatform.ui.components.loading.TrelloNotificationAnimationIcon

@Composable
fun DashboardAppBar(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean,
    onMenuIconClick: () -> Unit,
    onSearchIconClicked: () -> Unit,
    onNotificationIconClicked: () -> Unit,
    notificationCount: Int = 0
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = "Boards")
        },
        navigationIcon = {
            IconButton(onClick = onMenuIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Drawer"
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(onClick = onSearchIconClicked) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search"
                )
            }

            IconButton(onClick = onNotificationIconClicked) {
                TrelloNotificationAnimationIcon(
                    notificationCounts = 11
                )
            }
        }
    )
}
