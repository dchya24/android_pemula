package com.dchya24.submission1.main

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.dchya24.submission1.Adapter.LeagueAdapter
import com.dchya24.submission1.leaguedetail.LeagueDetailActivity
import com.dchya24.submission1.models.League
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityUI(private val items: MutableList<League>): AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>): View {
        return with(ui){
            verticalLayout{
                recyclerView{
                    lparams(width = matchParent, height = wrapContent)
                    topPadding = dip(8)
                    leftPadding = dip(8)
                    rightPadding = dip(8)

                    layoutManager = GridLayoutManager(context, 2)
                    adapter = LeagueAdapter(items) {
                        startActivity<LeagueDetailActivity>("league" to it)
                    }

                }.lparams(width = matchParent, height = wrapContent)
            }
        }
    }

}