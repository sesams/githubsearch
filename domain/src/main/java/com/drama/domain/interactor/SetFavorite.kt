package com.drama.domain.interactor

import com.drama.domain.User
import com.drama.domain.executor.ExecutionThread
import com.drama.domain.executor.PostExecutionThread
import com.drama.domain.repository.Repository
import io.reactivex.Observable

class SetFavorite(private val repository: Repository,
                  executionThread: ExecutionThread,
                  postExecutionThread: PostExecutionThread) : UseCase<User, User>(executionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: User): Observable<User> {
        return repository.setFavorite(params)
    }
}