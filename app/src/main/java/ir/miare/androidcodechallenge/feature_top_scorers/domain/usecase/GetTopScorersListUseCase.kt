package ir.miare.androidcodechallenge.feature_top_scorers.domain.usecase

import ir.miare.androidcodechallenge.core.di.qualifiers.IoDispatcher
import ir.miare.androidcodechallenge.core.util.network.ApiResult
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.TopScorersListItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTopScorersListUseCase @Inject constructor(
    private val topScorersRepository: TopScorersRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(): ApiResult<List<TopScorersListItem>> {
        return try {
            withContext(ioDispatcher) {
                topScorersRepository.getTopScorersList().let {
                    if (it.isSuccessful) {
                        val list = mutableListOf<TopScorersListItem>()
                        val result = it.body()!!
                        result.forEach { item ->
                            list.add(TopScorersListItem.LeagueData(item.league))
                            list.addAll(item.players.mapIndexed { index, player ->
                                TopScorersListItem.PlayerData(
                                    player,
                                    index + 1
                                )
                            })
                        }
                        ApiResult.Success(list)
                    } else {
                        ApiResult.Error(Exception("Network Error"))
                    }
                }
            }
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
    }
}
