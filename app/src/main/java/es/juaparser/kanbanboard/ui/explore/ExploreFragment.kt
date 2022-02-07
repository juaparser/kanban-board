package es.juaparser.kanbanboard.ui.explore

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import es.juaparser.kanbanboard.databinding.FragmentExploreBinding
import es.juaparser.kanbanboard.model.Repo
import es.juaparser.kanbanboard.ui.shared.RepoAdapter
import java.util.*

import androidx.recyclerview.widget.LinearLayoutManager




class ExploreFragment : Fragment() {

    private val exploreViewModel: ExploreViewModel by activityViewModels()
    private var _binding: FragmentExploreBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Lista hardcodeada con los futuros repositorios para añadirlos a la lista
        val repoList = mutableListOf(
            Repo(
                name = "Prueba repo",
                author = "Juan Parra Serna",
                link = "",
                created = Date(),
                issues = mutableListOf()),
            Repo(
                name = "Prueba repo",
                author = "Juan Parra Serna",
                link = "",
                created = Date(),
                issues = mutableListOf())
        )

        Log.d("EXPLOREFRAGMENT","JPS PRE ADAPTER SET")

        binding.apply {
            val adapter = RepoAdapter(false, repoList) {
                // Nothing
            }

            Log.d("EXPLOREFRAGMENT","JPS AFTER REPO LIST  $repoList")
            rvRepos.adapter = adapter
        }

        return root
    }

}