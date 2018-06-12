package com.drama.data.realm

import io.realm.Realm
import io.realm.RealmModel

object RealmUtil {
    fun <E : RealmModel> getIncrementalId(realm: Realm, cls: Class<E>): Int {
        return getIncrementalId(realm, cls, 0)
    }

    fun <E : RealmModel> getIncrementalId(realm: Realm, cls: Class<E>, defaultValue: Int): Int {
        return try {
            realm.where(cls).max("id")!!.toInt() + 1
        } catch (e: Exception) {
            defaultValue
        }
    }
}