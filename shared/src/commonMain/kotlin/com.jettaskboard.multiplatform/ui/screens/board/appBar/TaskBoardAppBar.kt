package com.jettaskboard.multiplatform.ui.screens.board.appBar

import DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun TaskBoardAppBar(
    modifier: Modifier = Modifier,
    isExpandedScreen: Boolean = false,
    onBackClick: () -> Unit,
    onSaveClicked: () -> Unit,
    title: String,
    editModeEnabled: Boolean,
    navigateToChangeBgScreen: (String) -> Unit,
    onHamBurgerMenuClick: () -> Unit = {}
) {
    var displayDropDownMenu by remember { mutableStateOf(false) }

    TopAppBar(
        modifier = modifier,
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
            if (editModeEnabled) {
                IconButton(
                    onClick = { onSaveClicked() }) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = "Save card"
                    )
                }
            } else {
                if (isExpandedScreen) {
                    IconButton(
                        onClick = { onHamBurgerMenuClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Hamburger Menu"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { displayDropDownMenu = !displayDropDownMenu }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Toolbar Menu"
                        )
                    }

                    DropdownMenu(
                        modifier = Modifier,
                        expanded = displayDropDownMenu,
                        navigateToChangeBgScreen = { navigateToChangeBgScreen("") },
                        onDismiss = { displayDropDownMenu = false }
                    )
                }
            }
        }
    )
}