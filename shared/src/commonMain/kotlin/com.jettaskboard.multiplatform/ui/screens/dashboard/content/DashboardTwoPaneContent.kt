package com.jettaskboard.multiplatform.ui.screens.dashboard.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.components.dashboard.Header
import com.jettaskboard.multiplatform.ui.components.dashboard.StarredItem
import com.jettaskboard.multiplatform.ui.components.dashboard.WorkspaceItem
import com.jettaskboard.multiplatform.ui.screens.dashboard.DashboardViewModel

@Composable
fun DashboardTwoPaneContent(
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit = {}
) {
    Row {
        LeftPane(
            modifier = Modifier.weight(1f),
            paddingValues = paddingValues,
            viewModel = viewModel,
            navigateToTaskBoard = navigateToTaskBoard
        )
        RightPane(
            modifier = Modifier.weight(1f),
            paddingValues = paddingValues,
            viewModel = viewModel,
            navigateToTaskBoard = navigateToTaskBoard
        )
    }
}

@Composable
fun LeftPane(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit
) {
    Column(
        modifier.padding(paddingValues)
    ) {
        Header(
            modifier = Modifier,
            title = "Starred Boards",
            onMenuItemClicked = {}
        )
        Row {
            LazyVerticalGrid(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 8.dp, end = 8.dp)
                    .fillMaxSize(),
                columns = Fixed(1),
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
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxHeight()
                    .width(1.dp)
            )
        }
    }
}

@Composable
fun RightPane(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit
) {
    Column(
        modifier.padding(paddingValues)
    ) {
        Header(
            modifier = Modifier,
            title = "Trello workspace",
            showIcon = true
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
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
