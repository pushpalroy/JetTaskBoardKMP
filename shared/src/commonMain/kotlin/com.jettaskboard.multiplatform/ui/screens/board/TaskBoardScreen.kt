package com.jettaskboard.multiplatform.ui.screens.board

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.components.zoomable.Zoomable
import com.jettaskboard.multiplatform.ui.components.zoomable.rememberZoomableState
import com.jettaskboard.multiplatform.ui.components.board.Board
import com.jettaskboard.multiplatform.ui.theme.DefaultTaskBoardBGColor
import com.jettaskboard.multiplatform.util.asyncimage.AsyncImage
import com.jettaskboard.multiplatform.util.krouter.rememberViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun TaskBoardRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    isExpandedScreen: Boolean = false,
    navigateToCreateCard: (String) -> Unit = {},
    navigateToChangeBackgroundScreen: (String) -> Unit = {},
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    val viewModel = rememberViewModel(TaskBoardViewModel::class) { TaskBoardViewModel() }
    val expandedScreenState = viewModel.drawerScreenState.value
    val scaffoldState = rememberScaffoldState()
    val zoomableState = rememberZoomableState()
    val coroutineScope = rememberCoroutineScope()
    var expandedPanel by remember {
        mutableStateOf(false)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                isExpandedScreen = isExpandedScreen,
                onBackClick = onBackClick,
                title = viewModel.boardInfo.value.second,
                navigateToChangeBackgroundScreen = { passedString ->
                    navigateToChangeBackgroundScreen(passedString)
                },
                onHamBurgerIconClicked = {
                    expandedPanel = !expandedPanel
                }
            )
        },
        floatingActionButton = {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FAB(
                    onClick = {},
                    painterResource("ic_zoom_in.xml")
                )
                FAB(
                    onClick = {
                        if (zoomableState.scale.value != 1f) {
                            coroutineScope.launch {
                                zoomableState.animateBy(
                                    zoomChange = 1 / zoomableState.scale.value,
                                    panChange = -zoomableState.offset.value,
                                    rotationChange = -zoomableState.rotation.value
                                )
                            }
                        }
                    },
                    painterResource("ic_zoom_out.xml")
                )
            }
        }
    ) { scaffoldPaddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(scaffoldPaddingValues),
            color = DefaultTaskBoardBGColor
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.TopEnd
            ) {
                AsyncImage(
                    imageUrl = viewModel.latestBackgroundImgUri,
                    loadingPlaceholder = {
                        //painterResource("bg_board.jpg")
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .matchParentSize()
                )

                Zoomable(
                    coroutineScope = coroutineScope,
                    zoomableState = zoomableState
                ) {
                    Board(
                        modifier = Modifier.fillMaxSize(),
                        navigateToCreateCard = navigateToCreateCard,
                        isExpandedScreen = isExpandedScreen
                    )
                }

                if (isExpandedScreen) {
                    AnimatedVisibility(
                        enter = slideInHorizontally { it },
                        exit = slideOutHorizontally { it },
                        visible = expandedPanel,
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.28f)
                                .drawWithCache {
                                    onDrawBehind {
                                        drawRect(
                                            color = backgroundColor
                                        )
                                    }
                                }
                                .shadow(elevation = 0.dp)
                        ) {
                            when (expandedScreenState) {
                                ExpandedBoardDrawerState.DRAWER_SCREEN_STATE -> {
                                    TaskBoardExpandedScreenDrawer(
                                        navigateToChangeBackgroundRoute = { changeBgScreen ->
                                            viewModel.changeExpandedScreenState(
                                                ExpandedBoardDrawerState.CHANGE_BACKGROUND_SCREEN_STATE
                                            )
//                                            navigateToChangeBackgroundScreen(changeBgScreen)
                                        }
                                    )
                                }
                                ExpandedBoardDrawerState.CHANGE_BACKGROUND_SCREEN_STATE -> {
//                                    ChangeBoardBackgroundRoute(
//                                        onBackClick = {
//                                            viewModel.changeExpandedScreenState(
//                                                ExpandedBoardDrawerState.DRAWER_SCREEN_STATE
//                                            )
//                                        }
//                                    )
                                }
                                ExpandedBoardDrawerState.FILTER_SCREEN_STATE -> {
                                    // Do Nothing
                                }
                                ExpandedBoardDrawerState.AUTOMATION_SCREEN_STATE -> {
                                    // Do Nothing
                                }
                                ExpandedBoardDrawerState.POWER_UP_SCREEN_STATE -> {
                                    // Do Nothing
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopAppBar(
    isExpandedScreen: Boolean = false,
    onBackClick: () -> Unit,
    title: String,
    navigateToChangeBackgroundScreen: (String) -> Unit,
    onHamBurgerIconClicked: () -> Unit = {}
) {
    var displayTaskBoardToolbarMenuState by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = { Text(text = title) },
        actions = {
            if (isExpandedScreen) {
                IconButton(onClick = {
                    onHamBurgerIconClicked()
                }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Hamburger Menu Icon"
                    )
                }
            } else {
                IconButton(onClick = {
                    displayTaskBoardToolbarMenuState = !displayTaskBoardToolbarMenuState
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Toolbar Menu Icon"
                    )
                }
            }
        }
    )
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
