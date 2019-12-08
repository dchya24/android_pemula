package com.dchya24.submission1.leaguedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.models.LeagueDetail
import com.dchya24.submission1.models.response.LeagueResponse
import com.dchya24.submission1.repository.DetailLeagueRepository

class LeagueDetailViewModel(application: Application): AndroidViewModel(application), DetailLeagueRepository.detailLeague {
    private val leagueDetailLiveData = MutableLiveData<LeagueDetail>()
    private lateinit var viewLayout: ViewLayout

    fun setLeagueData(id: Int){
        val leagueRepository = DetailLeagueRepository(this)
        leagueRepository.getDetailLeague(id)
    }

    fun setInterface(viewLayout: ViewLayout){
        this.viewLayout = viewLayout
    }

    fun getLeagueData(): MediatorLiveData<LeagueDetail> {
        val liveData = MediatorLiveData<LeagueDetail>()

        liveData.addSource(leagueDetailLiveData){
            liveData.value = it
        }

        return liveData
    }

    override fun handleError(t: Throwable) {
        viewLayout.setError(t)
    }

    override fun handleData(leagueResponse: LeagueResponse) {
        leagueDetailLiveData.postValue(leagueResponse.leagueDetail[0])
    }

    interface ViewLayout{
        fun setError(t: Throwable)
    }
}
