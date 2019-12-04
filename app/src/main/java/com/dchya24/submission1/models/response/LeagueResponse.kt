package com.dchya24.submission1.models.response

import com.dchya24.submission1.models.LeagueDetail
import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("leagues")
    var leagueDetail: MutableList<LeagueDetail>
)