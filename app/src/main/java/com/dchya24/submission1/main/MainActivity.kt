package com.dchya24.submission1.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.dchya24.submission1.R
import com.dchya24.submission1.models.League
import com.dchya24.submission1.search.SearchActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != ""){
            startActivity<SearchActivity>("query" to query)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    private var items: MutableList<League> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        MainActivityUI(items).setContentView(this)
    }

    private fun initData(){
        val leagueName = resources.getStringArray(R.array.league_name)
        val leagueId = resources.getStringArray(R.array.league_id)
        val leagueLogo = resources.obtainTypedArray(R.array.league_logo)

        for (i in leagueName.indices) {
            val leagueItem = League(leagueId[i], leagueName[i], leagueLogo.getResourceId(i, 0))
            items.add(leagueItem)
        }
        leagueLogo.recycle()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager

        if(searchManager != null){
            val searchView: SearchView = menu?.findItem(R.id.search)?.actionView as SearchView

            searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
            searchView.queryHint = "Barcelona vs Persija"

            searchView.setOnQueryTextListener(this)
        }

        return super.onCreateOptionsMenu(menu)
    }

}
