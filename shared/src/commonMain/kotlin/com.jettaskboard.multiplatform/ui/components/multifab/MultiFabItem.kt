package com.jettaskboard.multiplatform.ui.components.multifab

/**
 * @param id Cannot be duplicated with the [id] value of another [MultiFabItem].
 */
data class MultiFabItem(
    val id: Int,
    val iconRes: String,
    val label: String = ""
)
