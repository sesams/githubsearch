package com.drama.data.realm

import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.internal.Keep

@Keep
open class RealmUser : RealmObject() {

    @Index
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var url: String = ""

}