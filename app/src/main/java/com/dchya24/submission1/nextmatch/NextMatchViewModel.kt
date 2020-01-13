package com.dchya24.submission1.nextmatch

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.`interface`.MatchRepoInterface
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import com.dchya24.submission1.repository.MatchRepository

class NextMatchViewModel(application: Application): AndroidViewModel(application),
    MatchRepoInterface {
    private var liveDataMatchList = MutableLiveData<MatchDiscoverResponse>()

    override fun handleError(t: Throwable) {
        Log.d("Error MatchViewModel", t.message)
    }

    fun setMatchData(leagueId: Int){
        val matchRepository = MatchRepository(this)
        liveDataMatchList = matchRepository.getListNextMatch(leagueId)
    }

    fun getMatchData(): MediatorLiveData<MatchDiscoverResponse> {
        val mediatorLiveData = MediatorLiveData<MatchDiscoverResponse>()

        mediatorLiveData.addSource(liveDataMatchList){
            mediatorLiveData.value = it
        }

        return mediatorLiveData
    }
}