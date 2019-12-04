package com.dchya24.submission1.models.response

import com.dchya24.submission1.models.Match
import com.google.gson.annotations.SerializedName

data class MatchDetailResponse(
    @SerializedName("events")
    var events: MutableList<Match>
)