package com.dchya24.submission1.matchdetail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.`interface`.MatchRepoInterface
import com.dchya24.submission1.models.Match
import com.dchya24.submission1.models.MatchDiscover
import com.dchya24.submission1.models.Team
import com.dchya24.submission1.models.response.MatchDetailResponse
import com.dchya24.submission1.repository.FavoriteRepository
import com.dchya24.submission1.repository.MatchRepository
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton

class MatchDetailViewModel(application: Application)
    : AndroidViewModel(application),
    MatchRepoInterface {

    private val matchDetailRepository = MatchRepository(this)
    private val favoriteRepository = FavoriteRepository(application)
    private val context = application
    private lateinit var  matchDetailVM: MatchDetailVM

    override fun handleError(t: Throwable) {
        Log.e("MatchDetailViewModel", t.message)
    }

    fun setMatchDetailVM(matchDetailVM: MatchDetailVM){
        this.matchDetailVM = matchDetailVM
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

    fun isFavorite(id: Int){
        val data = favoriteRepository.getFavoriteMatch(id)
        if(data.error != ""){
            handleFavoriteError(data)
        }else{
            matchDetailVM.setIsFavorite(data.listFavoriteData.size)
        }
    }

    fun insertFavorite(match: MatchDiscover) {
        val data = favoriteRepository.addToFavorite(match)
        if (data.error != "") {
            handleFavoriteError(data)
        } else {
            matchDetailVM.setToast("Match telah ditambahkan di favorite match")
            matchDetailVM.changeFavoriteIcon(true)
        }
    }

    fun deleteFavorite(id: Int){
        val data = favoriteRepository.deleteFavoriteMatch(id)
        if (data.error != "") {
            handleFavoriteError(data)
        } else {
            matchDetailVM.setToast("Match Telah dihapus dari favorite Match")
            matchDetailVM.changeFavoriteIcon(false)
        }
    }

    private fun handleFavoriteError(data: FavoriteRepository.FavoriteHandler){
        context.alert {
            title = "Error"
            message = data.error
            noButton {  }
        }
    }

    interface MatchDetailVM{
        fun setIsFavorite(favorite: Int)
        fun setError(error: String)
        fun changeFavoriteIcon(favorite: Boolean)
        fun setToast(message: String)
    }

}