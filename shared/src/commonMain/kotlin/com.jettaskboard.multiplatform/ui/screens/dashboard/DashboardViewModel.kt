package com.jettaskboard.multiplatform.ui.screens.dashboard

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.jettaskboard.multiplatform.data.fakes.BoardList
import com.jettaskboard.multiplatform.domain.model.Board
import com.jettaskboard.multiplatform.domain.model.BoardListModel
import com.jettaskboard.multiplatform.util.Result
import com.jettaskboard.multiplatform.util.krouter.ViewModel
import com.jettaskboard.multiplatform.util.successOr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    var toggleDrawerContent = mutableStateOf(true)

    private val _listOfBoards: MutableList<Board> = mutableStateListOf()
    val listOfBoards: List<Board> = _listOfBoards

    /**
     * A list of Boards to show on the dashboard
     */
    fun getBoardListData() {
        CoroutineScope(coroutineContext).launch {
            // Trigger repository requests in parallel
            val boardDeferred = async { getFakeBoardList() }

            // Wait for all requests to finish
            val boardList = boardDeferred.await().successOr(BoardListModel())
            if (_listOfBoards.isEmpty()) {
                _listOfBoards.addAll(boardList.listOfBoards)
            }
        }
    }

    private fun getFakeBoardList(): Result<BoardListModel> {
        return Result.Success(BoardList)
    }
}
