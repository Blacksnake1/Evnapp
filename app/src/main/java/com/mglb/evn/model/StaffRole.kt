package com.mglb.evn.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class StaffRole(
    @PrimaryKey
    var id: Int? = null,
    var roleId: Int?=null,
    var staffId: Int?=null,
    var role: Role? = Role()
) : RealmObject()