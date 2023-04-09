package ir.miare.androidcodechallenge.feature_top_scorers.data.repository

import ir.miare.androidcodechallenge.feature_top_scorers.data.remote.TopScorersApi
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class TopScorersRepositoryImpl @Inject constructor(
    private val topScorersApi: TopScorersApi,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : TopScorersRepository {
    override suspend fun getTopScorersList(): Response<List<FakeData>> =
        withContext(ioDispatcher) {
            topScorersApi.getTopScorersList()
        }
}