package com.jettaskboard.multiplatform.ui.screens.dashboard.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells.Adaptive
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.components.dashboard.StarredItem
import com.jettaskboard.multiplatform.ui.components.dashboard.Header
import com.jettaskboard.multiplatform.ui.components.dashboard.WorkspaceItem
import com.jettaskboard.multiplatform.ui.screens.dashboard.DashboardViewModel

@Composable
fun DashboardSinglePaneContent(
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit = {}
) {
    LazyColumn(
        Modifier.padding(paddingValues)
    ) {
        item {
            Header(
                modifier = Modifier,
                title = "Starred Boards",
                onMenuItemClicked = {}
            )
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp)
                    .height(240.dp),
                columns = Adaptive(minSize = 150.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                if (viewModel.listOfBoards.isNotEmpty()) {
                    items(viewModel.listOfBoards.subList(0, 5)) { boardItem ->
                        StarredItem(
                            modifier = Modifier.clickable { navigateToTaskBoard("") },
                            title = boardItem.title,
                            backgroundImageUrl = boardItem.imageUrl
                        )
                    }
                }
            }
        }
        item {
            Header(
                modifier = Modifier,
                title = "Trello workspace",
                showIcon = true
            )
            LazyColumn(
                modifier = Modifier
                    .height(320.dp)
                    .padding(top = 4.dp)
            ) {
                items(viewModel.listOfBoards) {
                    WorkspaceItem(
                        modifier = Modifier.clickable { navigateToTaskBoard("") },
                        title = it.title,
                        imageUrl = it.imageUrl,
                        isWorkshopStarred = it.isStarred
                    )
                }
            }
        }
    }
}
