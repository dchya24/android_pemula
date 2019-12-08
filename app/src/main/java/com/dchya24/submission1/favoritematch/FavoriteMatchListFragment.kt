package com.dchya24.submission1.favoritematch

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dchya24.submission1.R
import com.dchya24.submission1.adapter.MatchAdapter
import com.dchya24.submission1.matchdetail.MatchDetailActivity
import com.dchya24.submission1.models.MatchDiscover
import kotlinx.android.synthetic.main.fragment_favorite_match_list.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteMatchListFragment : Fragment() {
    private lateinit var favoriteMatchViewModel: FavoriteMatchViewModel
    private val matchAdapter = MatchAdapter{
        startActivity<MatchDetailActivity>("match" to it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvFavoriteMatch.apply{
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        showView(false)
        favoriteMatchViewModel = ViewModelProviders.of(this)
            .get(FavoriteMatchViewModel::class.java)

        initData()
    }

    private fun initData(){
        favoriteMatchViewModel.setFavoriteMatchList()
        favoriteMatchViewModel.getFavoriteMatchList()
            .observe(this, Observer<MutableList<MatchDiscover>>{
                showView(true)
                if(it.size == 0){
                    toast("tidak ada Match Favorite ")
                }else{
                    matchAdapter.setMatchList(it)
                    Log.d("count", it.size.toString())
                }
            })

    }

    private fun showView(show: Boolean){
        if(show){
            pbFavoriteMatch.visibility = View.INVISIBLE
            rvFavoriteMatch.visibility = View.VISIBLE
        }else{
            pbFavoriteMatch.visibility = View.VISIBLE
            rvFavoriteMatch.visibility = View.INVISIBLE
        }
    }

}
