package com.drama.githubsearch.view.fragment

import android.content.Context
import com.drama.domain.User
import com.drama.githubsearch.view.adapter.MainListAdapter
import com.drama.githubsearch.view.model.ItemUser
import com.drama.githubsearch.view.presenter.impl.GitPresenterImpl

class GitFragment : BaseFragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = GitPresenterImpl(this)
        channel = object : MainListAdapter.Channel {
            override fun requestItem(keyword: String, page: Int) {
                presenter.search(keyword, page)
            }

            override fun requestFavorite(itemUser: ItemUser): Boolean {
                if (!itemUser.isFavorite) {
                    itemUser.isFavorite = true
                    presenter.setFavorite(itemUser)
                }
                return itemUser.isFavorite
            }
        }
    }
}