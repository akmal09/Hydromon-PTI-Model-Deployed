package com.example.hydromon.signuphydroponics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.hydromon.MainActivityOwner
import com.example.hydromon.databinding.ActivitySignUpHydroponicsBinding
import com.example.hydromon.ui.authentication.LoginCookieAttribute
import com.example.hydromon.ui.authentication.login.LoginCookie

class SignUpHydroponicsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpHydroponicsBinding
    val signUpHydroponicsAttribute : SignUpHydroponicsAttribute = SignUpHydroponicsAttribute()
    private lateinit var signUpHydroponicsViewModel: SignUpHydroponicsViewModel
    private lateinit var loginCookie : LoginCookie
    companion object{
        var loginCookieAttribute="loginAttribute"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpHydroponicsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var loginAttribute = intent.getParcelableExtra<LoginCookieAttribute>(loginCookieAttribute)
        signUpHydroponicsViewModel = ViewModelProvider(this@SignUpHydroponicsActivity).get(
            SignUpHydroponicsViewModel::class.java)
        if (loginAttribute != null) {
            signUpHydroponicsAttribute.idUser = loginAttribute.id
        }

        val locationMap : EditText = binding.locationMap
        locationMap.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                signUpHydroponicsAttribute.locationMapText = p0.toString()
            }
        })

        val kodeHydroponic : EditText = binding.inputEditTextHc
        kodeHydroponic.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                signUpHydroponicsAttribute.kodeHydroponicText = p0.toString()
            }
        })

        val namaHydroponic : EditText = binding.inputEditTextHn
        namaHydroponic.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                signUpHydroponicsAttribute.namaHydroponicText = p0.toString()
            }
        })

        val backButton : ImageView = binding.backButton
        backButton.setOnClickListener{
            finish()
        }

        val signUpButton : Button = binding.signUpButton
        signUpButton.setOnClickListener{
            Log.d("input hydroponics attribute", "${signUpHydroponicsAttribute} ")
            if (loginAttribute != null) {
                signUpHydroponicsViewModel.insertHydroponicsData(signUpHydroponicsAttribute.namaHydroponicText, signUpHydroponicsAttribute.locationMapText,signUpHydroponicsAttribute.idUser, signUpHydroponicsAttribute.kodeHydroponicText, loginAttribute.token)
                signUpHydroponicsViewModel.changeUserRole(signUpHydroponicsAttribute.idUser,loginAttribute.token)

                signUpHydroponicsViewModel.getInsertHydroponicsResponse().observe(this,{
                    Log.d("insert hydro act","${it.code}")
                    if (it.code == 200) {
                        changeUserRole(signUpHydroponicsViewModel)
                    }else{
                        Toast.makeText(this@SignUpHydroponicsActivity, "Role Failed to updated", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

    fun changeUserRole(signUpHydroponicsViewModel: SignUpHydroponicsViewModel) {
        signUpHydroponicsViewModel.getChangeUserRoleResponse().observe(this,{
            Log.d("change user act","${it.code}")
            if (it.code == 200) {
                moveToMainOwner()
            }else{
                Toast.makeText(this@SignUpHydroponicsActivity, "Alat sudah ada yang punya", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun moveToMainOwner() {
        loginCookie = LoginCookie(this)
        loginCookie.setRoleCookie(1)
        val moveToOwner = Intent(this@SignUpHydroponicsActivity, MainActivityOwner::class.java)
        moveToOwner.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        moveToOwner.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        moveToOwner.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(moveToOwner)
        finish()
    }
}

