package com.dchya24.submission1.models

data class MatchDetail(
    val match: Match,
    val bothTeamBadges: BothTeamBadges
)

data class BothTeamBadges(
    val home: String,
    val away: String
)