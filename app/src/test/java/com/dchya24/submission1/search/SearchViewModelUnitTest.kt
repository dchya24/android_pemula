package com.dchya24.submission1.search

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.dchya24.submission1.models.MatchDiscover
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SearchViewModelUnitTest{
    @Mock private lateinit var searchViewModel: SearchViewModel
    @Mock private lateinit var searchActivity: SearchActivity

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        searchViewModel = spy(SearchViewModel(Application()))
        searchActivity = spy(SearchActivity())
        searchViewModel.setSearchVMInterface(searchActivity)
    }

    @Test
    fun testGetSearchMatchList(){
        val data = MediatorLiveData<MutableList<MatchDiscover>>()
        val query = "Barcelona vs Real Madrid"

        searchViewModel.initSearchMatchList(query)
        verify(searchViewModel).initSearchMatchList(query)

        Mockito.`when`(searchViewModel.getSearchMatchList())
            .thenReturn(data)
    }
}