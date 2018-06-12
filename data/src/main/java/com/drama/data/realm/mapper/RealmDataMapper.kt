package com.drama.data.realm.mapper

import com.drama.data.realm.RealmUser
import com.drama.domain.User
import io.realm.RealmResults

object RealmDataMapper {

    fun convertToUser(realmUser: RealmUser): User {
        return User(realmUser.name, realmUser.url, true)
    }

    fun convertToList(realmResults: RealmResults<RealmUser>): ArrayList<User> {
        val result = ArrayList<User>()
        realmResults.map {
            result.add(User(it.name, it.url, true))
        }
        return result
    }
}