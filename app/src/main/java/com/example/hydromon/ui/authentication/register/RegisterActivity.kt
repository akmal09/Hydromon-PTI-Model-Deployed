package com.example.hydromon.ui.authentication.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.hydromon.ChooseRoleActivity
import com.example.hydromon.MainActivityOwner
import com.example.hydromon.databinding.ActivityRegisterBinding
import com.example.hydromon.ui.authentication.LoginCookieAttribute
import com.example.hydromon.ui.authentication.RegisterAttribute
import com.example.hydromon.ui.authentication.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterActivityViewModel
    private var registerAttribute : RegisterAttribute = RegisterAttribute()
    private lateinit var loginCookieAttribute: LoginCookieAttribute

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerViewModel = ViewModelProvider(this@RegisterActivity).get(RegisterActivityViewModel::class.java)

        var usernameText:EditText = binding.etUsername

        usernameText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {

                }else{
                    registerAttribute.username = p0.toString()
                }
            }
        })

        val emailText:EditText = binding.etEmail
        emailText.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {

                }else{
                    registerAttribute.email = p0.toString()
                }
            }

        })

        var passwordText:EditText = binding.etPassword

        passwordText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isEmpty()) {

                }else{
                    registerAttribute.password = p0.toString()
                }
            }
        })

        val btRegister : Button = binding.btRegister
        btRegister.setOnClickListener{
            registerViewModel.registerRequest(registerAttribute.username,registerAttribute.email,registerAttribute.password)
            registerViewModel.getRegisterResponse().observe(this,{
                if (it.code == 200) {
                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@RegisterActivity,"${it.status}",Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}