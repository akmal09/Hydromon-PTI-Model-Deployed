package com.example.hydromon.ui.authentication.register

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hydromon.api.ApiConfig
import com.example.hydromon.api.RegisterResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterActivityViewModel : ViewModel() {
    private val registerResponse = MutableLiveData<RegisterResponse>()

    fun registerRequest(username:String, email:String, password:String) {
        val jsonObject = JSONObject()
        jsonObject.put("username", username)
        jsonObject.put("email", email)
        jsonObject.put("password", password)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())


        val registering = ApiConfig.getApiService().register(requestBody)
        registering.enqueue(object : Callback<RegisterResponse>{
            @SuppressLint("NullSafeMutableLiveData")
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("response register berhasil", "${responseBody}")
                        registerResponse.postValue(responseBody)
                    }
                }else{
                    Log.d("response register gagal", "${response}")
                    registerResponse.postValue(RegisterResponse(response.code(),"Email is already used. Use another email."))
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("api register gagal", "${t.message}")
            }

        })
    }

    fun getRegisterResponse(): LiveData<RegisterResponse> {
        return registerResponse
    }
}