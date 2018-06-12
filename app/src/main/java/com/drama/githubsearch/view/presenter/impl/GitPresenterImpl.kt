package com.drama.githubsearch.view.presenter.impl

import android.os.AsyncTask
import com.drama.data.repository.GitRepository
import com.drama.data.repository.LocalRepository
import com.drama.domain.executor.ExecutionThread
import com.drama.domain.interactor.GetUserList
import com.drama.domain.interactor.SetFavorite
import com.drama.githubsearch.executor.UIThread
import com.drama.githubsearch.view.presenter.Presenter
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GitPresenterImpl(view: Presenter.View) : BasePresenter(view) {

    private val postThread = UIThread
    private val gitWorkerThread = object : ExecutionThread {
        override fun getScheduler(): Scheduler {
            return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)
        }
    }
    private val realmWorkerThread = object : ExecutionThread {
        override fun getScheduler(): Scheduler {
            return AndroidSchedulers.mainThread()
        }
    }

    init {
        getUserList = GetUserList(GitRepository(), gitWorkerThread, postThread)
        setFavorite = SetFavorite(LocalRepository(view.getAppContext()), realmWorkerThread, postThread)
    }
}