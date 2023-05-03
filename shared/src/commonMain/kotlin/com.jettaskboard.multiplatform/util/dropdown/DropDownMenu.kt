package com.jettaskboard.multiplatform.util.dropdown

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
expect fun JDropdownMenu(
    expanded: Boolean,
    onDismissRequest: () -> Unit = {},
    modifier: Modifier = Modifier,
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    content: @Composable () -> Unit
)

@Composable
expect fun JDropdownMenuItem(
    modifier: Modifier = Modifier,
    onSelect: () -> Unit = {},
    content: @Composable () -> Unit
)