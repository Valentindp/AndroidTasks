package valentyn.androidtasks.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import valentyn.androidtasks.fragments.CityFragment
import valentyn.androidtasks.fragments.ParkFragment


class FragmentsPagerAdapter internal constructor(
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CityFragment()
            1 -> fragment = ParkFragment()
        }
        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

}