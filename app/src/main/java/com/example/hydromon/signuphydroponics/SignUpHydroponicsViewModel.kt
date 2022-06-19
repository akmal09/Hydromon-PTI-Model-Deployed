package com.example.hydromon.signuphydroponics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hydromon.api.ApiConfig
import com.example.hydromon.api.ResponseChangeUserRole
import com.example.hydromon.api.ResponseHydroponicsInsert
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpHydroponicsViewModel : ViewModel() {

    private val insertHydroponicsResponse = MutableLiveData<ResponseHydroponicsInsert>()
    private val changeUserRoleResponse = MutableLiveData<ResponseChangeUserRole>()

    fun insertHydroponicsData(namaHidroponik:String, lokasi:String, idUser:String, tokenAlat:String, tokenUser:String) {
        val jsonObject = JSONObject()
        jsonObject.put("nama_hidroponik", namaHidroponik)
        jsonObject.put("lokasi_hidroponik", lokasi)
        jsonObject.put("pemilik", idUser)
        jsonObject.put("token_alat", tokenAlat)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("request json", "${requestBody}")
        var insertRequest = ApiConfig.getApiService().insertHydroponics("Bearer ${tokenUser}",requestBody)
        insertRequest.enqueue(object : Callback<ResponseHydroponicsInsert>{
            override fun onResponse(
                call: Call<ResponseHydroponicsInsert>,
                response: Response<ResponseHydroponicsInsert>
            ) {
                Log.d("insert hydro", "${response}")
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d("respon insert hidroponik", "${responseBody}")
                        insertHydroponicsResponse.postValue(responseBody)
                    }else{
                        insertHydroponicsResponse.postValue(responseBody)
                    }
                }else{

                }
            }

            override fun onFailure(call: Call<ResponseHydroponicsInsert>, t: Throwable) {

            }

        })
    }

    fun changeUserRole(idUser: String, tokenUser: String) {
        val jsonObject = JSONObject()
        jsonObject.put("role", 1)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        Log.d("user role json", "${requestBody}")

        val changeRequest = ApiConfig.getApiService().changeUserRole("Bearer ${tokenUser}",idUser, requestBody)
        Log.d("request chagng","${changeRequest}")
        changeRequest.enqueue(object :Callback<ResponseChangeUserRole>{
            override fun onResponse(
                call: Call<ResponseChangeUserRole>,
                response: Response<ResponseChangeUserRole>
            ) {
                Log.d("update user", "${response}")
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("respon change user", "${responseBody}")
                    if (responseBody != null) {
                        changeUserRoleResponse.postValue(responseBody)
                    }else{

                    }
                }
            }

            override fun onFailure(call: Call<ResponseChangeUserRole>, t: Throwable) {

            }

        })
    }

    fun getInsertHydroponicsResponse(): LiveData<ResponseHydroponicsInsert> {
        return insertHydroponicsResponse
    }

    fun getChangeUserRoleResponse(): LiveData<ResponseChangeUserRole> {
        return changeUserRoleResponse
    }

}