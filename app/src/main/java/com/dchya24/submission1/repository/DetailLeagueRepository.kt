package com.dchya24.submission1.repository

import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.`interface`.LeagueDetailInterface
import com.dchya24.submission1.api.DbSportApiService
import com.dchya24.submission1.models.response.LeagueResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailLeagueRepository(var leagueDetailInterface: LeagueDetailInterface) {
    fun getDetailLeague(id: Int): MutableLiveData<LeagueResponse>{
        val mldLeagueResponse = MutableLiveData<LeagueResponse>()
        DbSportApiService()
            .services.getLeagueDetail(id).enqueue(object : Callback<LeagueResponse> {
                override fun onFailure(call: Call<LeagueResponse>, t: Throwable) {
                    leagueDetailInterface.handleError(t)
                }
                override fun onResponse(call: Call<LeagueResponse>, response: Response<LeagueResponse>) {
                    mldLeagueResponse.postValue(response.body())
                }
            })
        return mldLeagueResponse
    }

}