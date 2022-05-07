package academy.nouri.sajadcourse.pages

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter

class ViewPagerAdapter(fm: FragmentManager, private val totalPages: Int) : FragmentStatePagerAdapter(fm) {

    override fun getCount() = totalPages

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ProfileFragment()
            else -> ShopFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        super.getPageTitle(position)

        return when (position) {
            0 -> "Home"
            1 -> "Profile"
            else -> "Shop"
        }
    }
}