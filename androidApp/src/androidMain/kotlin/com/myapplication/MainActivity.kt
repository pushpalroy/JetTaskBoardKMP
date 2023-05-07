package com.myapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.decompose.defaultComponentContext
import com.jettaskboard.multiplatform.MainView
import com.jettaskboard.multiplatform.di.initKoin
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
                MainView()
            }
        }
    }
}