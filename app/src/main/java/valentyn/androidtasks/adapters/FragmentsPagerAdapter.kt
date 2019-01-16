package valentyn.androidtasks.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import valentyn.androidtasks.R
import valentyn.androidtasks.fragments.CityFragment
import valentyn.androidtasks.fragments.ParkFragment


class FragmentsPagerAdapter internal constructor(
    fm: FragmentManager,
    applicationContext: Context
) : FragmentPagerAdapter(fm) {

    private val context = applicationContext
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

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> context.getString(R.string.city_page_title)
            1 -> context.getString(R.string.park_page_title)
            else -> {
                return context.getString(R.string.unknown)
            }
        }
    }

}