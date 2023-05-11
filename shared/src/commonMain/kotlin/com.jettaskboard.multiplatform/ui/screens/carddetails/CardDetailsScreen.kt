package com.jettaskboard.multiplatform.ui.screens.carddetails

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jettaskboard.multiplatform.util.insetsx.ExperimentalSoftwareKeyboardApi
import com.jettaskboard.multiplatform.util.insetsx.safeDrawing
import com.jettaskboard.multiplatform.util.krouter.rememberViewModel

@OptIn(ExperimentalSoftwareKeyboardApi::class)
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
                CardDetailTopBar(
                    modifier = Modifier.windowInsetsPadding(
                        WindowInsets.safeDrawing.only(WindowInsetsSides.Vertical)
                    ),
                    onClose = onCancelClick,
                    onMenuClick = {}
                )
            }
        ) {
            if (!isExpandedScreen) {
                CardDetailsContent(Modifier.padding(it),scrollState, CardDetail(), viewModel)
            } else {
                ExpandedCardDetailContent(
                    expandedLeftScrollState,
                    expandedRightScrollState,
                    CardDetail(),
                    viewModel
                )
            }
        }
    }
}
