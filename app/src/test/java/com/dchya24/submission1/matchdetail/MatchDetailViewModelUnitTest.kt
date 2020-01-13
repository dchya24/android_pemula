package com.dchya24.submission1.matchdetail

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.dchya24.submission1.models.Match
import com.dchya24.submission1.models.Team
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*

class MatchDetailViewModelUnitTest {
    @Mock
    private lateinit var matchDetailViewModel: MatchDetailViewModel

    @Before
    fun setup(){
        matchDetailViewModel = spy(MatchDetailViewModel(Application()))
    }

    @Test
    fun testGetMatchDetail(){
        val mediatorLiveData = MediatorLiveData<Match>()

        matchDetailViewModel.setMatchDetail(602249)
        verify(matchDetailViewModel).setMatchDetail(602249)

        `when`(matchDetailViewModel.getMatchDetail())
            .thenReturn(mediatorLiveData)
    }

    @Test
    fun testGetTeamBadge(){
        val mediatorLiveData = MediatorLiveData<Team>()

        `when`(matchDetailViewModel.getTeamBadge(133632))
            .thenReturn(mediatorLiveData)
    }

}