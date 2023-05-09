package ir.miare.androidcodechallenge.feature_top_scorers.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.miare.androidcodechallenge.databinding.ItemLeagueBinding
import ir.miare.androidcodechallenge.databinding.ItemPlayerBinding
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.TopScorersListItem
import ir.miare.androidcodechallenge.feature_top_scorers.util.diff_util.TopScorersDiffCallback

class TopScorersAdapter :
    ListAdapter<TopScorersListItem, RecyclerView.ViewHolder>(TopScorersDiffCallback()) {

    companion object {
        private const val VIEW_TYPE_LEAGUE = 1
        private const val VIEW_TYPE_PLAYER = 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_LEAGUE -> LeagueViewHolder(
                ItemLeagueBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            VIEW_TYPE_PLAYER ->
                PlayerViewHolder(
                    ItemPlayerBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LeagueViewHolder) {
            holder.bind(getItem(position) as TopScorersListItem.LeagueData)
        } else if (holder is PlayerViewHolder) {
            holder.bind(getItem(position) as TopScorersListItem.PlayerData)
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if (getItem(position) is TopScorersListItem.LeagueData) VIEW_TYPE_LEAGUE else VIEW_TYPE_PLAYER
    }

    class LeagueViewHolder(private val binding: ItemLeagueBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(leagueData: TopScorersListItem.LeagueData) {

        }
    }

    class PlayerViewHolder(private val binding: ItemPlayerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(playerData: TopScorersListItem.PlayerData) {

        }
    }
}