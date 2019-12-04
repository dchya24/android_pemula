package com.dchya24.submission1.matchdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dchya24.submission1.R
import com.dchya24.submission1.models.BothTeamBadges
import com.dchya24.submission1.models.Match
import com.dchya24.submission1.models.MatchDiscover
import com.dchya24.submission1.models.Team
import kotlinx.android.synthetic.main.activity_match_detail.*
import kotlinx.android.synthetic.main.away_team.*
import kotlinx.android.synthetic.main.home_team.*
import kotlinx.android.synthetic.main.match_detail_header.*
import kotlinx.android.synthetic.main.match_red_card.*
import kotlinx.android.synthetic.main.match_score.*
import kotlinx.android.synthetic.main.match_yellow_card.*

class MatchDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val match: MatchDiscover = intent.getParcelableExtra("match")

        tvHomeScore.text = match.getScoreHome()
        tvAwayScore.text = match.getScoreAway()
        tvHomeName.text = match.homeTeam
        tvAwayName.text = match.awayTeam
        hideView()

        val matchDetailViewModel = ViewModelProviders.of(this)
            .get(MatchDetailViewModel::class.java)

        matchDetailViewModel.setMatchDetail(match.id)

        matchDetailViewModel.getMatchDetail()
            .observe(this, Observer<Match>{
                setData(it)
                showView()
            })

        matchDetailViewModel.getTeamBadge(match.awayTeamId)
            .observe(this, Observer<Team>{
                Glide.with(this).load(it.badge).into(imgAwayLogo)
            })

        matchDetailViewModel.getTeamBadge(match.homeTeamId)
            .observe(this, Observer<Team>{
                Glide.with(this).load(it.badge).into(imgHomeLogo)
            })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setData(data: Match){
        tvHomeScoreDetail.text  = data.getHomeScoreArray()
        tvAwayScoreDetail.text = data.getAwayScoreArray()
        tvHomeYellowCard.text = data.getHomeYellowCardInfo()
        tvAwayYellowCard.text = data.getAwayYellowCardInfo()
        tvHomeRedCard.text = data.getHomeRedCardInfo()
        tvAwayRedCard.text = data.getAwayRedCardInfo()
    }

    private fun hideView(){
        pbMatchHeader.visibility = View.VISIBLE
        pbMatchBody.visibility = View.VISIBLE
        matchDetailHeader.visibility = View.INVISIBLE
        matchDetailBody.visibility = View.INVISIBLE
    }

    private fun showView(){
        pbMatchHeader.visibility = View.INVISIBLE
        pbMatchBody.visibility = View.INVISIBLE
        matchDetailHeader.visibility = View.VISIBLE
        matchDetailBody.visibility = View.VISIBLE
    }
}
