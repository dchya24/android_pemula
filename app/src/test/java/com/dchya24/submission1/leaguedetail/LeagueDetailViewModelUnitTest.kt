package com.dchya24.submission1.leaguedetail

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dchya24.submission1.models.LeagueDetail
import com.dchya24.submission1.models.response.LeagueResponse
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.*
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LeagueDetailViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks private var viewModel = LeagueDetailViewModel(Application())

    @Mock private lateinit var leagueResponse: LeagueResponse

    @Mock
    private val observer: Observer<LeagueDetail> = mockUp()

    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        viewModel = spy(LeagueDetailViewModel(Application()))
        viewModel.getLeagueData().observeForever(observer)
    }

    @Test
    fun testSetLeagueData(){
        viewModel.setLeagueData(4328)
        verify(viewModel).handleData(leagueResponse)

        val captor = ArgumentCaptor.forClass(LeagueDetail::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals("English Premiere League", value.name)
        }

    }

}

inline fun <reified T> mockUp(): T = mock(T::class.java)