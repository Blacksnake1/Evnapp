package com.mglb.evn.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mglb.evn.model.StaffModel
import com.mglb.evn.repository.StaffRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private var staffRepository: StaffRepository = StaffRepository()
):ViewModel() {
    var staffResponseLiveData = MutableLiveData<MutableList<StaffModel>>()
    var myInfo = MutableLiveData<StaffModel>()


    fun getAlStaff(){
        viewModelScope.launch ( Dispatchers.Main ){
            staffResponseLiveData.postValue(staffRepository.getAllStaff())

        }
    }
    fun search(textSearch : String){
        viewModelScope.launch ( Dispatchers.Main) {
            staffResponseLiveData.postValue(staffRepository.searchStaff(textSearch))


        }
    }
    fun getStaffById(id : Int){
        viewModelScope.launch ( Dispatchers.Main ){
           myInfo.postValue(staffRepository.getStaffById(id))
        }
    }
}