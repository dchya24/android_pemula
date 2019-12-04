package com.dchya24.submission1.search

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dchya24.submission1.models.MatchDiscover
import com.dchya24.submission1.repository.MatchRepository

class SearchViewModel: ViewModel(), MatchRepository.MatchRepoInterface{
    private val matchRepository = MatchRepository(this)
    val mldSearchMatch = MediatorLiveData<MutableList<MatchDiscover>>()

    override fun handleError(t: Throwable) {
        Log.e("SearchViewModel", t.message)
    }

    fun initSearchMatchList(query: String){
        mldSearchMatch.addSource(matchRepository.searchMatchs(query)){
            mldSearchMatch.value = it.events
        }
    }

    fun getSearchMatchList(): MediatorLiveData<MutableList<MatchDiscover>>{
        return mldSearchMatch
    }

}