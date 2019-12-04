package com.dchya24.submission1.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dchya24.submission1.R
import com.dchya24.submission1.models.MatchDiscover
import kotlinx.android.synthetic.main.item_match.view.*

class MatchAdapter(
    private val listener: (MatchDiscover) -> Unit): RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {
    private var matchList = mutableListOf<MatchDiscover>()

    fun setMatchList(list: MutableList<MatchDiscover>){
        matchList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_match,parent, false))
    }

    override fun getItemCount(): Int {
        return matchList.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.onBind(matchList[position], listener)
    }

    class MatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvTitleMatch = view.tvTitleMatch
        private val tvHomeName = view.tvHomeName
        private val tvAwayName = view.tvAwayName
        private val tvHomeScore = view.tvHomeScore
        private val tvAwayScore = view.tvAwayScore
        private val tvDate = view.tvDate
        private val tvTime = view.tvTime

        fun onBind(match: MatchDiscover, listener: (MatchDiscover) -> Unit){
            tvTitleMatch.text = match.eventName
            tvHomeName.text = match.homeTeam
            tvAwayName.text = match.awayTeam
            tvHomeScore.text = match.getScoreHome()
            tvAwayScore.text = match.getScoreAway()
            tvDate.text = match.date
            tvTime.text = match.time

            itemView.setOnClickListener{
                listener(match)
            }
        }
    }

}
