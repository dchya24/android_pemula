package com.dchya24.submission1.`interface`

import com.dchya24.submission1.models.response.LeagueResponse


interface LeagueDetailInterface{
    fun handleError(t: Throwable)
    fun handleData(leagueResponse: LeagueResponse)
}