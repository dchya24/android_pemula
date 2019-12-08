package com.dchya24.submission1.repository

import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.api.DbSportApiService
import com.dchya24.submission1.models.Team
import com.dchya24.submission1.models.response.MatchDetailResponse
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import com.dchya24.submission1.models.response.SearchMatchsResponse
import com.dchya24.submission1.models.response.TeamResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MatchRepository(val matchRepoInterface: MatchRepoInterface){
    private val mutableMatchResponse = MutableLiveData<MatchDiscoverResponse>()

    fun getListLastMatch(id: Int):MutableLiveData<MatchDiscoverResponse>{

        DbSportApiService()
            .services.getLastMatch(id)
            .enqueue(object: Callback<MatchDiscoverResponse>{
                override fun onFailure(call: Call<MatchDiscoverResponse>, t: Throwable) {
                    matchRepoInterface.handleError(t)
                }

                override fun onResponse(
                    call: Call<MatchDiscoverResponse>,
                    response: Response<MatchDiscoverResponse>
                ) {
                    mutableMatchResponse.postValue(response.body())
                }
            })

        return mutableMatchResponse
    }

    fun getNextLastMatch(id: Int):MutableLiveData<MatchDiscoverResponse>{
        DbSportApiService()
            .services.getNextMatch(id)
            .enqueue(object: Callback<MatchDiscoverResponse>{
                override fun onFailure(call: Call<MatchDiscoverResponse>, t: Throwable) {
                    matchRepoInterface.handleError(t)
                }

                override fun onResponse(
                    call: Call<MatchDiscoverResponse>,
                    response: Response<MatchDiscoverResponse>
                ) {
                    mutableMatchResponse.postValue(response.body())
                }
            })

        return mutableMatchResponse
    }

    fun searchMatchs(query: String): MutableLiveData<SearchMatchsResponse>{
        val searchMatchsLivData = MutableLiveData<SearchMatchsResponse>()

        DbSportApiService()
            .services.searchMatchs(query.replace(" ", "_"))
            .enqueue(object: Callback<SearchMatchsResponse>{
                override fun onFailure(call: Call<SearchMatchsResponse>, t: Throwable) {
                    matchRepoInterface.handleError(t)
                }

                override fun onResponse(
                    call: Call<SearchMatchsResponse>,
                    response: Response<SearchMatchsResponse>
                ) {
                    val data  = response.body()
                    searchMatchsLivData.postValue(data)
                }
            })

        return searchMatchsLivData
    }

    fun getMatchDetail(id: Int): MutableLiveData<MatchDetailResponse>{
        val mutableMatchDetail: MutableLiveData<MatchDetailResponse> = MutableLiveData()

        DbSportApiService()
            .services.getMatchDetail(id)
            .enqueue(object: Callback<MatchDetailResponse>{
                override fun onFailure(call: Call<MatchDetailResponse>, t: Throwable) {
                    matchRepoInterface.handleError(t)
                }
                override fun onResponse(
                    call: Call<MatchDetailResponse>,
                    response: Response<MatchDetailResponse>
                ) {
                    mutableMatchDetail.postValue(response.body())
                }
            })

        return mutableMatchDetail
    }

    fun getTeamBadge(id: Int): MutableLiveData<Team>{
        val team = MutableLiveData<Team>()
        DbSportApiService()
            .services.getTeamDetail(id)
            .enqueue(object: Callback<TeamResponse>{
                override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                    matchRepoInterface.handleError(t)
                }

                override fun onResponse(
                    call: Call<TeamResponse>,
                    response: Response<TeamResponse>
                ) {
                    team.value = response.body()!!.teams[0]
                }
            })

        return team
    }

    interface MatchRepoInterface{
        fun handleError(t: Throwable)
    }
}