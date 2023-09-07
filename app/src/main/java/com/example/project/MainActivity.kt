package com.example.project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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


//        val items: List<Any> = listOf(
//            TextItem("Some text"),
//            ButtonItem("Click me"),
//            ImageItem("1234"),
//            TextItem("al;kdfa")
//        )
//
//        binding.rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        val adapter = MyListAdapter()
//        adapter.items = items
//        binding.rv.adapter = adapter
    }
}