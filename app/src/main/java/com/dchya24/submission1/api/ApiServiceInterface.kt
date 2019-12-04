package com.dchya24.submission1.api

import com.dchya24.submission1.models.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiServiceInterface {
    @GET("lookupleague.php")
    fun getLeagueDetail(@Query("id") id: Int): Call<LeagueResponse>

    @GET("eventspastleague.php")
    fun getLastMatch(@Query("id") id: Int): Call<MatchDiscoverResponse>

    @GET("eventsnextleague.php")
    fun getNextMatch(@Query("id") id: Int): Call<MatchDiscoverResponse>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") id: Int): Call<MatchDetailResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") id: Int): Call<TeamResponse>

    @GET("searchevents.php")
    fun searchMatchs(@Query("e") e: String): Call <SearchMatchsResponse>

}