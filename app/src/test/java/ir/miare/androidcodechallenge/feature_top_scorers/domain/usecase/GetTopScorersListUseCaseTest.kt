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

            val response = Response.success(unsortedList)
            `when`(mockTopScorersRepository.getTopScorersList()).thenReturn(response)

            // Act
            val result = getTopScorersListUseCase(SortingType.NONE)

            // Assert
            assertTrue(result is ApiResult.Success)
            assertThat(expectedSortedByNoneList).isEqualTo(result.data)
        }
    }
}