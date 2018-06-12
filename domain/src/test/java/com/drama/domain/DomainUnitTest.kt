package com.drama.domain

import com.drama.domain.interactor.GetUserList
import io.reactivex.observers.DisposableObserver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DomainUnitTest {

    @Test
    fun test_search() {
        val executor = MockThreadExecutor()
        val postExecutionThread = MockPostExecutionThread()

        var request = Request("Tom", 1)
        var observable = GetUserList(MockGitRepositoryImpl(), executor, postExecutionThread)
        assertNotEquals(null, observable)
        test_Observable(observable, request)

        request = Request("Michael", 1)
        observable = GetUserList(MockLocalRepositoryImpl(), executor, postExecutionThread)
        assertNotEquals(null, observable)
        test_Observable(observable, request)
    }

    private fun test_Observable(observable: GetUserList, params: Request) {
        observable.execute(object : DisposableObserver<ArrayList<User>>() {
            override fun onComplete() {
            }

            override fun onNext(list: ArrayList<User>) {
                val item = list[0]
                assertNotEquals(null, item)
                assertEquals(params.name, item.name)
            }

            override fun onError(e: Throwable) {
            }
        }, params)
    }
}