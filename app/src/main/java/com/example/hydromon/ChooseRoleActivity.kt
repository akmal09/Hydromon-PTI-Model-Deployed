package com.example.hydromon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.hydromon.databinding.ActivityChooseRoleBinding
import com.example.hydromon.signuphydroponics.SignUpHydroponicsActivity
import com.example.hydromon.ui.authentication.LoginCookieAttribute

class ChooseRoleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChooseRoleBinding
    companion object{
        var loginCookieAttribute="loginAttribute"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChooseRoleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var loginAttribute = intent.getParcelableExtra<LoginCookieAttribute>(loginCookieAttribute) as LoginCookieAttribute
        Log.d("id user","${loginAttribute}")

        val signUpHydroponics : Button = binding.hydroponics
        val trackOwnerHydroponics : Button = binding.viewer

        signUpHydroponics.setOnClickListener{
            val intent = Intent(this@ChooseRoleActivity, SignUpHydroponicsActivity::class.java)
            intent.putExtra(SignUpHydroponicsActivity.loginCookieAttribute, loginAttribute)
            startActivity(intent)
        }

        trackOwnerHydroponics.setOnClickListener{
            val intent = Intent(this@ChooseRoleActivity, EnterCodeViewerActivity::class.java)
            intent.putExtra(EnterCodeViewerActivity.idUser, loginAttribute)
            startActivity(intent)
        }
    }
}