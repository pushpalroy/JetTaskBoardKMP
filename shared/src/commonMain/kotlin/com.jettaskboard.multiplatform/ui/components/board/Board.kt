package com.jettaskboard.multiplatform.ui.components.board

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jettaskboard.multiplatform.domain.model.ListModel
import com.jettaskboard.multiplatform.ui.components.board.card.TaskCard
import com.jettaskboard.multiplatform.ui.components.draganddrop.DragAndDropState
import com.jettaskboard.multiplatform.ui.components.draganddrop.DragAndDropSurface
import com.jettaskboard.multiplatform.ui.components.draganddrop.DragSurface
import com.jettaskboard.multiplatform.ui.components.draganddrop.DropSurface
import com.jettaskboard.multiplatform.ui.theme.SecondaryColor
import com.jettaskboard.multiplatform.util.showcase.IntroShowCaseScaffold
import com.jettaskboard.multiplatform.util.showcase.ShowcaseStyle

@Composable
fun Board(
    modifier: Modifier = Modifier,
    lists: List<ListModel>,
    onAddNewCardClicked: (Int) -> Unit,
    onCardEditDone: (Int, Int, String) -> Unit,
    onAddNewListClicked: () -> Unit,
    navigateToCreateCard: (String) -> Unit,
    onCardMovedToDifferentList: (Int, Int, Int) -> Unit,
    saveClicked: Boolean,
    isExpandedScreen: Boolean = false
) {
    val boardState = remember { DragAndDropState(isExpandedScreen) }
    var showEditOverlayForDesktop by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = boardState.movingCardData) {
        if (boardState.hasCardMoved()) {
            onCardMovedToDifferentList(
                boardState.movingCardData.first,
                boardState.cardDraggedInitialListId,
                boardState.movingCardData.second
            )
        }
    }

    IntroShowCaseScaffold(
        showIntroShowCase = showEditOverlayForDesktop,
        onShowCaseCompleted = {
            showEditOverlayForDesktop = false
        },
    ) {
        DragAndDropSurface(
            modifier = modifier.fillMaxSize(),
            state = boardState
        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(lists) { list ->
                    Lists(
                        boardState = boardState,
                        listModel = list,
                        isExpandedScreen = isExpandedScreen,
                        onCardClick = navigateToCreateCard,
                        onAddCardClick = {
                            showEditOverlayForDesktop = true
                            onAddNewCardClicked(list.id)
                        },
                        onCardEditDone = onCardEditDone,
                        saveClicked = saveClicked,
                        showcaseModifier = Modifier.introShowCaseTarget(
                            index = 0,
                            style = ShowcaseStyle.Default.copy(
                                backgroundColor = Color(0xFF7C99AC),
                                backgroundAlpha = 0.90f,
                                targetCircleColor = Color.White
                            ),
                            content = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Column {
                                        Text(
                                            text = "Save",
                                            color = Color.White,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                        ),
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
    isExpandedScreen: Boolean,
    showcaseModifier: Modifier = Modifier
) {
    DropSurface(
        modifier = Modifier
            .padding(start = 16.dp, end = 0.dp, top = 16.dp, bottom = 8.dp)
            .background(
                color = Color(0xFF222222),
                shape = RoundedCornerShape(2)
            ),
        listId = listModel.id
    ) { isInBound, _ ->
        Column(
            modifier = Modifier
                .background(
                    color = getDropSurfaceBgColor(isInBound, boardState.isDragging)
                )
                .width(if (isExpandedScreen) 300.dp else 240.dp)
                .padding(if (isExpandedScreen) 8.dp else 4.dp)
        ) {
            ListHeader(
                name = listModel.title
            )
            ListBody(
                modifier = Modifier,
                showcaseModifier = showcaseModifier,
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
    showcaseModifier: Modifier = Modifier,
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
                modifier = showcaseModifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
                cardId = card.id,
                cardListId = card.listId ?: 0
            ) {
                TaskCard(
                    cardModel = card,
                    onClick = { onTaskCardClick("1") },
                    isExpandedScreen = isExpandedScreen,
                    editModeEnabled = card.title.isEmpty(),
                    onEditDone = onTaskCardEditDone,
                    modifier = Modifier.fillMaxWidth(),
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
            .padding(start = 8.dp, top = 16.dp, end = 8.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        TextButton(
            modifier = Modifier
                .height(24.dp),
            contentPadding = PaddingValues(4.dp),
            colors = ButtonDefaults.textButtonColors(
                contentColor = SecondaryColor
            ),
            onClick = { onAddCardClick() }
        ) {
            Icon(imageVector = Filled.Add, contentDescription = "Add")
            Text(modifier = Modifier, fontSize = 10.sp, text = "Add Card")
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
            contentColor = Color.White,
            backgroundColor = Color(0xFF383838)
        ),
        contentPadding = PaddingValues(16.dp),
        onClick = onClick
    ) {
        Icon(imageVector = Filled.Add, contentDescription = "Add")
        Spacer(modifier = Modifier.width(8.dp))
        Text(modifier = Modifier.weight(1f), fontSize = 16.sp, text = "Add List")
    }
}

/**
 * Returns the color for background of the drop surface,based on
 * whether a drop surface is in bounds, when a card is hovered on it.
 */
fun getDropSurfaceBgColor(
    isInBound: Boolean,
    isDragging: Boolean
): Color {
    return if (isInBound && isDragging) {
        Color(0xFF383838)
    } else {
        Color.Transparent
    }
}
