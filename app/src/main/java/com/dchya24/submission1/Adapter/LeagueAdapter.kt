package com.dchya24.submission1.Adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dchya24.submission1.Component.LeagueUI
import com.dchya24.submission1.R
import com.dchya24.submission1.models.League
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find

class LeagueAdapter(
    private val leagueList: List<League>,
    private val listener: (League) -> Unit
    ): RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LeagueUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int {
        return leagueList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(leagueList[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val leagueLogo: ImageView = view.find(R.id.league_logo)
        private val leagueName: TextView = view.find(R.id.league_name)

        fun bindItem(item: League, listener: (League) -> Unit){
            Glide.with(itemView.context)
                .load(item.logo).into(leagueLogo)
            leagueName.text = item.name
            itemView.setOnClickListener{
                listener(item)
            }
        }
    }
}