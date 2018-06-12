package com.drama.data.network.response.mapper

import com.drama.data.network.response.GitResponse
import com.drama.domain.User

object ResponseMapper {

    fun convertToUserData(response: GitResponse): ArrayList<User> {
        val result = ArrayList<User>()
        response.items.map {
            result.add(User(it.login, it.avatarUrl, false))
        }
        return result
    }
}