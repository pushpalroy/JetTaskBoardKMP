package com.jettaskboard.multiplatform.ui.components.board

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.domain.model.ListModel
import com.jettaskboard.multiplatform.ui.components.board.card.TaskCard
import com.jettaskboard.multiplatform.ui.components.draganddrop.ArchiveDropSurface
import com.jettaskboard.multiplatform.ui.components.draganddrop.DragAndDropState
import com.jettaskboard.multiplatform.ui.components.draganddrop.DragAndDropSurface
import com.jettaskboard.multiplatform.ui.components.draganddrop.DragSurface
import com.jettaskboard.multiplatform.ui.components.draganddrop.DropSurface
import com.jettaskboard.multiplatform.ui.screens.board.appBar.TaskBoardAppBar

@Composable
fun Board(
    modifier: Modifier = Modifier,
    title: String,
    lists: List<ListModel>,
    onCardMovedToDifferentList: (Int, Int, Int) -> Unit,
    onCardRemovedFromList: (Int, Int) -> Unit,
    onCardEditDone: (Int, Int, String) -> Unit,
    onAddNewCardClicked: (Int) -> Unit,
    onAddNewListClicked: () -> Unit,
    navigateToCreateCard: (String) -> Unit,
    saveClicked: Boolean,
    boardState: DragAndDropState,
    isExpandedScreen: Boolean = false,
    onBackClick: () -> Unit,
    onSaveClicked: () -> Unit,
    editModeEnabled: Boolean,
    navigateToChangeBgScreen: (String) -> Unit,
    onHamBurgerMenuClick: () -> Unit = {}
) {
    LaunchedEffect(key1 = boardState.movingCardData) {
        if (boardState.hasCardMoved()) {
            onCardMovedToDifferentList(
                boardState.movingCardData.first,
                boardState.cardDraggedInitialListId,
                boardState.movingCardData.second
            )
        }
    }
    LaunchedEffect(boardState.isArchived, boardState.dragOffset) {
        if (boardState.isArchived && boardState.dragOffset == Offset.Zero) {
            onCardRemovedFromList(boardState.cardDraggedId, boardState.listIdWithCardInBounds)
            boardState.isArchived = false
        }
    }
    DragAndDropSurface(
        modifier = modifier.fillMaxSize(),
        state = boardState
    ) {
        Column {
            TaskBoardAppBar(
                isExpandedScreen = isExpandedScreen,
                onBackClick = onBackClick,
                title = title,
                navigateToChangeBgScreen = navigateToChangeBgScreen,
                onHamBurgerMenuClick = onHamBurgerMenuClick,
                onSaveClicked = onSaveClicked,
                editModeEnabled = editModeEnabled,
                boardState = boardState
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(lists) { list ->
                    Lists(
                        boardState = boardState,
                        listModel = list,
                        isExpandedScreen = isExpandedScreen,
                        onCardClick = navigateToCreateCard,
                        onAddCardClick = { onAddNewCardClicked(list.id) },
                        onCardEditDone = onCardEditDone,
                        saveClicked = saveClicked
                    )
                }
                item {
                    AddNewListButton(
                        onClick = onAddNewListClicked,
                        isExpandedScreen = isExpandedScreen
                    )
                }
            }
        }
    }
}

