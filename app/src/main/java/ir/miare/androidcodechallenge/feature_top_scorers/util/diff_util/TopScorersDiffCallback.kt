package ir.miare.androidcodechallenge.feature_top_scorers.util.diff_util

import androidx.recyclerview.widget.DiffUtil
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.TopScorersListItem

class TopScorersDiffCallback : DiffUtil.ItemCallback<TopScorersListItem>() {
    override fun areItemsTheSame(oldItem: TopScorersListItem, newItem: TopScorersListItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: TopScorersListItem, newItem: TopScorersListItem): Boolean {
        return oldItem == newItem
    }
}