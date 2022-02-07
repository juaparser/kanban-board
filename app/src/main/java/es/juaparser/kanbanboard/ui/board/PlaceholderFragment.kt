package es.juaparser.kanbanboard.ui.board

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import es.juaparser.kanbanboard.databinding.FragmentBoardBinding
import es.juaparser.kanbanboard.model.BoardState
import es.juaparser.kanbanboard.model.Issue
import java.util.*


class PlaceholderFragment : Fragment() {

    private val pageViewModel: PageViewModel by activityViewModels()
    private var _binding: FragmentBoardBinding? = null

    private val binding get() = _binding!!

    private var issueList = mutableListOf(
        Issue(title = "Backlog issue 1", number = "1001", comments = 0, date = Date(), type = BoardState.BACKLOG),
        Issue(title = "Next issue 1", number = "1002", comments = 1, date = Date(), type = BoardState.NEXT),
        Issue(title = "Doing issue 1", number = "1003", comments = 2, date = Date(), type = BoardState.DOING),
        Issue(title = "Done issue 1", number = "1004", comments = 3, date = Date(), type = BoardState.DONE)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBoardBinding.inflate(inflater, container, false)
        val root = binding.root

        binding.apply {
            pageViewModel.issueList.observe(viewLifecycleOwner) {
                rvBoard.adapter = getAdapter()
            }

        }
        return root
    }

    private fun getAdapter(): IssueAdapter {
        val filterList = arguments?.getInt(ARG_SECTION_NUMBER)?.let { getIssues(it) }?.toMutableList()
        val adapter = IssueAdapter(arguments?.getInt(ARG_SECTION_NUMBER)!!,
            filterList!!
        ) { left, tabPosition, issue ->
            Log.d("JPS","CALLBACK ADAPTER")
            //pageViewModel.issueList.value?.remove(issue)
            val state = if(left) moveLeft(tabPosition) else moveRight(tabPosition)
            issue.type = state!!
            binding.rvBoard.adapter?.notifyDataSetChanged()
            //pageViewModel.issueList.value?.add(issue)
        }
        return adapter
    }

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    private fun getIssues(section: Int): List<Issue> {
        var res = listOf<Issue>()

        when(section) {
            1 -> res = pageViewModel.issueList.value!!.filter { x -> x.type == BoardState.BACKLOG }
            2 -> res = pageViewModel.issueList.value!!.filter { x -> x.type == BoardState.NEXT }
            3 -> res = pageViewModel.issueList.value!!.filter { x -> x.type == BoardState.DOING }
            4 -> res = pageViewModel.issueList.value!!.filter { x -> x.type == BoardState.DONE }
        }

        return res
    }

    private fun moveLeft(tabPosition: Int): BoardState? {
        return when(tabPosition) {
            1 -> {
                // No se puede mover más a la izquierda del backlog
                null
            }
            2 -> BoardState.BACKLOG
            3 -> BoardState.NEXT
            4 -> BoardState.DOING
            else -> null
        }
    }

    private fun moveRight(tabPosition: Int): BoardState? {
        return when(tabPosition) {
            1 -> BoardState.NEXT
            2 -> BoardState.DOING
            3 -> BoardState.DONE
            4 -> {
                // No se puede mover más a la derecha de done
                null
            }
            else -> null
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}