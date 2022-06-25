package com.mglb.evn.utils

import com.mglb.evn.model.ROLE_ADMIN
import com.mglb.evn.model.ROLE_MANAGER
import com.mglb.evn.model.ROLE_STAFF
import com.mglb.evn.model.StaffModel

 fun StaffModel.isAdmin() : Boolean {
    return  staffRole?.roleId == ROLE_ADMIN
}
fun Int.getRoleNameById() : String {
    return when(this){
        ROLE_ADMIN -> "Admin"
        ROLE_MANAGER -> "Manager"

        else -> "Staff"
    }
}