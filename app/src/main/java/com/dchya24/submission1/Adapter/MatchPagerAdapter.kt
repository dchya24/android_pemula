package com.dchya24.submission1.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dchya24.submission1.nextmatch.NextMatchFragment
import com.dchya24.submission1.prevmatch.PrevMatchFragment

class MatchPagerAdapter(fm: FragmentManager, leagueId: Int): FragmentPagerAdapter(fm) {
    private val pages = listOf(
        PrevMatchFragment(leagueId),
        NextMatchFragment(leagueId)
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Prev Match"
            1 -> "Next Match"
            else -> "Third Path"
        }

    }
}