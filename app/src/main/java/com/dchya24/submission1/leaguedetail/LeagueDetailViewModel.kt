package com.dchya24.submission1.leaguedetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.`interface`.LeagueDetailInterface
import com.dchya24.submission1.models.LeagueDetail
import com.dchya24.submission1.models.response.LeagueResponse
import com.dchya24.submission1.repository.DetailLeagueRepository

class LeagueDetailViewModel(application: Application): AndroidViewModel(application), LeagueDetailInterface {
    private var leagueResponseLiveData = MutableLiveData<LeagueResponse>()
    private val leagueRepository = DetailLeagueRepository(this)

    private lateinit var viewLayout: ViewLayout


    fun setInterface(viewLayout: ViewLayout){
        this.viewLayout = viewLayout
    }

    fun setLeagueData(id: Int){
        leagueResponseLiveData = leagueRepository.getDetailLeague(id)
    }

    fun getLeagueData(): MediatorLiveData<LeagueDetail> {
        val liveData = MediatorLiveData<LeagueDetail>()

        liveData.addSource(leagueResponseLiveData){
            liveData.value = it.leagueDetail[0]
        }

        return liveData
    }

    override fun handleError(t: Throwable) {
        viewLayout.setError(t)
    }

    interface ViewLayout{
        fun setError(t: Throwable)
    }
}
