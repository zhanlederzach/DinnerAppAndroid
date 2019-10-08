package kz.myfood.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kz.myfood.R
import kz.myfood.ui.home.todolist_types.ActiveToDoFragment
import kz.myfood.ui.home.todolist_types.BasePagerAdapter
import kz.myfood.ui.home.todolist_types.HistoryToDoFragment

/**
 * 1.10.19 done by Zhanel
 */
class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setAdapter()
        setData()
    }

    private fun bindViews(view: View) {
        viewPager = view.findViewById(R.id.viewPager)
        tabLayout = view.findViewById(R.id.tabLayout)
    }

    private fun setAdapter() {
        val fragments = arrayListOf<Fragment>(
            ActiveToDoFragment(),
            HistoryToDoFragment()
        )
        val titles = arrayListOf(
            "Active",
            "History"
        )
        val adapter = BasePagerAdapter(childFragmentManager, fragments, titles)
        viewPager.adapter = adapter
        viewPager.offscreenPageLimit = 2
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setData() {

    }

}
