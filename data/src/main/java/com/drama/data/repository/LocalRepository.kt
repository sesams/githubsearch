package com.drama.data.repository

import android.content.Context
import com.drama.data.realm.RealmUser
import com.drama.data.realm.RealmUtil
import com.drama.data.realm.mapper.RealmDataMapper
import com.drama.domain.Request
import com.drama.domain.User
import com.drama.domain.repository.Repository
import io.reactivex.Observable
import io.realm.Realm

class LocalRepository(context: Context) : Repository {

    init {
        Realm.init(context)
    }

    override fun getUserList(params: Request): Observable<ArrayList<User>> {
        return Observable.create {
            val realm = Realm.getDefaultInstance()
            val realmResult = realm.where(RealmUser::class.java)
                    .contains("name", params.name).findAll()

            val list = RealmDataMapper.convertToList(realmResult)
            it.onNext(list)
            it.onComplete()
        }
    }

    override fun setFavorite(user: User): Observable<User> {
        if (user.isFavorite) {
            return Observable.create {
                val realm = Realm.getDefaultInstance()
                realm.beginTransaction()

                val index = RealmUtil.getIncrementalId(realm, RealmUser::class.java)
                val realmUser = realm.createObject(RealmUser::class.java, index)
                realmUser.name = user.name
                realmUser.url = user.ImageUrl
                realm.commitTransaction()

                it.onNext(user)
                it.onComplete()
            }
        }

        return Observable.create {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.where(RealmUser::class.java)
                    .equalTo("name", user.name)
                    .findAll().deleteAllFromRealm()
            realm.commitTransaction()

            it.onNext(user)
            it.onComplete()
        }
    }
}
