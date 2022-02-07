package es.juaparser.kanbanboard.ui.board

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.juaparser.kanbanboard.databinding.ListIssueItemBinding
import es.juaparser.kanbanboard.databinding.ListRepoItemBinding
import es.juaparser.kanbanboard.model.BoardState
import es.juaparser.kanbanboard.model.Issue
import es.juaparser.kanbanboard.model.Repo

class IssueAdapter(
    var tabPosition: Int,
    var issueList: MutableList<Issue>,
    var next: (left: Boolean, tabPosition: Int, issue: Issue) -> Unit): RecyclerView.Adapter<IssueAdapter.IssueViewHolder>() {

    private lateinit var context: Context


    inner class IssueViewHolder(private val binding: ListIssueItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(issue: Issue) {

            with(binding) {

                tvTitle.text = issue.title
                tvDate.text = issue.date.toString()
                tvNumber.text = issue.number
                tvComments.text = issue.comments.toString()

                showArrows(this, tabPosition)

                ivMoveLeft.setOnClickListener {
                    Log.d("JPS", "MOVELEFT CLICKED")
                    next(true, tabPosition, issue)
                    issueList.remove(issue)
                    notifyItemRemoved(position)
                   /* val newState = moveLeft(tabPosition)
                    newState?.let { state -> issue.type = state }
                    notifyItemRemoved(position)*/
                }

                ivMoveRight.setOnClickListener {
                    Log.d("JPS", "MOVERIGHT CLICKED")
                    next(false, tabPosition, issue)
                    notifyItemRemoved(position)
                    /*val newState = moveRight(tabPosition)
                    newState?.let { state -> issue.type = state }
                    notifyItemRemoved(position)*/
                }


                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = ListIssueItemBinding.inflate(layoutInflater, parent, false)
        return IssueViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) {
        val repo = issueList.get(position)
        holder.bind(repo)
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

    private fun showArrows(binding: ListIssueItemBinding, tabPosition: Int) {
        binding.apply {
            when(tabPosition) {
                1 -> {
                    ivMoveLeft.visibility = View.INVISIBLE
                    ivMoveRight.visibility = View.VISIBLE
                }
                4 -> {
                    ivMoveLeft.visibility = View.VISIBLE
                    ivMoveRight.visibility = View.INVISIBLE
                }
                else -> {
                    ivMoveLeft.visibility = View.VISIBLE
                    ivMoveRight.visibility = View.VISIBLE
                }
            }
        }
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Issue>() {
            override fun areItemsTheSame(oldItem: Issue, newItem: Issue): Boolean =
                // User ID serves as unique ID
                oldItem.number == newItem.number

            override fun areContentsTheSame(oldItem: Issue, newItem: Issue): Boolean =
                // Compare full contents (note: Java users should call .equals())
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return issueList.size
    }

}