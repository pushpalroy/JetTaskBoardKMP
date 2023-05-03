package com.jettaskboard.multiplatform.util.krouter

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

actual open class ViewModel : InstanceKeeper.Instance, CoroutineScope, KoinComponent {
    actual override val coroutineContext: CoroutineContext = Dispatchers.Main + SupervisorJob()
    override fun onDestroy() {
        coroutineContext.cancel()
    }
}