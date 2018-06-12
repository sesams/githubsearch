package com.drama.githubsearch.view.fragment

import android.content.Context
import com.drama.githubsearch.view.activity.MainActivity
import com.drama.githubsearch.view.adapter.MainListAdapter
import com.drama.githubsearch.view.model.ItemUser
import com.drama.githubsearch.view.presenter.impl.LocalPresenterImpl

class LocalFragment : BaseFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = LocalPresenterImpl(this)
        channel = object : MainListAdapter.Channel {
            override fun requestItem(keyword: String, page: Int) {}

            override fun requestFavorite(itemUser: ItemUser): Boolean {
                if (itemUser.isFavorite) {
                    itemUser.isFavorite = false
                    presenter.setFavorite(itemUser)
                }
                return itemUser.isFavorite
            }
        }
    }
}