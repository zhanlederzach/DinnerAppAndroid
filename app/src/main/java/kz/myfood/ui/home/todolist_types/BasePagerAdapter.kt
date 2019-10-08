package kz.myfood.ui.home.todolist_types

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class BasePagerAdapter(
    fm: FragmentManager,
    val actions: List<Fragment>,
    val titles: List<String>
): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return actions[position]
    }

    override fun getCount(): Int = actions.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}