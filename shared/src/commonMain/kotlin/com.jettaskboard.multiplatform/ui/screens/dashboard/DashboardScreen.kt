package com.jettaskboard.multiplatform.ui.screens.dashboard

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.FabPosition
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.rememberDrawerState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jettaskboard.multiplatform.ui.components.dashboard.DashboardAppBar
import com.jettaskboard.multiplatform.ui.components.drawer.JtbDrawerShape
import com.jettaskboard.multiplatform.ui.components.multifab.FabIcon
import com.jettaskboard.multiplatform.ui.components.multifab.FabOption
import com.jettaskboard.multiplatform.ui.components.multifab.MultiFabItem
import com.jettaskboard.multiplatform.ui.components.multifab.MultiFloatingActionButton
import com.jettaskboard.multiplatform.ui.screens.dashboard.content.DashboardSinglePaneContent
import com.jettaskboard.multiplatform.ui.screens.dashboard.content.DashboardTwoPaneContent
import com.jettaskboard.multiplatform.ui.screens.dashboard.drawer.JtbDrawer
import com.jettaskboard.multiplatform.util.krouter.rememberViewModel
import com.moriatsushi.insetsx.ExperimentalSoftwareKeyboardApi
import com.moriatsushi.insetsx.safeDrawing
import kotlinx.coroutines.launch

@OptIn(ExperimentalSoftwareKeyboardApi::class)
@Composable
fun DashboardRoute(
    modifier: Modifier = Modifier,
    navigateToTaskBoard: (String) -> Unit = {},
    navigateToCreateCard: (String) -> Unit = {},
    navigateToCreateBoard: (String) -> Unit = {},
    navigateToSearchScreen: (String) -> Unit = {},
    isExpandedScreen: Boolean = false
) {
    val viewModel = rememberViewModel(DashboardViewModel::class) { DashboardViewModel() }
    var isMenuClickedInExpandedMode by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.apply {
            getBoardListData()
        }
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scaffoldState = rememberSizeAwareScaffoldState(isExpandedScreen)
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
            ),
            topBar = {
                DashboardAppBar(
                    isExpandedScreen = isExpandedScreen,
                    onMenuIconClick = {
                        scope.launch {
                            if (isExpandedScreen.not()) {
                                scaffoldState.drawerState.open()
                            } else {
                                isMenuClickedInExpandedMode = isMenuClickedInExpandedMode.not()
                            }
                        }
                    },
                    onSearchIconClicked = {
                        navigateToSearchScreen("")
                    },
                    onNotificationIconClicked = {}
                )
            },
            // Gestures are enabled only on smaller and medium screens
            drawerGesturesEnabled = isExpandedScreen.not(),
            drawerShape = JtbDrawerShape(),
            drawerContent = {
                // Modal drawer is available only on smaller and medium screens
                if (isExpandedScreen.not()) {
                    JtbDrawer(
                        modifier = Modifier.fillMaxSize(),
                        viewModel = viewModel,
                        isExpandedScreen = isExpandedScreen
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                MultiFab(
                    navigateToCreateCard,
                    navigateToCreateBoard
                )
            },
            backgroundColor = MaterialTheme.colors.background
        ) { scaffoldPadding ->
            val permanentNavDrawerWidth by animateDpAsState(
                targetValue = if (isMenuClickedInExpandedMode) 80.dp else 320.dp,
                animationSpec = SpringSpec(
                    dampingRatio = 0.5f,
                    stiffness = Spring.StiffnessLow
                )
            )
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                // Show permanent drawer only for large screens
                if (isExpandedScreen) {
                    Column(
                        Modifier.width(permanentNavDrawerWidth)
                    ) {
                        Spacer(modifier = Modifier.height(56.dp))
                        JtbDrawer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            viewModel = viewModel,
                            isExpandedScreen = true,
                            isMenuClickedInExpandedMode = isMenuClickedInExpandedMode
                        )
                    }
                }
                AdaptiveDashboardContent(
                    viewModel = viewModel,
                    isExpandedScreen = isExpandedScreen,
                    contentPadding = scaffoldPadding,
                    navigateToTaskBoard = navigateToTaskBoard
                )
            }
        }
    }
}

@Composable
fun AdaptiveDashboardContent(
    isExpandedScreen: Boolean,
    contentPadding: PaddingValues,
    viewModel: DashboardViewModel,
    navigateToTaskBoard: (String) -> Unit = {}
) {
    if (isExpandedScreen.not()) {
        DashboardSinglePaneContent(
            paddingValues = contentPadding,
            viewModel = viewModel,
            navigateToTaskBoard = navigateToTaskBoard
        )
    } else {
        DashboardTwoPaneContent(
            paddingValues = contentPadding,
            viewModel = viewModel,
            navigateToTaskBoard = navigateToTaskBoard
        )
    }
}

@Composable
private fun rememberSizeAwareScaffoldState(
    isExpandedScreen: Boolean
): ScaffoldState {
    val commonSnackBarHostState = remember { SnackbarHostState() }
    val compactScaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(DrawerValue.Closed),
        snackbarHostState = commonSnackBarHostState
    )
    val expandedScaffoldState = rememberScaffoldState(
        drawerState = DrawerState(DrawerValue.Closed),
        snackbarHostState = commonSnackBarHostState
    )
    return if (isExpandedScreen) {
        expandedScaffoldState
    } else {
        compactScaffoldState
    }
}

@Composable
private fun MultiFab(
    navigateToCreateCard: (String) -> Unit = {},
    navigateToCreateBoard: (String) -> Unit = {}
) {
    MultiFloatingActionButton(
        fabIcon = FabIcon(
            iconRes = "ic_create.xml",
            iconRotate = 25f
        ),
        fabOption = FabOption(
            iconTint = Color.White,
            showLabels = true
        ),
        items = listOf(
            MultiFabItem(
                id = 1,
                iconRes = "ic_dashboard.png",
                label = "Board"
            ),
            MultiFabItem(
                id = 2,
                iconRes = "ic_add_card.png",
                label = "Card"
            )
        ),
        onFabItemClicked = { item ->
            if (item.id == 1) {
                navigateToCreateBoard("")
            } else {
                navigateToCreateCard("")
            }
        }
    )
}
