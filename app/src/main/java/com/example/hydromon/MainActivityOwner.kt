package com.example.hydromon

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.hydromon.databinding.ActivityMainOwnerBinding

class MainActivityOwner : AppCompatActivity() {

    private lateinit var binding: ActivityMainOwnerBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainOwnerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navViewOwner
        navView.itemBackgroundResource = R.color.nav_color
        navView.itemActiveIndicatorColor = getColorStateList(R.color.black)
        navView.itemTextColor = getColorStateList(R.color.black)

        val navController = findNavController(R.id.nav_host_fragment_activity_main_owner)

        navView.setupWithNavController(navController)
    }
}