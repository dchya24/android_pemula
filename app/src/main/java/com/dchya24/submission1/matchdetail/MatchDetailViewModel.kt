package com.dchya24.submission1.matchdetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.models.BothTeamBadges
import com.dchya24.submission1.models.Match
import com.dchya24.submission1.models.MatchDetail
import com.dchya24.submission1.models.Team
import com.dchya24.submission1.models.response.MatchDetailResponse
import com.dchya24.submission1.repository.MatchRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailViewModel(application: Application) : AndroidViewModel(application), MatchRepository.MatchRepoInterface{
    private val matchDetailRepository = MatchRepository(this)

    override fun handleError(t: Throwable) {
        Log.e("MatchDetailViewModel", t.message)
    }

    private var matchDetailLiveData =  MutableLiveData<MatchDetailResponse>()

    fun setMatchDetail(id: Int){
        matchDetailLiveData = matchDetailRepository.getMatchDetail(id)
    }

    fun getMatchDetail(): MediatorLiveData<Match>{
        val mediatorLiveData = MediatorLiveData<Match>()

        mediatorLiveData.addSource(matchDetailLiveData){
            mediatorLiveData.value = it.events[0]
        }

        return mediatorLiveData
    }

    fun getTeamBadge(id: Int):MediatorLiveData<Team>{
        val mediatorLiveData = MediatorLiveData<Team>()

        mediatorLiveData.addSource(matchDetailRepository.getTeamBadge(id)){
            mediatorLiveData.value = it
        }

        return mediatorLiveData
    }

}