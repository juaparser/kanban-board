package es.juaparser.kanbanboard.ui.local

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import es.juaparser.kanbanboard.R
import es.juaparser.kanbanboard.databinding.FragmentLocalBinding
import es.juaparser.kanbanboard.model.Repo
import es.juaparser.kanbanboard.ui.shared.RepoAdapter
import java.util.*

class LocalFragment : Fragment() {

    private val localViewModel: LocalViewModel by activityViewModels()
    private var _binding: FragmentLocalBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentLocalBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Lista hardcodeada con los futuros repositorios con tablero

        val repoList = mutableListOf(
            Repo(
                name = "Prueba repo",
                author = "Juan Parra Serna",
                link = "",
                created = Date(),
                issues = mutableListOf()),
            Repo(
                name = "Prueba repo2",
                author = "Juan Parra Serna",
                link = "",
                created = Date(),
                issues = mutableListOf())
        )

        binding.apply {
            val adapter = RepoAdapter(true, repoList) {
                findNavController().navigate(R.id.action_navigation_local_to_boardActivity)
            }
            rvRepos.adapter = adapter
        }

        return root
    }
}