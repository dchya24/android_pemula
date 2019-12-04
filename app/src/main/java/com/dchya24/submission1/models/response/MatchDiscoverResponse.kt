package com.dchya24.submission1.models.response

import com.dchya24.submission1.models.MatchDiscover
import com.google.gson.annotations.SerializedName

data class MatchDiscoverResponse(
    @SerializedName("events")
    var events: MutableList<MatchDiscover>
)
