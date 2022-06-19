package com.example.hydromon.api

import com.google.gson.annotations.SerializedName
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

data class RegisterResponse(
    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("status")
    val status: String,
)

data class DataUser(
    @field:SerializedName("createdAt")
    val createdAt: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("nama_lengkap")
    val namaLengkap: String,

    @field:SerializedName("telepon")
    val telepon: String,

    @field:SerializedName("_id")
    val id: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("updatedAt")
    val updatedAt: String
)

data class LoginResponse(
    @field:SerializedName("code")
    val code: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("token")
    val token: String,

    @field:SerializedName("data")
    val data: DataLogin?
)

data class DataLogin(
    @field:SerializedName("role")
    val role: Int?=null,

    @field:SerializedName("id")
    val id: String=""
)

data class ProfileResponse(
    @field:SerializedName("code")
    val code: String? = null,
    @field:SerializedName("status")
    val status: String? = null,
    @field:SerializedName("data")
    val data: DataProfileResponse? = null
)

data class DataProfileResponse(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("nama_lengkap")
    val nama_lengkap: String? = null,


)

data class ResponseHydroponicsInsert(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: DataHydroponics? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class DataHydroponics(

    @field:SerializedName("token_alat")
    val tokenAlat: String? = null,

    @field:SerializedName("createdAt")
    val createdAt: String? = null,

    @field:SerializedName("pemilik")
    val pemilik: String? = null,

    @field:SerializedName("lokasi_hidroponik")
    val lokasiHidroponik: String? = null,

    @field:SerializedName("nama_hidroponik")
    val namaHidroponik: String? = null,

    @field:SerializedName("updatedAt")
    val updatedAt: String? = null
)

data class ResponseChangeUserRole(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("data")
    val data: DataResponseUserRole? = null,

    @field:SerializedName("status")
    val status: String? = null
)

data class DataResponseUserRole(

    @field:SerializedName("role")
    val role: Int? = null,

    @field:SerializedName("email")
    val email: String? = null
)

interface ApiService {

    @POST("auth/register")
    fun register(
        @Body requestBody: RequestBody
    ): Call<RegisterResponse>

    @POST("auth/login")
    fun login(
        @Body requestBody: RequestBody
    ): Call<LoginResponse>

    @POST("hidroponik")
    fun insertHydroponics(
        @Header("Authorization") Authorization: String,
        @Body requestBody: RequestBody
    ): Call<ResponseHydroponicsInsert>

    @PUT("user/{id}")
    fun changeUserRole(
        @Header("Authorization") Authorization: String,
        @Path("id") id:String,
        @Body requestBody: RequestBody
    ): Call<ResponseChangeUserRole>

    @GET("user/{id}")
    fun setUserData(
        @Header("Authorization") Authorization: String,
        @Path("id") id:String
    ): Call<ProfileResponse>

    @PUT("user/{id}")
    fun updateData(
        @Header("Authorization") Authorization: String,
        @Path("id") id:String,
        @Body requestBody: RequestBody
    ): Call<ProfileResponse>



}