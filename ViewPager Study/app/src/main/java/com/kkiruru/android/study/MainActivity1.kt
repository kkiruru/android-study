package com.kkiruru.android.study

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout

class MainActivity1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        var newTab = tabLayout.newTab()
        newTab.text = "진행상태"
        tabLayout.addTab(newTab, 0, true)
        tabLayout.selectTab(newTab)
        newTab = tabLayout.newTab()
        newTab.text = "결제금액"
        tabLayout.addTab(newTab, 1, false)

        newTab = tabLayout.newTab()
        newTab.text = "상품주문 내역"
        tabLayout.addTab(newTab, 2, false)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.d("onTabSelected", "")
                onChangedTab(tabLayout)
            }
        })

        navigateToReady()

    }

    private fun navigateToReady() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BlankFragment())
            .commitNow()
    }


    private var fragmentIndex = 0

    private fun onChangedTab(tab: TabLayout) {
        val transaction = supportFragmentManager.beginTransaction()
        var tag = "fragment_${fragmentIndex}"
        var fragment = supportFragmentManager.findFragmentByTag(tag)

        if (fragment is Fragment) {
            fragment.onPause()
            transaction.hide(fragment)
        }
        fragmentIndex = tab.selectedTabPosition

        tag = "fragment_${tab.selectedTabPosition}"
        fragment = supportFragmentManager.findFragmentByTag(tag)
        if (fragment != null) {
            transaction.show(fragment)
            fragment.onResume()
        } else {
            fragment = getFragment(tab.selectedTabPosition)
            fragment?.run {
                transaction.add(R.id.container, this, tag)
            }
        }
        transaction.commitAllowingStateLoss()
    }

    private var fragments: Array<Fragment?> = arrayOfNulls(3)


    private fun getFragment(index: Int): Fragment? {

        val fragment = fragments[index]
        if (fragment == null) {
            when (index) {
                0 -> fragments[index] = BlankFragment()
                1 -> fragments[index] = BlankFragment2()
                2 -> fragments[index] = BlankFragment3()
            }
        }
        return fragments[index]
    }

    class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fragmentManager, lifecycle) {

        override fun getItemCount(): Int {
            return 3
        }

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return BlankFragment()
                1 -> return BlankFragment2()
                2 -> return BlankFragment3()
            }
            return BlankFragment()
        }
    }
}