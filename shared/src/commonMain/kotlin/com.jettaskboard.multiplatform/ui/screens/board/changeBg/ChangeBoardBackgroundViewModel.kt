package com.jettaskboard.multiplatform.ui.screens.board.changeBg

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.jettaskboard.multiplatform.domain.model.ChangeBackgroundPhotoModel
import com.jettaskboard.multiplatform.domain.usecase.GetRandomPhotoListUseCase
import com.jettaskboard.multiplatform.domain.usecase.SearchPhotoListUseCase
import com.jettaskboard.multiplatform.ui.util.UIState
import com.jettaskboard.multiplatform.util.Result
import com.jettaskboard.multiplatform.util.krouter.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch
import org.koin.core.component.inject

@OptIn(FlowPreview::class)
class ChangeBoardBackgroundViewModel : ViewModel() {

    private val getRandomPhotoListUseCase: GetRandomPhotoListUseCase by inject()
    private val searchPhotoListUseCase: SearchPhotoListUseCase by inject()

    private var _screenState = mutableStateOf(ChangeBackgroundScreenState.STATIC_SCREEN)
    val state: State<ChangeBackgroundScreenState> = _screenState

    private var _randomPhotoList =
        mutableStateOf<UIState<List<ChangeBackgroundPhotoModel>>>(UIState.Loading)
    var randomPhotoList = _randomPhotoList

    private val _textSearch = MutableStateFlow<String?>(null)
    val textSearch: StateFlow<String?> = _textSearch.asStateFlow()

    init {
        CoroutineScope(coroutineContext).launch {
            _textSearch.debounce(1600).collect { searchQuery ->
                searchQuery?.let { safeQueryString ->
                    _randomPhotoList.value = UIState.Loading
                    when (val result = searchPhotoListUseCase.invoke(query = safeQueryString)) {
                        is Result.Error -> {
                            _randomPhotoList.value = UIState.Failure(Throwable(result.exception))
                        }

                        is Result.Success<*> -> {
                            result.data?.let { modelList ->
                                _randomPhotoList.value =
                                    UIState.Success(modelList as List<ChangeBackgroundPhotoModel>)
                            }
                        }
                    }
                }
            }
        }
    }

    fun changeScreenState(newState: ChangeBackgroundScreenState) {
        _screenState.value = newState
    }

    fun generateRandomPhotoList(collectionId: Int) = CoroutineScope(coroutineContext).launch {
        _randomPhotoList.value = UIState.Loading
        when (val result = getRandomPhotoListUseCase.invoke(collectionId)) {
            is Result.Error -> {
                _randomPhotoList.value = UIState.Failure(result.exception)
            }

            is Result.Success<*> -> {
                result.data?.let { modelList ->
                    _randomPhotoList.value =
                        UIState.Success(modelList as List<ChangeBackgroundPhotoModel>)
                }
            }
        }
    }

    fun setSearchText(updatedQuery: String) {
        _textSearch.value = updatedQuery
    }
}