package com.jettaskboard.multiplatform.ui.screens.board

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.jettaskboard.multiplatform.data.util.Constants
import com.jettaskboard.multiplatform.ui.components.board.Board
import com.jettaskboard.multiplatform.ui.components.board.menu.slide.SlideMenu
import com.jettaskboard.multiplatform.ui.components.zoomable.Zoomable
import com.jettaskboard.multiplatform.ui.components.zoomable.rememberZoomableState
import com.jettaskboard.multiplatform.ui.components.zoomable.zoomIn
import com.jettaskboard.multiplatform.ui.components.zoomable.zoomOut
import com.jettaskboard.multiplatform.ui.screens.board.appBar.TaskBoardAppBar
import com.jettaskboard.multiplatform.ui.screens.board.fab.TaskBoardZoomOptions
import com.jettaskboard.multiplatform.ui.theme.DefaultTaskBoardBGColor
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage
import com.jettaskboard.multiplatform.util.insetsx.ExperimentalSoftwareKeyboardApi
import com.jettaskboard.multiplatform.util.insetsx.safeDrawing
import com.jettaskboard.multiplatform.util.krouter.rememberViewModel

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun TaskBoardRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    isExpandedScreen: Boolean = false,
    navigateToCreateCard: (String) -> Unit = {},
    navigateToChangeBgScreen: (String) -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.surface,
    boardBg: String? = null
) {
    val viewModel = rememberViewModel(TaskBoardViewModel::class) { TaskBoardViewModel() }
    val boardBackground by viewModel.boardBackground.collectAsState(initial = Constants.DEFAULT_BOARD_BG)
    val lists = remember(viewModel.totalCards) { viewModel.lists }
    val expandedScreenState = viewModel.drawerScreenState.value

    var boardCenterOffset by remember { mutableStateOf(Offset.Zero) }
    var editModeEnabled by remember { mutableStateOf(false) }
    var saveCardClicked by remember { mutableStateOf(false) }
    var expandedPanel by remember { mutableStateOf(false) }

    val scaffoldState = rememberScaffoldState()
    val zoomableState = rememberZoomableState()
    val coroutineScope = rememberCoroutineScope()

    // Updating board background when a new one is available
    LaunchedEffect(boardBg) {
        boardBg?.let { safeBoardBg ->
            if (safeBoardBg.isEmpty().not()) {
                viewModel.updateBoardBackground(safeBoardBg)
            }
        }
    }

    Scaffold(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
        ),
        scaffoldState = scaffoldState,
        topBar = {
            TaskBoardAppBar(
                isExpandedScreen = isExpandedScreen,
                onBackClick = onBackClick,
                title = viewModel.boardInfo.value.second,
                navigateToChangeBgScreen = { passedString -> navigateToChangeBgScreen(passedString) },
                onHamBurgerMenuClick = { expandedPanel = !expandedPanel },
                onSaveClicked = { saveCardClicked = true },
                editModeEnabled = editModeEnabled
            )
        },
        floatingActionButton = {
            TaskBoardZoomOptions(
                modifier = Modifier,
                onZoomIn = {
                    zoomableState.zoomIn(coroutineScope, boardCenterOffset)
                },
                onZoomOut = {
                    zoomableState.zoomOut(coroutineScope)
                }
            )
        }
    ) { scaffoldPaddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(scaffoldPaddingValues),
            color = DefaultTaskBoardBGColor
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    imageUrl = boardBackground,
                    contentDescription = "Board background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    loadingPlaceholder = {}
                )

                Zoomable(
                    coroutineScope = coroutineScope,
                    zoomableState = zoomableState,
                    composableCenter = boardCenterOffset,
                    onComposableCenterShift = { boardCenterOffset = it },
                    onDoubleTap = {
                        if (zoomableState.scale.value == 1f) {
                            zoomableState.zoomIn(coroutineScope, boardCenterOffset)
                        } else if (zoomableState.scale.value != 1f) {
                            zoomableState.zoomOut(coroutineScope)
                        }
                    }
                ) {
                    Board(
                        modifier = Modifier.fillMaxSize(),
                        lists = lists,
                        onAddNewCardClicked = { listId ->
                            viewModel.addNewCardInList(listId)
                            editModeEnabled = true
                        },
                        onCardEditDone = { cardId, listId, title ->
                            viewModel.editCardInList(cardId, listId, title)
                            editModeEnabled = false
                            saveCardClicked = false
                        },
                        onAddNewListClicked = { viewModel.addNewList() },
                        onCardMovedToDifferentList = { cardId, oldListId, newListId ->
                            viewModel.moveCardToDifferentList(cardId, oldListId, newListId)
                        },
                        navigateToCreateCard = navigateToCreateCard,
                        isExpandedScreen = isExpandedScreen,
                        saveClicked = saveCardClicked
                    )
                }

                if (isExpandedScreen) {
                    AnimatedVisibility(
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it },
                        visible = expandedPanel,
                    ) {
                        SlideMenu(
                            modifier = Modifier,
                            backgroundColor = backgroundColor,
                            expandedScreenState = expandedScreenState,
                            navigateToChangeBackgroundRoute = {
                                viewModel.changeExpandedScreenState(
                                    ExpandedBoardDrawerState.CHANGE_BACKGROUND_SCREEN_STATE
                                )
                            },
                            changeToDrawerScreenState = {
                                viewModel.changeExpandedScreenState(
                                    ExpandedBoardDrawerState.DRAWER_SCREEN_STATE
                                )
                            },
                            updateBoardBg = { viewModel.updateBoardBackground(it) }
                        )
                    }
                }
            }
        }
    }
}