package com.dchya24.submission1.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.dchya24.submission1.favoritematch.FavoriteMatchListFragment
import com.dchya24.submission1.leaguelist.LeagueListFragment
import com.dchya24.submission1.R
import com.dchya24.submission1.search.SearchActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener, BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(p: MenuItem): Boolean {
        when(p.itemId){
            R.id.home_league -> {
                    Log.d("trigger", "Ketrigger")
                    val fragment = LeagueListFragment()
                    addFragment(fragment)
                }
            R.id.home_favorite -> {
                val fragment = FavoriteMatchListFragment()
                addFragment(fragment)
            }
        }

        return true
    }

    private fun addFragment(fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameMain, fragment, fragment.javaClass.simpleName)
            .commit()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query != ""){
            startActivity<SearchActivity>("query" to query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bn_main.setOnNavigationItemSelectedListener(this)
        addFragment(LeagueListFragment())
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
