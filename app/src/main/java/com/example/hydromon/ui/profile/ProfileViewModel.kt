package com.example.hydromon.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hydromon.api.ApiConfig
import com.example.hydromon.api.DataUser
import com.example.hydromon.api.ProfileResponse
import com.example.hydromon.api.ResponseChangeUserRole
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {

    private val userData = MutableLiveData<ProfileResponse>()

    fun setUserProfile(token: String, id: String){
        Log.d("isi", "${id} dan {$token}")
        val client = ApiConfig.getApiService().setUserData("Bearer ${token}", id)
        client.enqueue(object : Callback<ProfileResponse>{
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                Log.d("isi", "${response}")
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("response profile berhasil", "${responseBody}")
                        userData.postValue(responseBody)
                    }
                }else{
                    Log.e("profilenya", "${response}")
                    val responseBody = response.code().toString()

//                    userData.postValue(ProfileResponse(responseBody,"fail", id))
//                    var ProfileResponseLocal : ProfileResponse
                    Log.d("response profile gagal", "${userData}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e("api gagal", "${t.message}")
            }
        })

//        client.enqueue(object : Callback<ResponseChangeUserRole> {
//            override fun onResponse(
//                call: Call<ResponseChangeUserRole>,
//                response: Response<ResponseChangeUserRole>
//            ) {
//                Log.d("update user", "${response}")
//                if (response.isSuccessful) {
//                    val responseBody = response.body()
//                    Log.d("respon change user", "${responseBody}")
//                    if (responseBody != null) {
//                        changeUserRoleResponse.postValue(responseBody)
//                    }else{
//
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseChangeUserRole>, t: Throwable) {
//
//            }
//
//        })


    }

    fun getProfile(): LiveData<ProfileResponse>{
        return userData
    }

    fun updateProfile(token: String, id: String, nama:String, username:String, email:String){
        val jsonObject = JSONObject()
        jsonObject.put("nama_lengkap", nama)
        jsonObject.put("username", username)
        jsonObject.put("email", email)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("update test", "${requestBody}")

        val client = ApiConfig.getApiService().updateData("Bearer ${token}", id, requestBody)
        Log.d("request ","${client}")
        client.enqueue(object :Callback<ProfileResponse>{
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>
            ) {
                Log.d("update user", "${response}")
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("respon change user", "${responseBody}")
                    if (responseBody != null) {
                        userData.postValue(responseBody)
                    }else{

                    }
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {

            }

        })  
    }

}