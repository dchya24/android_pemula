package com.dchya24.submission1.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MatchDiscover(
    @SerializedName("idEvent")
    val id: Int,

    @SerializedName("strEvent")
    val eventName: String,

    @SerializedName("strHomeTeam")
    val homeTeam: String,

    @SerializedName("strAwayTeam")
    val awayTeam: String,


    @SerializedName("strSport")
    val strSport: String,

    @SerializedName("dateEvent")
    val date: String,

    @SerializedName("strTime")
    val time: String,

    @SerializedName("idHomeTeam")
    val homeTeamId: Int,

    @SerializedName("idAwayTeam")
    val awayTeamId: Int,

    @SerializedName("intHomeScore")
    val homeScore: String?,

    @SerializedName("intAwayScore")
    val awayScore: String?

): Parcelable{
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