package ir.miare.androidcodechallenge.feature_top_scorers.domain.usecase

import com.google.common.truth.Truth.assertThat
import ir.miare.androidcodechallenge.core.util.network.ApiResult
import ir.miare.androidcodechallenge.core.util.network.data
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TopScorersRepository
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetTopScorersListUseCaseTest {

    private val testDispatcher: TestDispatcher by lazy { StandardTestDispatcher() }

    @Mock
    private lateinit var mockTopScorersRepository: TopScorersRepository

    private lateinit var getTopScorersListUseCase: GetTopScorersListUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getTopScorersListUseCase =
            GetTopScorersListUseCase(mockTopScorersRepository, testDispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when repository returns successful response, it should return success result`() {
        runTest {
            // Given
            val fakeData = listOf(
                FakeData(
                    league = League("Premier League", "England", 1, 38),
                    players = listOf(
                        Player("Mohamed Salah", Team("Liverpool", 1), 32),
                        Player("Harry Kane", Team("Tottenham", 2), 23),
                    )
                )
            )
            // Given
            val fakeListItems = listOf(
                TopScorersListItem.LeagueData(League("Premier League", "England", 1, 38)),
                TopScorersListItem.PlayerData(
                    Player("Mohamed Salah", Team("Liverpool", 1), 32),
                    1
                ),
                TopScorersListItem.PlayerData(
                    Player("Harry Kane", Team("Tottenham", 2), 23),
                    2
                ),
            )

            val response = Response.success(fakeData)
            `when`(mockTopScorersRepository.getTopScorersList()).thenReturn(response)

            // Act
            val result = getTopScorersListUseCase(SortingType.NONE)

            // Assert
            assertTrue(result is ApiResult.Success)
            assertThat(fakeListItems).isEqualTo(result.data)
        }
    }
}