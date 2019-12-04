package com.dchya24.submission1.repository

import android.util.Log
import com.dchya24.submission1.api.DbSportApiService
import com.dchya24.submission1.models.response.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeagueRepository(private var viewm: detailLeague) {
    fun getDetailLeague(id: Int){
        DbSportApiService()
            .services.getLeagueDetail(id).enqueue(object : Callback<LeagueResponse> {
                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    viewm.handleError(t)
                }
                override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                    viewm.handleData(response.body()!!)
                }
            })
    }

    interface detailLeague{
        fun handleError(t: Throwable)
        fun handleData(leagueResponse: LeagueResponse)
    }
}