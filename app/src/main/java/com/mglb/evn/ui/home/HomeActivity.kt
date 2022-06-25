package com.mglb.evn.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mglb.evn.R
import com.mglb.evn.model.StaffModel
import com.mglb.evn.utils.getRoleNameById
import com.mglb.evn.utils.isAdmin
import kotlinx.android.synthetic.main.activity_home_activity.*
import kotlinx.android.synthetic.main.view_left_menu.*

class HomeActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }
    var homeAdapter: HomeAdapter? = null
    var listStaff = mutableListOf<StaffModel>()

    var userProfile : StaffModel?=null
    val staffId by lazy {
        intent.getIntExtra("data",-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_activity)
        setUpRcv()
        setupObsever()
        viewModel.getAlStaff()
        viewModel.getStaffById(id = staffId)
        setUpFeatureSearch()
        setUpEvent()

    }
private fun setUpEvent(){
    img_lef_menu.setOnClickListener {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        drawer_layout.openDrawer(GravityCompat.START)
    }
}
    private fun setUpRcv() {
        homeAdapter = HomeAdapter(this, listStaff, ::onDeleteStaff, ::onEditStaff)
        rcv_staff.layoutManager = LinearLayoutManager(this)
        rcv_staff.adapter = homeAdapter
    }

    private fun onDeleteStaff(staffModel: StaffModel) {


    }

    private fun onEditStaff(staffModel: StaffModel) {

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setupObsever() {
        viewModel.staffResponseLiveData.observe(this) {
            listStaff.clear()
            listStaff.addAll(it)
            homeAdapter?.notifyDataSetChanged()

        }
        viewModel.myInfo.observe(this){
            userProfile = it
            setUpLeftMenu()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setUpLeftMenu(){
        btn_create_staff.isVisible = userProfile?.isAdmin() ?: false
        Log.e("TAG", "setUpLeftMenu: ${ userProfile?.isAdmin() ?: false} " )
        Log.e("TAG", "setUpLeftMenu: ${ userProfile?.staffRole?.role?.roleActionId} " )
        txt_menu_name.text = userProfile?.lastName + userProfile?.firstName
        txt_menu_role.text = userProfile?.staffRole?.roleId?.getRoleNameById()
        txt_menu_department.text = userProfile?.department.toString()
        txt_menu_address.text = userProfile?.address
        txt_menu_email.text = userProfile?.email

    }


    private fun setUpFeatureSearch() {
        edt_Search.doAfterTextChanged {
            if (it.isNullOrBlank()) {
                viewModel.getAlStaff()
            } else {
                viewModel.search(it.toString())
            }
        }
    }
}