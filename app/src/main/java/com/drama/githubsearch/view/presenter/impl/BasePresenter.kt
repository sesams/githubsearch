package com.drama.githubsearch.view.presenter.impl

import com.drama.domain.Request
import com.drama.domain.User
import com.drama.domain.interactor.GetUserList
import com.drama.domain.interactor.SetFavorite
import com.drama.githubsearch.view.model.ItemUser
import com.drama.githubsearch.view.model.mapper.Mapper
import com.drama.githubsearch.view.presenter.Presenter
import io.reactivex.observers.DisposableObserver

open class BasePresenter(val view: Presenter.View) : Presenter {

    lateinit var getUserList: GetUserList
    lateinit var setFavorite: SetFavorite

    override fun search(keyword: String, page: Int) {
        getUserList.execute(object : DisposableObserver<ArrayList<User>>() {
            override fun onNext(list: ArrayList<User>) {
                view.updateList(keyword, page, list.map {
                    Mapper.convertToItemUser(it)
                } as ArrayList<ItemUser>)
            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                //notify error...
            }

            override fun onComplete() {}
        }, Request(keyword, page))
    }

    override fun setFavorite(itemUser: ItemUser) {
        setFavorite.execute(object : DisposableObserver<User>() {
            override fun onNext(t: User) {
                view.updateFavorite(itemUser)
            }

            override fun onComplete() {

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                //notify error..
            }
        }, User(itemUser.name, itemUser.avatarUrl, itemUser.isFavorite))
    }

    override fun onDestroy() {
        getUserList.dispose()
        setFavorite.dispose()
    }
}