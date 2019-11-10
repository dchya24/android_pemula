package com.dchya24.submission1.MainActivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dchya24.submission1.R
import com.dchya24.submission1.models.League
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        MainActivityUI(items).setContentView(this)
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
