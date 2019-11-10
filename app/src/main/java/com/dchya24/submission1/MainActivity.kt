package com.dchya24.submission1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dchya24.submission1.models.League
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()

        verticalLayout{

            recyclerView{
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(8)
                leftPadding = dip(8)
                rightPadding = dip(8)

                layoutManager = GridLayoutManager(context, 2)
                adapter = LeagueAdapter(items){
                    startActivity(intentFor<LeagueDetailActivity>("league" to it))
                }

            }.lparams(width = matchParent, height = wrapContent)
        }
    }

    private fun initData(){
        val leagueName = resources.getStringArray(R.array.league_name)
        val leagueId = resources.getStringArray(R.array.league_id)
        val leagueDesc = resources.getStringArray(R.array.league_description)
        val leagueLogo = resources.obtainTypedArray(R.array.league_logo)

        for (i in leagueName.indices) {
            val leagueItem = League(leagueId[i], leagueName[i], leagueDesc[i], leagueLogo.getResourceId(i, 0))
            items.add(leagueItem)
        }
        leagueLogo.recycle()
    }

}
