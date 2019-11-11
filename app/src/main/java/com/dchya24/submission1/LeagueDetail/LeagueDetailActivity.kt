package com.dchya24.submission1.leaguedetail

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dchya24.submission1.R
import com.dchya24.submission1.models.League
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class LeagueDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // create parcelable data
        val league: League = intent.getParcelableExtra("league")

        LeagueDetailActivityUI(league).setContentView(this)

        val imgLeagueLogo: ImageView = find(R.id.league_logo)
        Glide.with(applicationContext)
            .load(league.logo).into(imgLeagueLogo)

        supportActionBar?.title = league.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
