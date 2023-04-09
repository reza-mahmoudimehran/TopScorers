package ir.miare.androidcodechallenge.feature_top_scorers.domain.usecase

import ir.miare.androidcodechallenge.core.di.qualifiers.IoDispatcher
import ir.miare.androidcodechallenge.core.domain.common.usecase.ApiUseCase
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.FakeData
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Response
import javax.inject.Inject

class GetTopScorersListUseCase @Inject constructor(
    private val topScorersRepository: TopScorersRepository,
    @IoDispatcher ioDispatcher: CoroutineDispatcher
) : ApiUseCase<Unit, List<FakeData>>(
    ioDispatcher
) {
    override suspend fun execute(parameters: Unit): Response<List<FakeData>> =
        topScorersRepository.getTopScorersList()
}
