package com.mglb.evn.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mglb.evn.model.StaffModel
import com.mglb.evn.repository.StaffRepository
import com.mglb.evn.staff_localdata.StaffLocalData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private var staffRepository: StaffRepository = StaffRepository()
) : ViewModel() {
    var staffResponseLiveData = MutableLiveData<StaffModel?>()


    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.Main) {
            staffResponseLiveData.postValue(staffRepository.login(email, password))
        }
    }

}