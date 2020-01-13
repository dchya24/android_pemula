package com.dchya24.submission1.leaguedetail

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dchya24.submission1.models.LeagueDetail
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LeagueDetailViewModelUnitTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: LeagueDetailViewModel

    @Mock
    private val observer: Observer<LeagueDetail> = mockUp()

    @Before
    fun before(){
        MockitoAnnotations.initMocks(this)
        viewModel = LeagueDetailViewModel(Application())
        viewModel.getLeagueData().observeForever(observer)
    }

    @Test
    fun testSetLeagueData(){
//        val mediatorLiveData = MediatorLiveData<LeagueDetail>()

        viewModel.setLeagueData(4328)
        viewModel.getLeagueData().observeForever(observer)

        val captor = ArgumentCaptor.forClass(LeagueDetail::class.java)
        captor.run {
            verify(observer, times(1)).onChanged(capture())
            assertEquals("English Premiere League", value.name)
        }

    }

}

inline fun <reified T> mockUp(): T = mock(T::class.java)