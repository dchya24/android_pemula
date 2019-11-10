package com.dchya24.submission1

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dchya24.submission1.models.League
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class LeagueAdapter(
    private val leagueList: List<League>,
    private val listener: (League) -> Unit
    ): RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LeagueUI().createView(AnkoContext.create(parent.context, parent)))
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

    class LeagueUI: AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                cardView{
                    cardElevation = 4f
                    lparams {
                        width = matchParent
                        margin = dip(8)
                    }

                    verticalLayout{
                        lparams(width = matchParent, height = wrapContent)
                        padding = dip(16)
                        orientation = LinearLayout.VERTICAL
                        gravity = Gravity.CENTER_HORIZONTAL

                        imageView {
                            id = R.id.league_logo
                        }.lparams {
                            height = dip(100)
                            width = dip(100)
                        }

                        textView {
                            id = R.id.league_name
                            textSize = 16f
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            typeface = Typeface.DEFAULT_BOLD
                        }.lparams {
                            topMargin = dip(8)
                        }
                    }
                }
            }
        }

    }

}