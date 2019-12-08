package com.dchya24.submission1.search

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dchya24.submission1.R
import com.dchya24.submission1.adapter.MatchAdapter
import com.dchya24.submission1.matchdetail.MatchDetailActivity
import com.dchya24.submission1.models.MatchDiscover
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class SearchActivity : AppCompatActivity(), SearchViewModel.SearchVMInterface{
    override fun handleNullData() {
        showView()
        alert {
            title = "Null Data"
            message = "Data yang dicari tidak ada"
            yesButton {  }
        }.show()
    }

    private lateinit var matchAdapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val query = intent.getStringExtra("query")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        etSearch.setText(query)

        matchAdapter = MatchAdapter {
            startActivity<MatchDetailActivity>("match" to it)
        }

        rvMatchList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        hideView()
        val searchViewModel = ViewModelProviders.of(this)
            .get(SearchViewModel::class.java)
        searchViewModel.setSearchVMInterface(this)

        searchViewModel.initSearchMatchList(query)
        searchViewModel.getSearchMatchList()
            .observe(this,
                Observer<MutableList<MatchDiscover>> { t ->
                    val searchBySoccer = t.filter {it.strSport == "Soccer" }
                    val mutableList = mutableListOf<MatchDiscover>()
                    mutableList.addAll(searchBySoccer)
                    setData(mutableList)
                })

        btnSearch.setOnClickListener {
            val string = etSearch?.text.toString()
            searchViewModel.initSearchMatchList(string)
            hideView()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setData(data: MutableList<MatchDiscover>){
        if(data.size == 0){
            handleNullData()
        }else{
            matchAdapter.setMatchList(data)
            showView()
        }
    }

    private fun hideView(){
        pbSearchMatch.visibility = View.VISIBLE
        rvMatchList.visibility = View.INVISIBLE
    }

    private fun showView(){
        pbSearchMatch.visibility = View.INVISIBLE
        rvMatchList.visibility = View.VISIBLE
    }

}
