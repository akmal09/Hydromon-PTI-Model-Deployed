package com.example.hydromon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.hydromon.databinding.ActivityHydroponicsHistoryBinding

class HydroponicsHistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHydroponicsHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHydroponicsHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backButton : ImageView = binding.backButton

        backButton.setOnClickListener{
            finish()
        }
    }
}