package ir.miare.androidcodechallenge.feature_top_scorers.domain.entity

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable


data class FakeData(
    @JsonProperty("league") var league: League,
    @JsonProperty("players") var players: List<Player>
)

data class League(
    @JsonProperty("name") val name: String,
    @JsonProperty("country") val country: String,
    @JsonProperty("rank") val rank: Int,
    @JsonProperty("total_matches") val totalMatches: Int,
)

data class Player(
    @JsonProperty("name") val name: String,
    @JsonProperty("team") val team: Team,
    @JsonProperty("total_goal") val totalGoal: Int
) : Serializable

data class Team(
    @JsonProperty("name") val name: String,
    @JsonProperty("rank") val rank: Int
) : Serializable



sealed class TopScorersListItem {
    data class LeagueData(val league: League) : TopScorersListItem()
    data class PlayerData(val player: Player) : TopScorersListItem()
}
