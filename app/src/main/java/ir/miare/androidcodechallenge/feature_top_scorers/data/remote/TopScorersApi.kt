package ir.miare.androidcodechallenge.feature_top_scorers.data.remote

import ir.logicbase.mockfit.Mock
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import retrofit2.Response
import retrofit2.http.GET

interface TopScorersApi {
    @Mock("data2.json")
    @GET("list")
    suspend fun getTopScorersList(): Response<List<FakeData>>
}