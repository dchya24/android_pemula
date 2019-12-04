package com.dchya24.submission1.models

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    val id: String,

    @SerializedName("strTeamBadge")
    val badge: String
)