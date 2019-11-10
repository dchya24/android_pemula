package com.dchya24.submission1

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dchya24.submission1.models.League
import org.jetbrains.anko.*

class LeagueDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lateinit var tvLeagueTitle: TextView
        lateinit var imgLeagueLogo: ImageView
        lateinit var tvLeagueDesc: TextView

        // create oarcelable data
        val league: League = intent.getParcelableExtra("league")

        verticalLayout{
            padding = dip(16)
            gravity  = Gravity.CENTER_HORIZONTAL

            imgLeagueLogo = imageView{ }.lparams{
                width = dip(80)
                height = dip(80)
                bottomMargin = dip(8)
            }

            tvLeagueTitle =  textView{
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                textSize - 16f
                typeface = Typeface.DEFAULT_BOLD
            }.lparams(width = matchParent)

            tvLeagueDesc = textView {
                textSize = 12f
            }.lparams{
                topMargin = dip(8)
                width = matchParent
            }
        }

        tvLeagueTitle.text = league.name
        tvLeagueDesc.text = league.description
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
