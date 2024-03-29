package com.jettaskboard.multiplatform.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.jettaskboard.multiplatform.ui.screens.board.TaskBoardRoute
import com.jettaskboard.multiplatform.ui.screens.board.changeBg.ChangeBoardBackgroundRoute
import com.jettaskboard.multiplatform.ui.screens.carddetails.CardDetailsRoute
import com.jettaskboard.multiplatform.ui.screens.dashboard.DashboardRoute
import com.jettaskboard.multiplatform.ui.theme.JtbTheme
import com.jettaskboard.multiplatform.util.krouter.RoutedContent
import com.jettaskboard.multiplatform.util.krouter.rememberRouter

@Composable
fun RootComponent(
    isExpandedScreen: Boolean = false
) {

    val router = rememberRouter(RootStateModel::class, listOf(RootStateModel.Dashboard))
    var boardBg by remember { mutableStateOf("") }

    JtbTheme(darkTheme = true) {
        RoutedContent(
            router = router,
            animation = stackAnimation(animator = slide()),
            content = { screen ->
                when (screen) {
                    is RootStateModel.Dashboard -> DashboardRoute(
                        isExpandedScreen = isExpandedScreen,
                        navigateToTaskBoard = {
                            router.push(RootStateModel.TaskBoard)
                        }
                    )

                    is RootStateModel.TaskBoard -> TaskBoardRoute(
                        isExpandedScreen = isExpandedScreen,
                        navigateToChangeBgScreen = {
                            router.push(RootStateModel.ChangeBackground)
                        },
                        onBackClick = { router.pop() },
                        navigateToCreateCard = {
                            router.push(RootStateModel.CardDetails)
                        },
                        boardBg = boardBg
                    )

                    is RootStateModel.ChangeBackground -> ChangeBoardBackgroundRoute(
                        onImageSelected = { imageUri ->
                            boardBg = imageUri
                            router.pop()
                        },
                        onBackClick = { router.pop() }
                    )

                    is RootStateModel.CardDetails -> CardDetailsRoute(
                        isExpandedScreen = isExpandedScreen,
                        onCancelClick = { router.pop() }
                    )
                }
            }
        )
    }
}

@Parcelize
sealed class RootStateModel : Parcelable {
    data object Dashboard : RootStateModel()
    data object TaskBoard : RootStateModel()
    data object ChangeBackground : RootStateModel()

    data object CardDetails: RootStateModel()
}
