package com.drama.domain

import com.drama.domain.executor.PostExecutionThread
import com.drama.domain.repository.Repository
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MockGitRepositoryImpl : Repository {
    override fun getUserList(params: Request): Observable<ArrayList<User>> {
        return Observable.create {
            val list = ArrayList<User>()
            list.add(User(params.name, "", false))
            it.onNext(list)
        }
    }
}

class MockLocalRepositoryImpl : Repository {
    override fun getUserList(params: Request): Observable<ArrayList<User>> {
        return Observable.create {
            val list = ArrayList<User>()
            list.add(User(params.name, "", false))
            it.onNext(list)
        }
    }
}

class MockThreadExecutor : Executor {
    override fun execute(command: Runnable?) {
        Executors.newSingleThreadExecutor().execute(command)
    }
}

class MockPostExecutionThread : PostExecutionThread {
    override fun getScheduler(): Scheduler {
        return Schedulers.computation()
    }
}