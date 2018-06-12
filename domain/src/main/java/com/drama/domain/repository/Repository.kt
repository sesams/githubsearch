package com.drama.domain.repository

import com.drama.domain.Request
import com.drama.domain.User
import io.reactivex.Observable

interface Repository {
    fun getUserList(params: Request): Observable<ArrayList<User>>
    fun setFavorite(user: User): Observable<User>
}