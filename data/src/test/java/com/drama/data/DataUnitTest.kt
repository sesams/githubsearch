package com.drama.data

import com.drama.data.repository.GitRepository
import com.drama.domain.Request
import org.junit.Assert.assertNotEquals
import org.junit.Test

class DataUnitTest {

    @Test
    fun test_repository() {
        val gitRepository = GitRepository()
        val userNameObservable = gitRepository.getUserList(Request("tom", 1))
        assertNotEquals(null, userNameObservable)
        userNameObservable.subscribe {
            assertNotEquals(null, it)
            assertNotEquals(0, it.size)
        }
    }
}