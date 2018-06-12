package com.drama.githubsearch.view.presenter.impl

import com.drama.data.repository.LocalRepository
import com.drama.domain.executor.ExecutionThread
import com.drama.domain.interactor.GetUserList
import com.drama.domain.interactor.SetFavorite
import com.drama.githubsearch.executor.UIThread
import com.drama.githubsearch.view.presenter.Presenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


class LocalPresenterImpl(view: Presenter.View) : BasePresenter(view) {

    private val postThread = UIThread
    private val executionThread = object : ExecutionThread {
        override fun getScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }

    init {
        val repository = LocalRepository(view.getAppContext())
        getUserList = GetUserList(repository, executionThread, postThread)
        setFavorite = SetFavorite(repository, executionThread, postThread)
    }

}