package ir.miare.androidcodechallenge.feature_top_scorers.data.repository

import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import retrofit2.Response

class TopScorersRepositoryImpl : TopScorersRepository {
    override suspend fun getTopScorersList(): Response<List<FakeData>> {
        TODO("Not yet implemented")
    }

}