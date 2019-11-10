package com.dchya24.submission1.Component

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.dchya24.submission1.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class LeagueUI: AnkoComponent<ViewGroup> {
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