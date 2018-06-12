package com.drama.data.repository

import com.drama.data.network.impl.GitApiImpl
import com.drama.domain.Request
import com.drama.domain.User
import com.drama.domain.repository.Repository
import io.reactivex.Observable

class GitRepository : Repository {

    private val gitApi = GitApiImpl()

    override fun getUserList(params: Request): Observable<ArrayList<User>> {
        return gitApi.getUserList(params.name, params.page)
    }

    override fun setFavorite(user: User): Observable<User> {
        return Observable.create {
            it.onNext(user)
            it.onComplete()
        }
    }
}