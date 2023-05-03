package com.jettaskboard.multiplatform.util.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset

@Composable
actual fun JDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    modifier: Modifier,
    offset: DpOffset,
    content: @Composable () -> Unit
) {
    // todo - implementation
}

@Composable
actual fun JDropdownMenuItem(
    modifier: Modifier,
    onSelect: () -> Unit,
    content: @Composable () -> Unit
) {
    // todo - implementation
}