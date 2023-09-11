package com.example.project.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.project.fragments.HotelFragment
import com.example.project.R
import com.example.project.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, HotelFragment())
            .commit()
    }
}