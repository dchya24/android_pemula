package com.dchya24.submission1.models.response

import com.dchya24.submission1.models.Team
import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val teams: List<Team>
)