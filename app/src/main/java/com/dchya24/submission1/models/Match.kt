package com.dchya24.submission1.models

import com.google.gson.annotations.SerializedName


data class Match(
    @SerializedName("idHomeTeam")
    val idHome: String,

    @SerializedName("idAwayTeam")
    val idAway: String,

    @SerializedName("strHomeGoalDetails")
    val homeScoreDetail: String,

    @SerializedName("strHomeRedCards")
    val homeRedCard: String,

    @SerializedName("strHomeYellowCards")
    val homeYellowCard: String,

    @SerializedName("strAwayGoalDetails")
    val awayScoreDetail: String,

    @SerializedName("strAwayRedCards")
    val awayRedCard: String,

    @SerializedName("strAwayYellowCards")
    val awayYellowCard: String
){
    fun getHomeScoreArray(): String{
        if(this.homeScoreDetail == null){
            return "Tidak ada Data"
        }else{
            val data = this.homeScoreDetail.split(";")
            return listToString(data)
        }
    }

    fun getHomeYellowCardInfo(): String {
        if(this.homeYellowCard == null){
            return "Tidak ada Data"
        }else{
            val data = this.homeYellowCard.split(";")
            return listToString(data)
        }
    }

    fun getHomeRedCardInfo(): String {
        if(this.homeRedCard == null){
            return "Tidak ada Data"
        }else{
            val data = this.homeRedCard.split(";")
            return listToString(data)
        }
    }

    fun getAwayScoreArray(): String{
        if(this.awayScoreDetail == null){
            return "Tidak ada Data"
        }else{
            val data = this.awayScoreDetail.split(";")
            return listToString(data)
        }
    }

    fun getAwayYellowCardInfo(): String {
        if(this.awayYellowCard== null){
            return "Tidak ada Data"
        }else{
            val data = this.awayYellowCard.split(";")
            return listToString(data)
        }
    }

    fun getAwayRedCardInfo(): String {
        if(this.awayRedCard == null){
            return "Tidak ada Data"
        }else{
            val data = this.awayRedCard.split(";")
            return listToString(data)
        }
    }

    private fun listToString(list: List<String>): String{
        var string = ""

        for(value in list){
            string += "$value\n"
        }

        return string
    }
}
