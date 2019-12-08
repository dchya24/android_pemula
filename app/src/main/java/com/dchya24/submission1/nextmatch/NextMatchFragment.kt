package com.dchya24.submission1.nextmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dchya24.submission1.adapter.MatchAdapter
import com.dchya24.submission1.R
import com.dchya24.submission1.matchdetail.MatchDetailActivity
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment(private val leagueId: Int) : Fragment() {
    private lateinit var nextMatchViewModel: NextMatchViewModel
    private val matchAdapter = MatchAdapter{
        startActivity<MatchDetailActivity>("match" to it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvNextMatch.visibility = View.INVISIBLE
        pbNextMatch.visibility = View.VISIBLE

        rvNextMatch.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }

        nextMatchViewModel = ViewModelProviders.of(this)
            .get(NextMatchViewModel::class.java)

        setData()

    }

    private fun setData(){

        nextMatchViewModel.setMatchData(leagueId)
        nextMatchViewModel.getMatchData()
            .observe(this,
                Observer<MatchDiscoverResponse> {
                        t ->
                        if(t.events != null){
                            matchAdapter.setMatchList(t.events)
                        }else{
                            toast("Tidak ada data pertandingan berikutnya")
                        }
                        rvNextMatch.visibility = View.VISIBLE
                        pbNextMatch.visibility = View.INVISIBLE
                })
    }


}
