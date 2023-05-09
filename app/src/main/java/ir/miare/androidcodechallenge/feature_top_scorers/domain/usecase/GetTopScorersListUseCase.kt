package ir.miare.androidcodechallenge.feature_top_scorers.domain.usecase

import ir.miare.androidcodechallenge.core.di.qualifiers.IoDispatcher
import ir.miare.androidcodechallenge.core.domain.common.usecase.ApiUseCase
import ir.miare.androidcodechallenge.core.util.network.ApiResult
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.SortingType
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.TopScorersListItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class GetTopScorersListUseCase @Inject constructor(
    private val topScorersRepository: TopScorersRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(sortingType: SortingType): ApiResult<List<TopScorersListItem>> {
        return try {
            withContext(ioDispatcher) {
                topScorersRepository.getTopScorersList().let {
                    if (it.isSuccessful) {
                        ApiResult.Success(sortedList(sortingType, it.body()!!))
                    } else {
                        ApiResult.Error(Exception("Network Error"))
                    }
                }
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }

    private fun sortedList(
        sortingType: SortingType,
        unsortedList: List<FakeData>
    ): List<TopScorersListItem> {
        val list = mutableListOf<TopScorersListItem>()
        return when (sortingType) {
            SortingType.RANKING -> {
                unsortedList.sortedBy { item -> item.league.rank }.forEach { item ->
                    list.add(TopScorersListItem.LeagueData(item.league))
                    list.addAll(item.players.sortedBy { player -> player.team.rank }
                        .mapIndexed { index, player -> TopScorersListItem.PlayerData(player, index+1) }
                    )
                }
                list
            }
            SortingType.MOST_GOAL -> {
                unsortedList.forEach { item ->
                    list.add(TopScorersListItem.LeagueData(item.league))
                    list.addAll(item.players.sortedByDescending { player -> player.totalGoal }
                        .mapIndexed { index, player -> TopScorersListItem.PlayerData(player, index+1) })
                }
                list
            }
            SortingType.AVERAGE -> {
                unsortedList.sortedByDescending { item ->
                    val totalGoals: Double = item.players.sumOf { player ->
                        player.totalGoal.toDouble()
                    }
                    totalGoals / item.league.totalMatches
                }.forEach { item ->
                    list.add(TopScorersListItem.LeagueData(item.league))
                    list.addAll(item.players.sortedByDescending { player -> player.totalGoal }
                        .mapIndexed { index, player -> TopScorersListItem.PlayerData(player, index+1) })
                }
                list
            }
            SortingType.NONE -> {
                unsortedList.forEach { item ->
                    list.add(TopScorersListItem.LeagueData(item.league))
                    list.addAll(item.players.mapIndexed { index, player -> TopScorersListItem.PlayerData(player, index+1) })
                }
                list
            }
        }
    }
}
