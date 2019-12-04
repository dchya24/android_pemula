package com.dchya24.submission1.prevmatch

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import com.dchya24.submission1.repository.MatchRepository

class PrevMatchViewModel: ViewModel(), MatchRepository.MatchRepoInterface {
    private var liveDataMatchList = MutableLiveData<MatchDiscoverResponse>()

    override fun handleError(t: Throwable) {
        Log.d("Error MatchViewModel", t.message)
    }

    fun setMatchData(leagueId: Int){
        val matchRepository = MatchRepository(this)
        liveDataMatchList = matchRepository.getListLastMatch(leagueId)
    }

    fun getMatchData(): MediatorLiveData<MatchDiscoverResponse>{
        val mediatorLiveData = MediatorLiveData<MatchDiscoverResponse>()

        mediatorLiveData.addSource(liveDataMatchList){
            mediatorLiveData.value = it
        }

        return mediatorLiveData
    }
}