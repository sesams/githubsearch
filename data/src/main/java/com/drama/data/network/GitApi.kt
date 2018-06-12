package com.drama.data.network

import com.drama.data.network.response.GitResponse
import com.drama.domain.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

const val GIT_BASE_URL = "https://api.github.com"
const val GIT_SEARCH_USER = "/search/users"

interface GitApi {
    fun getUserList(name: String, page: Int): Observable<ArrayList<User>>
}

interface GitService {

    @GET(GIT_SEARCH_USER)
    fun getUserList(@Query("q") name: String,
                    @Query("page") page: Int): Observable<GitResponse>
}