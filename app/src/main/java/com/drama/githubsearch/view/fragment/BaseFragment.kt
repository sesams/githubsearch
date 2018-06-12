package com.drama.githubsearch.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.drama.githubsearch.R
import com.drama.githubsearch.view.activity.MainActivity
import com.drama.githubsearch.view.adapter.MainListAdapter
import com.drama.githubsearch.view.model.ItemHeaderViewBinder
import com.drama.githubsearch.view.model.ItemUser
import com.drama.githubsearch.view.model.ItemUserViewBinder
import com.drama.githubsearch.view.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_page.*
import tellh.com.stickyheaderview_rv.adapter.StickyHeaderViewAdapter

open class BaseFragment : Fragment(), Presenter.View {

    lateinit var presenter: Presenter
    lateinit var channel: MainListAdapter.Channel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = MainListAdapter(ArrayList(), channel)
                .RegisterItemType(ItemUserViewBinder())
                .RegisterItemType(ItemHeaderViewBinder())

        adapter.setDataSetChangeListener(object : StickyHeaderViewAdapter.DataSetChangeListener {
            override fun remove(p0: Int) {}
            override fun onClearAll() {}
        })

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        editText.addTextChangedListener(watcher)
    }

    override fun updateList(keyword: String, page: Int, list: ArrayList<ItemUser>) {
        (recyclerView.adapter as MainListAdapter).updateList(keyword, page, list)
    }

    override fun updateFavorite(itemUser: ItemUser) {
        (activity as MainActivity).update(itemUser)
    }

    fun update(itemUser: ItemUser) {
        if (this is GitFragment)
            (recyclerView.adapter as MainListAdapter).updateFavorite(itemUser)
        else
            (recyclerView.adapter as MainListAdapter).removeItem(itemUser)
    }

    override fun getAppContext(): Context {
        return requireContext().applicationContext
    }

    private val watcher = object : TextWatcher {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.let {
                if (it.isEmpty()) {
                    (recyclerView.adapter as MainListAdapter).clear(recyclerView)
                } else {
                    presenter.search(s.toString(), 1)
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}