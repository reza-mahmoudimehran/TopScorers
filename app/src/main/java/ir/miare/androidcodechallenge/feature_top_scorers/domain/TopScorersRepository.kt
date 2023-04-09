package ir.miare.androidcodechallenge.feature_top_scorers.domain

import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import retrofit2.Response

interface TopScorersRepository {
    suspend fun getTopScorersList(): Response<List<FakeData>>
}