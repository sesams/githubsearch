package com.drama.domain.interactor

import com.drama.domain.executor.ExecutionThread
import com.drama.domain.executor.PostExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

abstract class UseCase<T, Params>(private val workThread: ExecutionThread,
                                  private val postExecutionThread: PostExecutionThread) {

    private val compositeDisposable = CompositeDisposable()

    abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(observer: DisposableObserver<T>, params: Params) {
        val observable = buildUseCaseObservable(params)
                .subscribeOn(workThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(observer)

        compositeDisposable.add(observable)
    }

    fun dispose() {
        compositeDisposable.dispose()
    }

}