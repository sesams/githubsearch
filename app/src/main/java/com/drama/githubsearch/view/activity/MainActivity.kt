package com.drama.githubsearch.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.drama.githubsearch.R
import com.drama.githubsearch.view.adapter.PagerAdapter
import com.drama.githubsearch.view.fragment.BaseFragment
import com.drama.githubsearch.view.model.ItemUser
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        viewPager.adapter = PagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    fun update(itemUser: ItemUser) {
        val pagerAdapter = viewPager.adapter as PagerAdapter
        pagerAdapter.fragments.map {
            (it as BaseFragment).update(itemUser)
        }
    }
}
