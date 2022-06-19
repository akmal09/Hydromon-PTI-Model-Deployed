package com.example.hydromon.ui.authentication

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class LoginAtrribute(
    var email:String="",
    var password:String=""
)

data class RegisterAttribute(
    var username:String="",
    var email:String="",
    var password:String=""
)

@Parcelize
data class LoginCookieAttribute(
    var role:Int=-1,
    var token:String="",
    var id:String=""
):Parcelable
