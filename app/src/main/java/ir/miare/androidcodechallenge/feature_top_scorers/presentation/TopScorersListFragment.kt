package ir.miare.androidcodechallenge.feature_top_scorers.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.core.util.network.ApiResult
import ir.miare.androidcodechallenge.core.util.view.gone
import ir.miare.androidcodechallenge.core.util.view.visible
import ir.miare.androidcodechallenge.databinding.FragmentTopScorersListBinding
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.SortingType
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.TopScorersListItem
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopScorersListFragment : Fragment() {

    private val viewModel: TopScorersListViewModel by viewModels()
    private lateinit var binding: FragmentTopScorersListBinding
    private lateinit var topScorersAdapter: TopScorersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTopScorersListBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.getTopScorersList(SortingType.NONE)
        setupViews()
        collectData()
    }

    private fun setupViews() {
        topScorersAdapter = TopScorersAdapter()
        binding.rcvLeague.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL, false
            )
            adapter = topScorersAdapter
        }

        binding.rdgSoring.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rdb_sort_by_ranking -> {
                    viewModel.getTopScorersList(SortingType.RANKING)
                }
                R.id.rdb_sort_by_average -> {
                    viewModel.getTopScorersList(SortingType.AVERAGE)
                }
                R.id.rdb_sort_by_most_goal -> {
                    viewModel.getTopScorersList(SortingType.MOST_GOAL)
                }
                R.id.rdb_sort_by_none -> {
                    viewModel.getTopScorersList(SortingType.NONE)
                }
            }
        }
    }
    private fun collectData() {
        lifecycleScope.launch {
            launch {
                viewModel.topScorersList.collectLatest {
                    handleApiResult(it)
                }
            }
        }
    }

    private fun handleApiResult(result: ApiResult<List<TopScorersListItem>>){
        when(result){
            is ApiResult.Loading ->{
                binding.pgbLoading.visible()
                binding.txtErrorMessage.gone()
                binding.rcvLeague.gone()
            }
            is ApiResult.Success ->{
                topScorersAdapter.submitList(result.data){
                    binding.pgbLoading.gone()
                    binding.txtErrorMessage.gone()
                    binding.rcvLeague.visible()
                }
            }
            is ApiResult.Error ->{
                binding.pgbLoading.gone()
                binding.txtErrorMessage.visible()
                binding.rcvLeague.gone()
                binding.txtErrorMessage.text = result.exception.message
            }
            else -> {}
        }
    }
}