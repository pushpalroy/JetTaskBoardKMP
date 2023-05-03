package com.jettaskboard.multiplatform.data.source.local.preferences

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.getStringFlow


@OptIn(ExperimentalSettingsApi::class)
class UserPreferences {

    private val settings = Settings() as ObservableSettings

    private object PreferencesKeys {
        const val BOARD_BG = "board_background"
    }

    val boardBackground = settings.getStringFlow(
        PreferencesKeys.BOARD_BG,
        "https://images.unsplash.com/photo-1523895665936-7bfe172b757d"
    )

    fun updateBoardBackground(ImageUri: String) {
        settings.putString(PreferencesKeys.BOARD_BG, ImageUri)
    }

    fun clear() {
        settings.clear()
    }
}