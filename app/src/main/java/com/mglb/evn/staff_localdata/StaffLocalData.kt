package com.mglb.evn.staff_localdata
import com.mglb.evn.model.StaffModel
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.where

class StaffLocalData {
    private val config = RealmConfiguration.Builder()
        .allowQueriesOnUiThread(true)
        .allowWritesOnUiThread(true)
        .build()
    var realm: Realm = Realm.getInstance(config)



    fun getAllStaff(): MutableList<StaffModel> {
        // you can also filter a collection
        val listStaff: List<StaffModel> = realm.where(StaffModel::class.java).findAll()
        return listStaff.toMutableList()
    }

     fun createStaff(staffModel: StaffModel) {
         realm.beginTransaction()
         try {
             realm.copyToRealmOrUpdate(staffModel)
         }catch (e:Exception){
             e.printStackTrace()
         }
         realm.commitTransaction()
    }

     fun deleteStaff(staffModel: StaffModel) {
        realm.executeTransaction { transactionRealm ->
            val innerYetAnotherTask: StaffModel =
                transactionRealm.where<StaffModel>().equalTo("id", staffModel.id).findFirst()!!
            innerYetAnotherTask.deleteFromRealm()
        }
    }

     fun editStaff(staffModel: StaffModel) {
        realm.executeTransaction { transactionRealm ->
            val innerOtherTask: StaffModel =
                transactionRealm.where<StaffModel>().equalTo("id", staffModel.id).findFirst()!!
            innerOtherTask.firstName = staffModel.firstName
            innerOtherTask.lastName = staffModel.lastName
            innerOtherTask.email = staffModel.email
            innerOtherTask.password = staffModel.password
            innerOtherTask.department = staffModel.department
            innerOtherTask.address = staffModel.address
            innerOtherTask.createdAt = staffModel.createdAt
            innerOtherTask.createdBy = staffModel.createdBy
            innerOtherTask.staffRole = staffModel.staffRole
        }
    }

    fun login(email: String, password: String): StaffModel? {
        return realm.where(StaffModel::class.java).contains("email", email)
            .contains("password", password).findFirst()
    }
    fun searchStaff(textSearch: String): MutableList<StaffModel> {
        return realm.where(StaffModel::class.java).contains("firstName", textSearch).findAll()
    }
    fun getStaffById(id : Int): StaffModel? {
        return realm.where(StaffModel::class.java).contains("id", id.toString()).findFirst()
    }

}

