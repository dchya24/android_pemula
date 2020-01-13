package com.dchya24.submission1.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import com.dchya24.submission1.`interface`.MatchRepoInterface
import com.dchya24.submission1.models.MatchDiscover
import com.dchya24.submission1.repository.MatchRepository

class SearchViewModel(application: Application): AndroidViewModel(application), MatchRepoInterface {
    private val matchRepository = MatchRepository(this)
    private val mldSearchMatch = MediatorLiveData<MutableList<MatchDiscover>>()
    private lateinit var searchVMInterface: SearchVMInterface

    fun setSearchVMInterface(searchVMInterface: SearchVMInterface){
        this.searchVMInterface = searchVMInterface
    }

    override fun handleError(t: Throwable) {
        Log.e("SearchViewModel", t.message)
    }

    fun initSearchMatchList(query: String){

        mldSearchMatch.addSource(matchRepository.searchMatchs(query)){
            val data = it.events
            if(data == null){
                searchVMInterface.handleNullData()
            }else{
                mldSearchMatch.value = data
            }
        }
    }

    fun getSearchMatchList(): MediatorLiveData<MutableList<MatchDiscover>>{
        return mldSearchMatch
    }

    interface SearchVMInterface{
        fun handleNullData()
    }

}