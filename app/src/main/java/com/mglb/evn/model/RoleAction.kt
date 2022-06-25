package com.mglb.evn.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey


open class RoleAction(
    @PrimaryKey
    var id : Int? = null,

    var createStaff : Int?=null,

    var editStaff : Int?=null,

    var deleteStaff : Int?=null,

    var createRole : Int?=null,

    var editRole : Int?=null,

    var deleteRole : Int?=null,

    var createdAt : String?=null,

    var createBy : Int?=null
) : RealmObject()