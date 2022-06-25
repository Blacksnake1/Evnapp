package com.mglb.evn.repository

import com.mglb.evn.model.StaffModel
import com.mglb.evn.staff_localdata.StaffLocalData

class StaffRepository(
private val staffLocalData : StaffLocalData = StaffLocalData()
) {
    fun login(email : String,password : String) : StaffModel?{
        return staffLocalData.login(email,password)
    }
    fun createAccount(staffModel: StaffModel)  {
        return staffLocalData.createStaff(staffModel)
    }
    fun getAllStaff() : MutableList<StaffModel>{
        return staffLocalData.getAllStaff()
    }
    fun searchStaff(textSearch : String) : MutableList<StaffModel>{
        return staffLocalData.searchStaff(textSearch)
    }
    fun getStaffById(id : Int): StaffModel? {
        return staffLocalData.getStaffById(id)
    }
}