package ir.miare.androidcodechallenge.feature_top_scorers.data

import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.UNSORTED_LIST
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import retrofit2.Response
import javax.inject.Inject

class FakeTopScorersRepository @Inject constructor() : TopScorersRepository {
    private val fakeDataList:List<FakeData> = UNSORTED_LIST

    override suspend fun getTopScorersList(): Response<List<FakeData>> =
        Response.success(fakeDataList)
}