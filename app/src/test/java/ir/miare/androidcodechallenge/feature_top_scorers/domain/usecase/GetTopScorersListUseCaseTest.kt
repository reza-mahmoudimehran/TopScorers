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
import org.mockito.BDDMockito.given
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
    fun `when repository returns successful response, it should return success result and unsorted list`() {
        runTest {

            val response = Response.success(unsortedList)
            `when`(mockTopScorersRepository.getTopScorersList()).thenReturn(response)

            // Act
            val result = getTopScorersListUseCase(SortingType.NONE)

            // Assert
            assertTrue(result is ApiResult.Success)
            assertThat(expectedSortedByNoneList).isEqualTo(result.data)
        }
    }

    @Test
    fun `invoke by RANKING sort type, should return list sorted by league and player's team ranking`() {
        runTest {

            val response = Response.success(unsortedList)
            `when`(mockTopScorersRepository.getTopScorersList()).thenReturn(response)

            // Act
            val result = getTopScorersListUseCase(SortingType.RANKING)

            // Assert
            assertTrue(result is ApiResult.Success)
            assertThat(expectedSortedByRankList).isEqualTo(result.data)
        }
    }

    @Test
    fun `invoke by AVERAGE sort type, should return list sorted by league average goal`() {
        runTest {

            val response = Response.success(unsortedList)
            `when`(mockTopScorersRepository.getTopScorersList()).thenReturn(response)

            // Act
            val result = getTopScorersListUseCase(SortingType.AVERAGE)

            // Assert
            assertTrue(result is ApiResult.Success)
            assertThat(expectedSortedByAverageList).isEqualTo(result.data)
        }
    }

    @Test
    fun `invoke by MOST_GOAL sort type, should return list sorted by player total goal`() {
        runTest {

            val response = Response.success(unsortedList)
            `when`(mockTopScorersRepository.getTopScorersList()).thenReturn(response)

            // Act
            val result = getTopScorersListUseCase(SortingType.MOST_GOAL)

            // Assert
            assertTrue(result is ApiResult.Success)
            assertThat(expectedSortedByMostGoalList).isEqualTo(result.data)
        }
    }



    @Test
    fun `when repository throws an exception, then use case should return Error with same exception`() {
        runTest {
            // Given
            given(mockTopScorersRepository.getTopScorersList()).willThrow(RuntimeException())

            // When
            val result = getTopScorersListUseCase(SortingType.NONE)

            // Assert
            assertTrue(result is ApiResult.Error)
            assertThat((result as ApiResult.Error).exception).isInstanceOf(RuntimeException::class.java)
        }
    }
}