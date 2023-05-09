package com.jettaskboard.multiplatform.ui.screens.board

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.jettaskboard.multiplatform.data.fakes.Board
import com.jettaskboard.multiplatform.data.source.local.preferences.UserPreferences
import com.jettaskboard.multiplatform.domain.model.BoardModel
import com.jettaskboard.multiplatform.domain.model.CardModel
import com.jettaskboard.multiplatform.domain.model.ListModel
import com.jettaskboard.multiplatform.util.Result
import com.jettaskboard.multiplatform.util.krouter.ViewModel
import com.jettaskboard.multiplatform.util.successOr
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class TaskBoardViewModel : ViewModel() {

    /**
     * Holds the information of the [Board], a pair of ID and title.
     */
    var boardInfo = mutableStateOf<Pair<Int?, String>>(Pair(null, ""))
        private set

    /**
     * Defines a list of [ListModel]s which has a list of [CardModel]s internally.
     * The list of [ListModel]s is used to setup the row of lists (eg: To-do, InProgress, Completed, etc.) in the [Board].
     * The list of [CardModel]s inside every list, is used to setup the column of cards in a particular list.
     */
    private val _lists = mutableStateListOf<ListModel>()
    val lists: List<ListModel> = _lists

    /**
     * A counter of the total number of cards in the board, maintained to assign a unique
     * index everytime a new card gets added to the board in a particular list, which in also
     * used to run recomposition whenever the new card is added.
     */
    var totalCards by mutableStateOf(0)

    private var _drawerScreenState = mutableStateOf(ExpandedBoardDrawerState.DRAWER_SCREEN_STATE)
    val drawerScreenState: State<ExpandedBoardDrawerState> = _drawerScreenState

    private val preferences: UserPreferences by inject()
    val boardBackground by mutableStateOf(preferences.boardBackground)

    fun updateBoardBackground(imageUri: String) =
        CoroutineScope(coroutineContext).launch {
            preferences.updateBoardBackground(imageUri)
        }

    init {
        getBoardData()
    }

    /**
     * A Board has a list of Lists: Board = f(List)
     * A List has a list of Cards: List = f(Card)
     * A new Card has to be inserted in the list Cards of a Board
     */
    private fun getBoardData() = CoroutineScope(coroutineContext).launch {
        // Trigger repository requests in parallel
        val boardDeferred = async { getFakeBoard() }

        // Wait for all requests to finish
        val board = boardDeferred.await().successOr(BoardModel())

        boardInfo.value = Pair(board.id, board.title)

        // Executed for once when ui is loaded
        if (_lists.isEmpty()) {
            _lists.addAll(board.lists)
            _lists.forEach { list ->
                totalCards += list.cards.size
            }
        }
    }

    fun addNewCardInList(listId: Int) = CoroutineScope(coroutineContext).launch {
        totalCards += 1
        val newCardIndex = totalCards
        _lists.find { it.id == listId }?.cards?.add(
            CardModel(id = newCardIndex, title = "", listId = listId)
        )
    }

    fun editCardInList(cardId: Int, listId: Int, title: String) =
        CoroutineScope(coroutineContext).launch {
            // Locate the card to be edited
            val cardToEdit = _lists.find { it.id == listId }?.cards?.find { it.id == cardId }
            cardToEdit?.let { safeCardToEdit ->
                // Remove card from list
                _lists.find { it.id == listId }?.cards?.removeAll { it.id == cardId }
                totalCards -= 1
                // Place a copy of same card with new added data to the same list
                _lists.find { it.id == listId }?.cards?.add(
                    safeCardToEdit.copy(
                        listId = listId,
                        title = title
                    )
                )
                totalCards += 1
            }
        }

    fun moveCardToDifferentList(
        cardId: Int,
        oldListId: Int,
        newListId: Int
    ) = CoroutineScope(coroutineContext).launch {
        // Locate the card to be moved
        val cardToMove = _lists.find { it.id == oldListId }?.cards?.find { it.id == cardId }

        // Removing a card from one list and adding to a new list
        // Basically, modifying the internal data of two list-items simultaneously
        cardToMove?.let { safeCard ->
            _lists.find { it.id == oldListId }?.cards?.removeAll { it.id == safeCard.id }
            totalCards -= 1
            _lists.find { it.id == newListId }?.cards?.add(safeCard.copy(listId = newListId))
            totalCards += 1
        }
    }

    fun addNewList() = _lists.add(ListModel(id = _lists.size + 1, title = "New List"))


    private fun getFakeBoard(): Result<BoardModel> = Result.Success(Board)

    fun changeExpandedScreenState(newState: ExpandedBoardDrawerState) {
        _drawerScreenState.value = newState
    }
}
