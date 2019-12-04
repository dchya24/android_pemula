package com.dchya24.submission1.leaguedetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dchya24.submission1.Adapter.MatchPagerAdapter
import com.dchya24.submission1.R
import com.dchya24.submission1.models.LeagueDetail
import kotlinx.android.synthetic.main.activity_league_detail.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton

class LeagueDetailActivity : AppCompatActivity(), LeagueDetailViewModel.ViewLayout {
    override fun setError(t: Throwable) {
        alert("Error Message", t.message){
            noButton {  }
        }
    }

    private var leagueId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_league_detail)
        leagueId = intent.getIntExtra("leagueId", 0)

        MatchPager.adapter = MatchPagerAdapter(supportFragmentManager, leagueId)
        tabMatch.setupWithViewPager(MatchPager)

        hideView()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val leagueDetailViewModel = ViewModelProviders
            .of(this@LeagueDetailActivity)
            .get(LeagueDetailViewModel::class.java)
        leagueDetailViewModel.setInterface(this)

        leagueDetailViewModel.setLeagueData(leagueId)
        leagueDetailViewModel.getLeagueData()
            .observe(this,
                Observer<LeagueDetail>{ leagueDetail ->
                    setDataView(leagueDetail)
                })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setDataView(league: LeagueDetail){
        supportActionBar?.title = league.name
        tvLeagueName.text = league.name
        Glide.with(this).load(league.logo)
            .into(imgLeagueLogo)
        Glide.with(this).load(league.trophy)
            .into(imgLeagueTrophy)
        showView()
    }

    private fun hideView(){
        pbLeagueDetail.visibility = View.VISIBLE
        tvLeagueName.visibility = View.INVISIBLE
        imgLeagueLogo.visibility = View.INVISIBLE
        imgLeagueTrophy.visibility = View.INVISIBLE
    }

    private fun showView(){
        pbLeagueDetail.visibility = View.INVISIBLE
        tvLeagueName.visibility = View.VISIBLE
        imgLeagueLogo.visibility = View.VISIBLE
        imgLeagueTrophy.visibility = View.VISIBLE

    }

}