@Composable
fun Lists(
    boardState: DragAndDropState,
    listModel: ListModel,
    onCardClick: (String) -> Unit,
    onCardEditDone: (Int, Int, String) -> Unit,
    onAddCardClick: () -> Unit,
    saveClicked: Boolean,
    isExpandedScreen: Boolean
) {
    DropSurface(
        modifier = Modifier
            .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 8.dp),
        listId = listModel.id
    ) { isInBound, _ ->
        Column(
            modifier = Modifier
                .background(
                    color = getDropSurfaceBgColor(isInBound, boardState.isDragging),
                    shape = RoundedCornerShape(2)
                )
                .width(if (isExpandedScreen) 300.dp else 240.dp)
                .padding(if (isExpandedScreen) 8.dp else 4.dp)
        ) {
            ListHeader(
                name = listModel.title
            )
            ListBody(
                modifier = Modifier,
                listModel = listModel,
                onTaskCardClick = onCardClick,
                onTaskCardEditDone = onCardEditDone,
                onAddCardClick = onAddCardClick,
                isExpandedScreen = isExpandedScreen,
                saveClicked = saveClicked
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListBody(
    modifier: Modifier,
    listModel: ListModel,
    onTaskCardClick: (String) -> Unit,
    onTaskCardEditDone: (Int, Int, String) -> Unit,
    onAddCardClick: () -> Unit,
    saveClicked: Boolean,
    isExpandedScreen: Boolean
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listModel.cards,
            key = { card -> card.id }
        ) { card ->
            DragSurface(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
                cardId = card.id,
                cardListId = card.listId ?: 0
            ) {
                TaskCard(
                    modifier = Modifier.fillMaxWidth(),
                    cardModel = card,
                    onClick = { onTaskCardClick("1") },
                    isExpandedScreen = isExpandedScreen,
                    editModeEnabled = card.title.isEmpty(),
                    onEditDone = onTaskCardEditDone,
                    saveClicked = saveClicked
                )
            }
        }
        item {
            ListFooter(
                onAddCardClick = onAddCardClick
            )
        }
    }
}

@Composable
fun ListHeader(
    modifier: Modifier = Modifier,
    name: String
) {
    Row(
        modifier = modifier
            .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Text(modifier = Modifier.weight(1f), text = name)
        IconButton(modifier = Modifier.size(16.dp), onClick = {}) {
            Icon(imageVector = Filled.MoreVert, contentDescription = "Menu")
        }
    }
}

@Composable
fun ListFooter(
    modifier: Modifier = Modifier,
    onAddCardClick: () -> Unit
) {
    Row(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        TextButton(
            modifier = Modifier
                .wrapContentHeight(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.secondary
            ),
            onClick = { onAddCardClick() }
        ) {
            Icon(imageVector = Filled.Add, contentDescription = "Add card")
            Text(modifier = Modifier, fontSize = 12.sp, text = "Add Card")
        }
    }
}

@Composable
fun AddNewListButton(
    onClick: () -> Unit = {},
    isExpandedScreen: Boolean
) {
    TextButton(
        modifier = Modifier
            .padding(16.dp)
            .width(if (isExpandedScreen) 300.dp else 240.dp),
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colors.onPrimary,
            backgroundColor = MaterialTheme.colors.primary
        ),
        contentPadding = PaddingValues(16.dp),
        onClick = onClick
    ) {
        Icon(imageVector = Filled.Add, contentDescription = "Add")
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.weight(1f), fontSize = 16.sp, text = "Add List")
    }
}

@Composable
fun ArchiveSurface(
    modifier: Modifier = Modifier,
    boardState: DragAndDropState
) {
    AnimatedVisibility(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(color = MaterialTheme.colors.primary),
        visible = boardState.isDragging,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        ArchiveDropSurface(
            modifier = modifier
        ) { isInBound, _ ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = getArchiveSurfaceBgColor(
                            isInBound, boardState.isDragging
                        )
                    )
            ) {
                Button(
                    modifier = Modifier.align(Alignment.Center),
                    contentPadding = PaddingValues(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    elevation = ButtonDefaults.elevation(defaultElevation = if (isInBound) 0.dp else 2.dp),
                    onClick = {}
                ) {
                    Text(
                        modifier = Modifier,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        text = "Drag here to archive"
                    )
                }
            }
        }
    }
}

/**
 * Returns the color for background of the drop surface,based on
 * whether a drop surface is in bounds, when a card is hovered on it.
 */
@Composable
fun getDropSurfaceBgColor(
    isInBound: Boolean,
    isDragging: Boolean
): Color {
    return if (isInBound && isDragging) {
        MaterialTheme.colors.primary
    } else {
        MaterialTheme.colors.surface
    }
}

@Composable
fun getArchiveSurfaceBgColor(
    isInBound: Boolean,
    isDragging: Boolean
): Color {
    return if (isInBound && isDragging) {
        MaterialTheme.colors.secondary
    } else {
        Color.Transparent
    }
}
