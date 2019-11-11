package com.dchya24.submission1.leaguedetail

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import com.dchya24.submission1.R
import com.dchya24.submission1.models.League
import org.jetbrains.anko.*


class LeagueDetailActivityUI(private var league: League): AnkoComponent<LeagueDetailActivity>{

    override fun createView(ui: AnkoContext<LeagueDetailActivity>): View {

        return with(ui){
            verticalLayout{
                padding = dip(16)
                gravity  = Gravity.CENTER_HORIZONTAL

                imageView{
                    id  = R.id.league_logo
                }.lparams{
                    width = dip(80)
                    height = dip(80)
                    bottomMargin = dip(8)
                }

                // Title View
                textView{
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    textSize - 16f
                    typeface = Typeface.DEFAULT_BOLD
                    text = league.name
                }.lparams(width = matchParent)

                // Description View
                textView {
                    textSize = 12f
                    text = league.description
                }.lparams{
                    topMargin = dip(8)
                    width = matchParent
                }
            }
        }
    }

}