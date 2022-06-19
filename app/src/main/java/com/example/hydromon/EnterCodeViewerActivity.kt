package com.example.hydromon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hydromon.databinding.ActivityEnterCodeViewerBinding

class EnterCodeViewerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityEnterCodeViewerBinding
    companion object{
        var idUser="idUser"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnterCodeViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}