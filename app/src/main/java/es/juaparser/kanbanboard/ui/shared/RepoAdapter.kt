package es.juaparser.kanbanboard.ui.shared

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.juaparser.kanbanboard.databinding.ListRepoItemBinding
import es.juaparser.kanbanboard.model.Repo

class RepoAdapter(
    val isLocal: Boolean,
    val repoList: List<Repo>,
    val onClick: () -> Unit): RecyclerView.Adapter<RepoAdapter.RepoViewHolder>() {

    private lateinit var context: Context


    inner class RepoViewHolder(private val binding: ListRepoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(repo: Repo) {

            with(binding) {

                tvTitle.text = repo.name
                tvAuthor.text = repo.author

                if(isLocal) {
                    ivAddBoard.visibility = View.GONE
                    binding.root.setOnClickListener {
                        onClick()
                    }
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        context = parent.context
        val binding = ListRepoItemBinding.inflate(layoutInflater, parent, false)
        return RepoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repoList.get(position)
        holder.bind(repo)
    }

    companion object {
        val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                // User ID serves as unique ID
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                // Compare full contents (note: Java users should call .equals())
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

}