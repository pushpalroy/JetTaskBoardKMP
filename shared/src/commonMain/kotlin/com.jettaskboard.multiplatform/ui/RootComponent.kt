package com.jettaskboard.multiplatform.ui

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.essenty.parcelable.Parcelable
import com.arkivanov.essenty.parcelable.Parcelize
import com.jettaskboard.multiplatform.ui.screens.board.TaskBoardRoute
import com.jettaskboard.multiplatform.ui.screens.dashboard.DashboardRoute
import com.jettaskboard.multiplatform.ui.theme.JtbTheme
import com.jettaskboard.multiplatform.util.krouter.RoutedContent
import com.jettaskboard.multiplatform.util.krouter.rememberRouter

@Composable
fun RootComponent(
    isExpandedScreen: Boolean = false
) {

    val router = rememberRouter(RootStateModel::class, listOf(RootStateModel.Dashboard))

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
                        onBackClick = {
                            router.pop()
                        }
                    )
                    else -> {}
                }
            }
        )
    }
}

@Parcelize
sealed class RootStateModel : Parcelable {
    object Dashboard : RootStateModel()
    object TaskBoard : RootStateModel()
    data class NoteDetails(val noteId: Long?) : RootStateModel()
}
