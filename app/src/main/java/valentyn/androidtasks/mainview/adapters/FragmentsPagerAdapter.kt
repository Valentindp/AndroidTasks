package valentyn.androidtasks.mainview.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import valentyn.androidtasks.mainview.mainfragments.CityListFragment
import valentyn.androidtasks.mainview.mainfragments.ParkListFragment


class FragmentsPagerAdapter internal constructor(
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CityListFragment()
            1 -> fragment = ParkListFragment()
        }

        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

}