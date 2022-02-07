package es.juaparser.kanbanboard.ui.board

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.juaparser.kanbanboard.model.BoardState
import es.juaparser.kanbanboard.model.Issue
import java.util.*

class PageViewModel : ViewModel() {

     var issueList = MutableLiveData(mutableListOf(
        Issue(title = "Backlog issue 1", number = "1001", comments = 0, date = Date(), type = BoardState.BACKLOG),
        Issue(title = "Next issue 1", number = "1002", comments = 1, date = Date(), type = BoardState.NEXT),
        Issue(title = "Doing issue 1", number = "1003", comments = 2, date = Date(), type = BoardState.DOING),
        Issue(title = "Done issue 1", number = "1004", comments = 3, date = Date(), type = BoardState.DONE)
    ))
}