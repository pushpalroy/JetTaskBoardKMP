package com.jettaskboard.multiplatform.util.krouter

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 * From xxFast's Krouter library
 */
expect open class ViewModel() : InstanceKeeper.Instance, CoroutineScope {
    override val coroutineContext: CoroutineContext
}