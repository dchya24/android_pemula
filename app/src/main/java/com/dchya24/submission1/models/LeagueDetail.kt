package com.dchya24.submission1.models

import com.google.gson.annotations.SerializedName

data class LeagueDetail(
    @SerializedName("idLeague")
    var id: Int,

    @SerializedName("strLeague")
    var name: String,

    @SerializedName("strTrophy")
    var trophy: String,

    @SerializedName("strBadge")
    var logo: String
)