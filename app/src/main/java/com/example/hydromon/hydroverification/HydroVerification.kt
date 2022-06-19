package com.example.hydromon.hydroverification

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hydromon.databinding.ActivityHydroVerificationBinding

class HydroVerification : AppCompatActivity() {

    private lateinit var binding: ActivityHydroVerificationBinding
//    private lateinit var hydroVerificationViewModel: HydroVerificationViewModel
    companion object{
        const val signUpHydroponicAttributeVerif = "attribute"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHydroVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Log.d("asdoa","${intent.getParcelableExtra<SignUpHydroponicsAttribute>(signUpHydroponicAttributeVerif) as SignUpHydroponicsAttribute}")
//
////        val data = intent.getParcelableExtra<SignUpHydroponicsAttribute>(signUpHydroponicAttribute) as SignUpHydroponicsAttribute
//
//        hydroVerificationViewModel =
//            ViewModelProvider(this@HydroVerification).get(HydroVerificationViewModel::class.java)
//
//
//        val locationTvReceived : TextView = binding.locationTvReceived
////        locationTvReceived.text = data.locationMapText
//
//        val hydroIdReceived : TextView = binding.hydroIdReceived
////        hydroIdReceived.text = data.kodeHydroponicText
//
//        val hydroNameReceived : TextView = binding.hydroNameReceived
////        hydroNameReceived.text = data.namaHydroponicText
//
//        val roleReceived : TextView = binding.roleReceived
//        roleReceived.text = "Owner"
//
////        input data hidroponik
////        hydroVerificationViewModel.insertHydroponicsData(
////            hydroNameReceived.text.toString(),
////            locationTvReceived.text.toString(),
////            data.idUser
////        )
//
////        update role user
    }
}