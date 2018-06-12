package com.drama.githubsearch.view.presenter

import android.content.Context
import com.drama.githubsearch.view.model.ItemUser

interface Presenter {

    fun onDestroy()
    fun search(keyword: String, page: Int)
    fun setFavorite(itemUser: ItemUser)

    interface View {
        fun updateList(keyword: String, page: Int, list: ArrayList<ItemUser>)
        fun updateFavorite(itemUser: ItemUser)
        fun getAppContext(): Context
    }
}