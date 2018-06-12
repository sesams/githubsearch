package com.drama.data.network.impl

import com.drama.data.network.GIT_BASE_URL
import com.drama.data.network.GitApi
import com.drama.data.network.GitService
import com.drama.data.network.response.mapper.ResponseMapper
import com.drama.domain.User
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitApiImpl : GitApi {

    private val retrofit = Retrofit.Builder()
            .baseUrl(GIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val service = retrofit.create(GitService::class.java)

    override fun getUserList(name: String, page: Int): Observable<ArrayList<User>> {
        return service.getUserList(name, page).map(ResponseMapper::convertToUserData)
    }
}