package com.drama.domain.interactor

import com.drama.domain.Request
import com.drama.domain.User
import com.drama.domain.executor.ExecutionThread
import com.drama.domain.executor.PostExecutionThread
import com.drama.domain.repository.Repository
import io.reactivex.Observable

class GetUserList(private val repository: Repository,
                  executionThread: ExecutionThread,
                  postExecutionThread: PostExecutionThread) : UseCase<ArrayList<User>, Request>(executionThread, postExecutionThread) {

    override fun buildUseCaseObservable(params: Request): Observable<ArrayList<User>> {
        return repository.getUserList(params)
    }
}