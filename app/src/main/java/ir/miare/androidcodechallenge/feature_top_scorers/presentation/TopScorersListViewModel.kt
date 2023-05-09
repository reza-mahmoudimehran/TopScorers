package ir.miare.androidcodechallenge.feature_top_scorers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.miare.androidcodechallenge.core.util.network.ApiResult
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.SortingType
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.TopScorersListItem
import ir.miare.androidcodechallenge.feature_top_scorers.domain.usecase.GetTopScorersListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopScorersListViewModel @Inject constructor(
    private val getTopScorersListUseCase: GetTopScorersListUseCase
): ViewModel(){

    private val _topScorersList = MutableStateFlow<ApiResult<List<TopScorersListItem>>>(ApiResult.Init)
    val topScorersList: StateFlow<ApiResult<List<TopScorersListItem>>>
        get() = _topScorersList


    fun getTopScorersList(sortingType: SortingType){
        viewModelScope.launch {
            _topScorersList.value = getTopScorersListUseCase(sortingType)
        }
    }
}