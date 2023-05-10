package com.jettaskboard.multiplatform.ui.screens.carddetails

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jettaskboard.multiplatform.ui.screens.dashboard.DashboardViewModel
import com.jettaskboard.multiplatform.util.krouter.rememberViewModel

@Composable
fun CardDetailsRoute(
    isExpandedScreen: Boolean,
    modifier: Modifier = Modifier,
    onCancelClick: () -> Unit
) {
    val viewModel = rememberViewModel(CardViewModel::class) { CardViewModel() }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        val scrollState = rememberScrollState()
        val expandedLeftScrollState = rememberScrollState()
        val expandedRightScrollState = rememberScrollState()
        Scaffold(
            topBar = {
//                MotionTopBar(
//                    scrollState = scrollState,
//                    isExpandedScreen,
//                    onCancelClick,
//                    viewModel.cardModel.value.coverImageUrl,
//                    viewModel.cardModel.value.title
//                )
            }
        ) {
            if (!isExpandedScreen) {
                CardDetailsContent(scrollState, CardDetail(), viewModel)
            } else {
//                ExpandedCardDetailContent(
//                    expandedLeftScrollState,
//                    expandedRightScrollState,
//                    CardDetail(),
//                    viewModel
//                )
            }
        }
    }
}
