package ir.miare.androidcodechallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ir.miare.androidcodechallenge.databinding.BottomSheetPlayerInfoBinding
import ir.miare.androidcodechallenge.feature_top_scorers.domain.entity.Player

class PlayerInfoBottomSheet(
    val player: Player
) : BottomSheetDialogFragment() {

    private var binding: BottomSheetPlayerInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_player_info, container, false)
        binding = BottomSheetPlayerInfoBinding.bind(view)

        binding!!.tvPlayerName.text = player.name
        binding!!.tvTeamName.text = player.team.name
        binding!!.tvTotalGoals.text = player.totalGoal.toString()

        binding!!.btnBack.setOnClickListener {
            dismiss()
        }

        return view
    }
}
