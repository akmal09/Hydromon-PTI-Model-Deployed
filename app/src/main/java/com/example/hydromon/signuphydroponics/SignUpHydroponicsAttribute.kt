package com.example.hydromon.signuphydroponics

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SignUpHydroponicsAttribute(
    var locationMapText: String = "",
    var kodeHydroponicText:String = "",
    var namaHydroponicText:String = "",
    var userAttribute:String = "",
    var idUser:String = ""
): Parcelable