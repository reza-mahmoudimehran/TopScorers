package ir.miare.androidcodechallenge.feature_top_scorers.domain.usecase

import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.*

val unsortedList = listOf(
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

val expectedSortedByNoneList = listOf(
    TopScorersListItem.LeagueData(League("Premier League", "England", 1, 38)),
    TopScorersListItem.PlayerData(Player("Harry Kane", Team("Tottenham Hotspur", 6), 18), 1),
    TopScorersListItem.PlayerData(Player("Mohamed Salah", Team("Liverpool", 2), 22), 2),
    TopScorersListItem.LeagueData(League("La Liga", "Spain", 3, 38)),
    TopScorersListItem.PlayerData(Player("Karim Benzema", Team("Real Madrid", 2), 22), 1),
    TopScorersListItem.PlayerData(Player("Lionel Messi", Team("Barcelona", 1), 30), 2),
)

val expectedSortedByMostGoalList = listOf(
    TopScorersListItem.LeagueData(League("Premier League", "England", 1, 38)),
    TopScorersListItem.PlayerData(Player("Mohamed Salah", Team("Liverpool", 2), 22), 1),
    TopScorersListItem.PlayerData(Player("Harry Kane", Team("Tottenham Hotspur", 6), 18), 2),
    TopScorersListItem.LeagueData(League("La Liga", "Spain", 3, 38)),
    TopScorersListItem.PlayerData(Player("Lionel Messi", Team("Barcelona", 1), 30), 1),
    TopScorersListItem.PlayerData(Player("Karim Benzema", Team("Real Madrid", 2), 22), 2),
)


val expectedSortedByAverageList = listOf(
    TopScorersListItem.LeagueData(League("La Liga", "Spain", 3, 38)),
    TopScorersListItem.PlayerData(Player("Lionel Messi", Team("Barcelona", 1), 30), 1),
    TopScorersListItem.PlayerData(Player("Karim Benzema", Team("Real Madrid", 2), 22), 2),
    TopScorersListItem.LeagueData(League("Premier League", "England", 1, 38)),
    TopScorersListItem.PlayerData(Player("Mohamed Salah", Team("Liverpool", 2), 22), 1),
    TopScorersListItem.PlayerData(Player("Harry Kane", Team("Tottenham Hotspur", 6), 18), 2),
)


val expectedSortedByRankList = listOf(
    TopScorersListItem.LeagueData(League("Premier League", "England", 1, 38)),
    TopScorersListItem.PlayerData(Player("Mohamed Salah", Team("Liverpool", 2), 22), 1),
    TopScorersListItem.PlayerData(Player("Harry Kane", Team("Tottenham Hotspur", 6), 18), 2),
    TopScorersListItem.LeagueData(League("La Liga", "Spain", 3, 38)),
    TopScorersListItem.PlayerData(Player("Lionel Messi", Team("Barcelona", 1), 30), 1),
    TopScorersListItem.PlayerData(Player("Karim Benzema", Team("Real Madrid", 2), 22), 2),
)


