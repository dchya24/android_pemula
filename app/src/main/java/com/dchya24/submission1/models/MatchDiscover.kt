package com.dchya24.submission1.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchDiscover(
    var ID_: Int?,

    @SerializedName("idEvent")
    val id: Int,
    @SerializedName("strEvent")
    val eventName: String,
    @SerializedName("dateEvent")
    val date: String,
    @SerializedName("strTime")
    val time: String,
    @SerializedName("strSport")
    var strSport: String,
    @SerializedName("idHomeTeam")
    val homeTeamId: Int,
    @SerializedName("strHomeTeam")
    val homeTeam: String,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("idAwayTeam")
    val awayTeamId: Int,
    @SerializedName("strAwayTeam")
    val awayTeam: String,
    @SerializedName("intAwayScore")
    val awayScore: String?

): Parcelable{
    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"

        const val ID: String = "ID_"

        const val EVENT_ID: String = "EVENT_ID"
        const val EVENT_NAME: String = "EVENT"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
        const val SPORT_TYPE: String = "SPORT_TYPE"

        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_NAME: String = "HOME_NAME"
        const val AWAY_NAME: String = "AWAY_NAME"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
    }

   fun getScoreAway(): String{
       if (awayScore != null) {
           return awayScore
       }else{
           return "-"
       }
   }

    fun getScoreHome(): String{
        if (homeScore != null) {
            return homeScore
        }else{
            return "-"
        }
    }
}