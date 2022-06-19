package com.example.hydromon.ui.authentication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.hydromon.*
import com.example.hydromon.databinding.ActivityLoginBinding
import com.example.hydromon.ui.authentication.LoginAtrribute
import com.example.hydromon.ui.authentication.LoginCookieAttribute
import com.example.hydromon.ui.authentication.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding:ActivityLoginBinding
    private lateinit var loginViewModel : LoginActivityViewModel
    private var loginAtrribute : LoginAtrribute = LoginAtrribute()
    private lateinit var loadCookie : LoginCookieAttribute
    private lateinit var loginCookieAttribute: LoginCookieAttribute
    private lateinit var saveCookie:LoginCookie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCookie = loadCookie()
        loginCookieAttribute = LoginCookieAttribute()
        loginViewModel = ViewModelProvider(this@LoginActivity).get(LoginActivityViewModel::class.java)

        Log.d("cek cookie masuk","${loadCookie}")
        if (loadCookie.role != -1 && loadCookie.token != "") {
            if (loadCookie.role == 1) {
                val intent = Intent(this@LoginActivity, MainActivityOwner::class.java)
                startActivity(intent)
                finish()
            }else if (loadCookie.role == 0) {
                val intent = Intent(this@LoginActivity, MainActivityViewer::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@LoginActivity, ChooseRoleActivity::class.java)
                intent.putExtra(ChooseRoleActivity.loginCookieAttribute, loadCookie)
                startActivity(intent)
                finish()
            }
        }else{
            val emailtext : EditText = binding.etEmail
            emailtext.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    if (p0.toString().isEmpty()) {

                    }else{
                        loginAtrribute.email = p0.toString().trim()
                    }
                }
            })

            val passwordText : EditText = binding.etPassword
            passwordText.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(p0: Editable?) {
                    loginAtrribute.password = p0.toString().trim()
                }
            })

            val buttonLogin : Button = binding.btLogin
            buttonLogin.setOnClickListener{
                Log.d("login", "${loginAtrribute.email} dan ${loginAtrribute.password}")
                loginViewModel.loginRequest(loginAtrribute.email,loginAtrribute.password)
                loginViewModel.getLoginRequest().observe(this) {
                    if (it.code == "200") {
                        if (it.data?.role == 1) {
                            loginCookieAttribute.role = it.data?.role
                            loginCookieAttribute.token = it.token
                            loginCookieAttribute.id = it.data?.id
                            loginCookie(loginCookieAttribute)
                            val intent = Intent(this@LoginActivity, MainActivityOwner::class.java)
                            startActivity(intent)
                            finish()
                        } else if (it.data?.role == 0) {
                            loginCookieAttribute.role = it.data?.role
                            loginCookieAttribute.token = it.token
                            loginCookieAttribute.id = it.data?.id
                            loginCookie(loginCookieAttribute)
                            val intent = Intent(this@LoginActivity, MainActivityViewer::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            loginCookieAttribute.role = 2
                            loginCookieAttribute.token = it.token
                            loginCookieAttribute.id = it.data?.id.toString()
                            loginCookie(loginCookieAttribute)
//                            Log.d("id user","${it.data?.id}")
                            val intent = Intent(this@LoginActivity, ChooseRoleActivity::class.java)
                            intent.putExtra(
                                ChooseRoleActivity.loginCookieAttribute,
                                loginCookieAttribute
                            )
                            startActivity(intent)
                            finish()
                        }
                        Log.d("cek cookie berhasil", "${loadCookie}")
                    } else {
                        Toast.makeText(this@LoginActivity, "${it.status}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            val daftarSekarang : TextView = binding.daftarSekarang
            daftarSekarang.setOnClickListener {
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun loginCookie(loginCookieAtrribute: LoginCookieAttribute) {
        saveCookie = LoginCookie(this)
        saveCookie.setCookie(loginCookieAtrribute)
    }

    private fun loadCookie(): LoginCookieAttribute {
        val cookiePlace = LoginCookie(this)
        val loadCookie : LoginCookieAttribute = cookiePlace.getCookie()
        return loadCookie
    }
}