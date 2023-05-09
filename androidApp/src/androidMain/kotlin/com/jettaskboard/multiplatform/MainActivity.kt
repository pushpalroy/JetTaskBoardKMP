package com.jettaskboard.multiplatform

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jettaskboard.multiplatform.di.initKoin
import com.jettaskboard.multiplatform.ui.theme.dark_grey
import com.jettaskboard.multiplatform.util.krouter.LocalComponentContext
import org.koin.android.ext.koin.androidContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        val rootComponentContext: DefaultComponentContext = defaultComponentContext()

        initKoin {
            androidContext(applicationContext)
        }

        setContent {
            CompositionLocalProvider(LocalComponentContext provides rootComponentContext) {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = dark_grey,
                        darkIcons = false
                    )
                }
                MainView()
            }
        }
    }
}