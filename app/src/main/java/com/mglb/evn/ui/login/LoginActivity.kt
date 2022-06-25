package com.mglb.evn.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.mglb.evn.R
import com.mglb.evn.model.ROLE_ADMIN
import com.mglb.evn.model.Role
import com.mglb.evn.model.StaffModel
import com.mglb.evn.model.StaffRole
import com.mglb.evn.repository.StaffRepository
import com.mglb.evn.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    var email : String = ""
    var password : String = ""
    var isShowPassword : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
       createAdmin()
        setUpObserver()
        btn_login.setOnClickListener {
            if (email.isBlank()){
                Toast.makeText(this, "email isEmpty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (password.isBlank()){
                Toast.makeText(this, "password isEmpty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.login(email,password)

        }
        img_eye.setOnClickListener {
            if(isShowPassword){
                edt_Password.transformationMethod = HideReturnsTransformationMethod.getInstance()
                img_eye.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)


            } else{
                edt_Password.transformationMethod = PasswordTransformationMethod.getInstance()
                img_eye.setImageResource(R.drawable.ic_baseline_remove_red_eye_24)

            }
            isShowPassword =! isShowPassword
        }
        edt_Email.doOnTextChanged { text, start, before, count -> email = text.toString() }
        edt_Password.doOnTextChanged { text, start, before, count -> password = text.toString() }
    }
    private fun createAdmin(){
        var admin = StaffModel().apply {
            staffRole = StaffRole().apply {
                role = Role().apply {
                    roleActionId = ROLE_ADMIN
                }

            }
            email = "admin@123.com"
            password = "123123"
        }
        StaffRepository().createAccount(admin)

    }

    private fun setUpObserver(){
        viewModel.staffResponseLiveData.observe(this) {
            if (it != null) {
                Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show()
                var intent = Intent(this,HomeActivity::class.java)
                intent.putExtra("data",it.id)
                startActivity(intent)
                finish()
            }
         else{
                Toast.makeText(this, "Login Fail!", Toast.LENGTH_LONG).show()
         }
        }
    }

}