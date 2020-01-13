package com.dchya24.submission1.prevmatch

import androidx.lifecycle.MediatorLiveData
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PrevMatchViewModelUnitTest {
    @Mock
    private lateinit var prevMatchViewModel: PrevMatchViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        prevMatchViewModel = Mockito.spy(PrevMatchViewModel())
    }

    @Test
    fun testGetMatchData(){
        val prevMatchMediatorLiveData = MediatorLiveData<MatchDiscoverResponse>()

        prevMatchViewModel.setMatchData(4328)
        Mockito.verify(prevMatchViewModel).setMatchData(4328)

        Mockito.`when`(prevMatchViewModel.getMatchData())
            .thenReturn(prevMatchMediatorLiveData)
    }
}