package com.dchya24.submission1.repository

import androidx.lifecycle.MutableLiveData
import com.dchya24.submission1.models.response.LeagueResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailLeagueRepositoryUnitTest{
    @Mock
    private lateinit var detailLeagueRepository: DetailLeagueRepository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testGetDetailLeague(){
        val detailLeague = MutableLiveData<LeagueResponse>()

        detailLeagueRepository.getDetailLeague(4328)

        verify(detailLeagueRepository).getDetailLeague(4328)
        `when`(detailLeagueRepository.getDetailLeague(4328))
            .thenReturn(detailLeague)
    }
}