package ir.miare.androidcodechallenge.feature_top_scorers.presentation

import androidx.navigation.NavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import ir.miare.androidcodechallenge.R
import ir.miare.androidcodechallenge.core.presentation.MainActivity
import ir.miare.androidcodechallenge.feature_top_scorers.domain.EXPECTED_ITEM_SORTED_BY_NONE
import ir.miare.androidcodechallenge.feature_top_scorers.domain.TEST_ITEM_INDEX
import ir.miare.androidcodechallenge.feature_top_scorers.util.atPositionOnView
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


@HiltAndroidTest
@ExperimentalCoroutinesApi
class TopScorersListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var mActivityRule = ActivityScenarioRule(MainActivity::class.java)


    @Before
    fun setup() {
        hiltRule.inject()
        val navController = mock(NavController::class.java)
        navController.navigate(R.id.topScorersListFragment)
    }

    @Test
    fun navigateTopScorersFragment_shouldDisplayRecyclerview() {
        onView(withId(R.id.rcv_league)).check(matches(isDisplayed()))
    }

    @Test
    fun defaultSortType_shouldDisplayExpectedItemSortedByNone_atTextItemIndex() {
        onView(withId(R.id.rcv_league))
            .perform(
                RecyclerViewActions.scrollToPosition<TopScorersAdapter.PlayerViewHolder>(
                    TEST_ITEM_INDEX
                )
            )
            .check(
                matches(
                    atPositionOnView(
                        TEST_ITEM_INDEX,
                        withText(EXPECTED_ITEM_SORTED_BY_NONE.player.name),
                        R.id.player_name
                    )
                )
            )
    }

}