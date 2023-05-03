package com.jettaskboard.multiplatform.util.dropdown

import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
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
    // DropDown
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest
    ) {
        content()
    }
}

@Composable
actual fun JDropdownMenuItem(
    modifier: Modifier,
    onSelect: () -> Unit,
    content: @Composable () -> Unit
) {
    DropdownMenuItem(onClick = onSelect) {
        content()
    }
}