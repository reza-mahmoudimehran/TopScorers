package ir.miare.androidcodechallenge.feature_top_scorers.domain

import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.*

val UNSORTED_LIST = listOf(
    FakeData(
        League("Premier League", "England", 1, 38),
        listOf(
            Player("Harry Kane", Team("Tottenham Hotspur", 6), 18),
            Player("Mohamed Salah", Team("Liverpool", 2), 22),
        )
    ),
    FakeData(
        League("La Liga", "Spain", 3, 38),
        listOf(
            Player("Karim Benzema", Team("Real Madrid", 2), 22),
            Player("Lionel Messi", Team("Barcelona", 1), 30),
        )
    )
)

val TEST_ITEM_INDEX = 1

val EXPECTED_ITEM_SORTED_BY_NONE = TopScorersListItem.PlayerData(Player("Harry Kane", Team("Tottenham Hotspur", 6), 18), 1)
val EXPECTED_ITEM_SORTED_BY_MOST_GOAL = TopScorersListItem.PlayerData(Player("Mohamed Salah", Team("Liverpool", 2), 22), 1)
val EXPECTED_ITEM_SORTED_BY_AVERAGE = TopScorersListItem.PlayerData(Player("Lionel Messi", Team("Barcelona", 1), 30), 1)
val EXPECTED_ITEM_SORTED_BY_RANK = TopScorersListItem.PlayerData(Player("Mohamed Salah", Team("Liverpool", 2), 22), 1)


