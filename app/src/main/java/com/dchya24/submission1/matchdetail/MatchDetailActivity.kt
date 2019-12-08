package com.dchya24.submission1.matchdetail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.dchya24.submission1.R
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
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast

class MatchDetailActivity : AppCompatActivity(), MatchDetailViewModel.MatchDetailVM {

    private lateinit var matchDetailViewModel: MatchDetailViewModel
    private lateinit var matchDiscover: MatchDiscover

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchDiscover = intent.getParcelableExtra("match")

        tvHomeScore.text = matchDiscover.getScoreHome()
        tvAwayScore.text = matchDiscover.getScoreAway()
        tvHomeName.text = matchDiscover.homeTeam
        tvAwayName.text = matchDiscover.awayTeam
        hideView()

        matchDetailViewModel = ViewModelProviders.of(this)
            .get(MatchDetailViewModel::class.java)
        matchDetailViewModel.setMatchDetailVM(this)

        matchDetailViewModel.setMatchDetail(matchDiscover.id)

        matchDetailViewModel.getMatchDetail()
            .observe(this, Observer<Match>{
                setData(it)
                showView()
            })

        matchDetailViewModel.getTeamBadge(matchDiscover.awayTeamId)
            .observe(this, Observer<Team>{
                Glide.with(this).load(it.badge).into(imgAwayLogo)
            })

        matchDetailViewModel.getTeamBadge(matchDiscover.homeTeamId)
            .observe(this, Observer<Team>{
                Glide.with(this).load(it.badge).into(imgHomeLogo)
            })

        matchDetailViewModel.isFavorite(matchDiscover.id)
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
        fabFavorite.hide()

    }

    private fun showView(){
        pbMatchHeader.visibility = View.INVISIBLE
        pbMatchBody.visibility = View.INVISIBLE
        matchDetailHeader.visibility = View.VISIBLE
        matchDetailBody.visibility = View.VISIBLE
        fabFavorite.show()
    }


    override fun setIsFavorite(favorite: Int) {
        if(favorite == 0){
            changeFavoriteIcon(false)
        }else{
            changeFavoriteIcon(true)
        }
    }

    override fun setError(error: String) {
        alert {
            title = "Error"
            message = error
        }
    }

    override fun changeFavoriteIcon(favorite: Boolean) {
        if(favorite){
            fabFavorite.setImageResource(R.drawable.ic_favorite_black_24dp)
            fabFavorite.setOnClickListener{
                matchDetailViewModel.deleteFavorite(matchDiscover.id)
            }
        }else{
            fabFavorite.setImageResource(R.drawable.ic_favorite_border_black_24dp)
            fabFavorite.setOnClickListener{
                matchDetailViewModel.insertFavorite(matchDiscover)
            }
        }
    }

    override fun setToast(message: String) {
        toast(message)
    }
}
