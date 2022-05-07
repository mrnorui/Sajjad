package academy.nouri.sajadcourse.pages

import academy.nouri.sajadcourse.R
import academy.nouri.sajadcourse.databinding.ActivityPagesBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayout

class PagesActivity : AppCompatActivity() {
    //Binding
    private lateinit var binding: ActivityPagesBinding

    //Other
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //InitViews
        binding.apply {
            tabLayout.addTab(tabLayout.newTab().setText("Home"))
            tabLayout.addTab(tabLayout.newTab().setText("Profile"))
            tabLayout.addTab(tabLayout.newTab().setText("Shop"))
            tabLayout.tabGravity = TabLayout.GRAVITY_FILL
            tabLayout.tabMode = TabLayout.MODE_FIXED
            //Adapter
            pagerAdapter = ViewPagerAdapter(supportFragmentManager, tabLayout.tabCount)
            viewPager.adapter = pagerAdapter
            tabLayout.setupWithViewPager(viewPager)
            //Item selected
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab) {
                    viewPager.currentItem = tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }

            })
        }
    }
}