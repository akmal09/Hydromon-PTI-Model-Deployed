package com.example.hydromon.ui.authentication.login

import android.content.Context
import com.example.hydromon.ui.authentication.LoginCookieAttribute

class LoginCookie(context: Context) {
    companion object{
        private const val token = "token"
        private const val role = "role"
        private const val id = "id"
    }

    private val cookie = context.getSharedPreferences("save_cookie", Context.MODE_PRIVATE)

    fun setCookie(loginCookie: LoginCookieAttribute) {
        val cookie = cookie.edit()
        cookie.putInt(role,loginCookie.role)
        cookie.putString(token,loginCookie.token)
        cookie.putString(id,loginCookie.id)
        cookie.apply()
    }

    fun getCookie(): LoginCookieAttribute {
        val loginCookieAttribute = LoginCookieAttribute()
        loginCookieAttribute.role = cookie.getInt(role,-1)
        loginCookieAttribute.token = cookie.getString(token,"").toString()
        loginCookieAttribute.id = cookie.getString(id,"").toString()
        return loginCookieAttribute
    }

    fun setRoleCookie(roleProcess:Int) {
        val cookie = cookie.edit()
        cookie.putInt(role,roleProcess)
        cookie.apply()
    }

    fun deleteCookie() {
        val cookie = cookie.edit()
        cookie.clear().apply()
    }
}