package com.drama.githubsearch.view.adapter

import android.support.v7.widget.RecyclerView
import com.drama.githubsearch.view.model.ItemHeader
import com.drama.githubsearch.view.model.ItemUser
import com.drama.githubsearch.view.model.ItemUserViewBinder
import tellh.com.stickyheaderview_rv.adapter.DataBean
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter
import tellh.com.stickyheaderview_rv.adapter.ViewBinder
import java.util.ArrayList
import kotlin.Comparator

class MainListAdapter(list: List<DataBean>, private val channel: Channel) : StickyHeaderViewAdapter(list) {

    private val itemList = ArrayList<ItemUser>()
    private var keyword = ""
    private var page = 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position == itemCount - 1)
            channel.requestItem(keyword, ++page)
    }

    fun updateList(keyword: String, page: Int, list: ArrayList<ItemUser>) {
        if (list.isEmpty()) return

        if (this.keyword != keyword) {
            itemList.clear()
        }
        this.keyword = keyword
        this.page = page

        list.sortWith(Comparator { o1, o2 -> o1.name.compareTo(o2.name, true) })
        itemList.addAll(list)

        val userListBak = ArrayList<DataBean>()
        var currentPrefix = itemList[0].name.substring(0, 1).toUpperCase()
        userListBak.add(ItemHeader(currentPrefix))

        itemList.forEach {
            if (currentPrefix.compareTo(it.name.substring(0, 1), true) == 0)
                userListBak.add(it)
            else {
                currentPrefix = it.name.substring(0, 1).toUpperCase()
                userListBak.add(ItemHeader(currentPrefix))
                userListBak.add(it)
            }
        }

        refresh(userListBak)
    }

    fun updateFavorite(itemUser: ItemUser) {
        if (!itemUser.isFavorite) {
            for (dataBean in displayList) {
                if (dataBean is ItemUser && dataBean.name == itemUser.name) {
                    dataBean.isFavorite = false
                    break
                }
            }
        }
        notifyDataSetChanged()
    }

    fun removeItem(itemUser: ItemUser) {
        if (!itemUser.isFavorite) {
            delete(displayList.indexOf(itemUser))
            if (displayList.size == 1) {
                refresh(ArrayList())
            }

            var index = 0
            for (user in itemList) {
                if (user.name == itemUser.name) {
                    index = itemList.indexOf(user)
                    break
                }
            }
            itemList.removeAt(index)
        }
        notifyDataSetChanged()
    }

    override fun RegisterItemType(viewBinder: ViewBinder<*, *>?): StickyHeaderViewAdapter {
        if (viewBinder is ItemUserViewBinder) {
            viewBinder.setListener {
                channel.requestFavorite(it)
            }
        }
        return super.RegisterItemType(viewBinder)
    }

    interface Channel {
        fun requestItem(keyword: String, page: Int)
        fun requestFavorite(itemUser: ItemUser): Boolean
    }
}