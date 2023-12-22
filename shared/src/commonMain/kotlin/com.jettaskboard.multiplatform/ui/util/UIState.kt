package com.jettaskboard.multiplatform.ui.util

sealed class UIState<out T> {
    data object Empty : UIState<Nothing>()
    data object Loading : UIState<Nothing>()
    class Success<T>(val data: T) : UIState<T>()
    class Failure(val throwable: Throwable) : UIState<Nothing>()
}
