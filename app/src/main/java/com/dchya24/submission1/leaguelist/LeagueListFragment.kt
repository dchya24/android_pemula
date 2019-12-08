package com.dchya24.submission1.leaguelist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dchya24.submission1.R
import com.dchya24.submission1.adapter.LeagueAdapter
import com.dchya24.submission1.leaguedetail.LeagueDetailActivity
import com.dchya24.submission1.models.League
import kotlinx.android.synthetic.main.fragment_league_list.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 */
class LeagueListFragment : Fragment() {
    private val items: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.d("LeagueListFragment", "Called")
        return inflater.inflate(R.layout.fragment_league_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvLeague.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
        }
        prepareData()
    }

    private fun initData(){
        Log.d("Nani", "Cel")
        val leagueName = resources.getStringArray(R.array.league_name)
        val leagueId = resources.getStringArray(R.array.league_id)
        val leagueLogo = resources.obtainTypedArray(R.array.league_logo)
        val leagueDesc = resources.getStringArray(R.array.league_description)

        for (i in leagueName.indices) {
            val leagueItem = League(leagueId[i], leagueName[i], leagueDesc[i], leagueLogo.getResourceId(i, 0))
            items.add(leagueItem)
        }
        rvLeague.apply{
            adapter = LeagueAdapter(items){
                    startActivity<LeagueDetailActivity>("league" to it)
                }
        }
        leagueLogo.recycle()
    }

    private fun prepareData(){
        hideView(true)
        initData()
        hideView(false)
    }

    private fun hideView(status: Boolean){
        if(status){
            pbLeague.visibility = View.VISIBLE
            rvLeague.visibility = View.INVISIBLE
        }else{
            pbLeague.visibility = View.INVISIBLE
            rvLeague.visibility = View.VISIBLE
        }
    }


}
