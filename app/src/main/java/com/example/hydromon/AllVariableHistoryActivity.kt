package com.example.hydromon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hydromon.databinding.ActivityAllVariableHistoryBinding

class AllVariableHistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityAllVariableHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllVariableHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}