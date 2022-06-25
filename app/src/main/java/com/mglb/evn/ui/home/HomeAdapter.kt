package com.mglb.evn.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.mglb.evn.databinding.ItemSearchStaffBinding
import com.mglb.evn.model.ROLE_ADMIN
import com.mglb.evn.model.StaffModel
import com.mglb.evn.utils.isAdmin

class HomeAdapter(
    var context : Context,
    var listStaff : MutableList<StaffModel>,
    var setOnDeleteStaff : (item : StaffModel) -> Unit,
    var setOnEditStaff : (item : StaffModel) -> Unit,


) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemSearchStaffBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchStaffBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var staff = listStaff[position]
        var binding = holder.binding
        binding.txtName.text = staff.lastName + staff.firstName
        binding.txtDepartment.text = staff.department
        binding.txtRole.text = staff.staffRole?.role?.name

        if (staff.isAdmin()){
            binding.imgEdit.isVisible = true
            binding.itemDelete.isVisible = true
        }else{
            binding.imgEdit.isVisible = false
            binding.itemDelete.isVisible = false
        }

        binding.imgEdit.setOnClickListener {
            setOnEditStaff.invoke(staff)
        }
        binding.itemDelete.setOnClickListener {
            setOnDeleteStaff.invoke(staff)
        }
    }



    override fun getItemCount(): Int = listStaff.size
    }