package com.dchya24.submission1.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.`interface`.MatchRepoInterface
import com.dchya24.submission1.models.Team
import com.dchya24.submission1.models.response.MatchDetailResponse
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import com.dchya24.submission1.models.response.SearchMatchsResponse
import com.dchya24.submission1.nextmatch.NextMatchViewModel
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.MockitoAnnotations

class MatchRepositoryUnitTest {
    @Mock
    private lateinit var matchRepository: MatchRepository

    @Mock
    private val mutableMatchDetail: MutableLiveData<MatchDetailResponse> = MutableLiveData()

    @Mock
    private lateinit var mutableMatchResponse: MutableLiveData<MatchDiscoverResponse>

    @Mock
    private lateinit var searchMatchLiveData: MutableLiveData<SearchMatchsResponse>

    @Mock
    private lateinit var matchRepoInterface: MatchRepoInterface

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        matchRepoInterface = NextMatchViewModel(Application())
        matchRepository = spy(MatchRepository(matchRepoInterface))
        searchMatchLiveData = MutableLiveData()
        mutableMatchResponse = MutableLiveData()
    }

    @Test
    fun testGetListLastMatch(){
        Mockito.`when`(matchRepository.getListLastMatch(4328))
            .thenReturn(mutableMatchResponse)
    }

    @Test
    fun testGetListNextMatch(){
        Mockito.`when`(matchRepository.getListNextMatch(4328))
            .thenReturn(mutableMatchResponse)
    }

    @Test
    fun testSearchMatch(){
        Mockito.`when`(matchRepository.searchMatchs("Barcelona vs"))
            .thenReturn(searchMatchLiveData)
    }

    @Test
    fun testGetMatchDetail(){
        Mockito.`when`(matchRepository.getMatchDetail(602285))
            .thenReturn(mutableMatchDetail)
    }

    @Test
    fun testGetTeamBadge(){
        val team = MutableLiveData<Team>()

        Mockito.`when`(matchRepository.getTeamBadge(133636))
            .thenReturn(team)
    }
}