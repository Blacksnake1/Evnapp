package com.mglb.evn.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.mglb.evn.R
import com.mglb.evn.model.StaffModel

class DetailStaffDialog(
    var staffModel: StaffModel
) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       return LayoutInflater.from(requireContext()).inflate(R.layout.detail_staff_dialog,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }
}