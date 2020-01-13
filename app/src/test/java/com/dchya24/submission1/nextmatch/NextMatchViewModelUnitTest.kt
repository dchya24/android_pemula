package com.dchya24.submission1.nextmatch

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class NextMatchViewModelUnitTest {
    @Mock
    private val application = Application()

    @Mock
    private lateinit var nextMatchViewModel: NextMatchViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        nextMatchViewModel = Mockito.spy(NextMatchViewModel(application))
    }

    @Test
    fun testGetMatchData(){
        val nextMatchMediatorLiveData = MediatorLiveData<MatchDiscoverResponse>()

        nextMatchViewModel.setMatchData(4328)
        verify(nextMatchViewModel).setMatchData(4328)

        Mockito.`when`(nextMatchViewModel.getMatchData())
            .thenReturn(nextMatchMediatorLiveData)
    }
}