package com.jettaskboard.multiplatform.ui.components.multifab

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
interface FabIcon {
    @Stable
    val iconRes: String

    @Stable
    val iconRotate: Float?
}

private class FabIconImpl(
    override val iconRes: String,
    override val iconRotate: Float?
) : FabIcon

/**
 * Affects the main fab icon.
 *
 * @param iconRes [MultiFloatingActionButton]'s main icon
 * @param iconRotate if is not null, the [iconRes] rotates as much as [iconRotate] when [MultiFloatingActionButton] is in [MultiFabState.Expand] state.
 */
fun FabIcon(
    iconRes: String,
    iconRotate: Float? = null
): FabIcon = FabIconImpl(iconRes = iconRes, iconRotate = iconRotate)
