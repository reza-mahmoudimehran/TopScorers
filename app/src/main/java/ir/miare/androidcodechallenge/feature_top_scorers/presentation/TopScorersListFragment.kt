package ir.miare.androidcodechallenge.feature_top_scorers.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.core.util.network.data
import ir.miare.androidcodechallenge.databinding.FragmentTopScorersListBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopScorersListFragment : Fragment() {

    private val viewModel: TopScorersListViewModel by viewModels()
    private lateinit var binding: FragmentTopScorersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopScorersListBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getTopScorersList()
        collectData()
    }

    private fun collectData() {
        lifecycleScope.launch {
            launch {
                viewModel.topScorersList.collectLatest {

                }
            }
        }
    }
}