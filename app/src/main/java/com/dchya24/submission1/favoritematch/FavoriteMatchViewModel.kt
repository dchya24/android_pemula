package com.dchya24.submission1.favoritematch

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.models.MatchDiscover
import com.dchya24.submission1.repository.FavoriteRepository

class FavoriteMatchViewModel(application: Application): AndroidViewModel(application){
    private val favoriteRepository = FavoriteRepository(application)
    private val mediatorLiveData = MediatorLiveData<MutableList<MatchDiscover>>()

    fun setFavoriteMatchList(){
        val data = favoriteRepository.getAllFavoriteMatch()
        val liveData = MutableLiveData<List<MatchDiscover>>()
        liveData.value = data.listFavoriteData

        mediatorLiveData.addSource(liveData){
            val mutableList = mutableListOf<MatchDiscover>()
            mutableList.addAll(it)
            mediatorLiveData.postValue(mutableList)
        }
    }

    fun getFavoriteMatchList(): MediatorLiveData<MutableList<MatchDiscover>>{
        return mediatorLiveData
    }

}