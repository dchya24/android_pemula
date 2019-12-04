package com.dchya24.submission1.prevmatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dchya24.submission1.Adapter.MatchAdapter
import com.dchya24.submission1.R
import com.dchya24.submission1.matchdetail.MatchDetailActivity
import com.dchya24.submission1.models.response.MatchDiscoverResponse
import kotlinx.android.synthetic.main.fragment_prev_match.*
import org.jetbrains.anko.support.v4.startActivity

class PrevMatchFragment(private val leagueId: Int) : Fragment() {
    private lateinit var prevMatchViewModel: PrevMatchViewModel
    private val matchAdapter = MatchAdapter{
        startActivity<MatchDetailActivity>("match" to it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prev_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvLastMatch.visibility = View.INVISIBLE
        pbLastMatch.visibility = View.VISIBLE

        rvLastMatch.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = matchAdapter
        }
        prevMatchViewModel = ViewModelProviders
            .of(this).get(PrevMatchViewModel::class.java)

        setData()
    }

    private fun setData(){
        prevMatchViewModel.setMatchData(leagueId)
        prevMatchViewModel.getMatchData()
            .observe(this,
                Observer<MatchDiscoverResponse> { t ->
                    matchAdapter.setMatchList(t.events)

                    rvLastMatch.visibility = View.VISIBLE
                    pbLastMatch.visibility = View.INVISIBLE
                })
    }

}
