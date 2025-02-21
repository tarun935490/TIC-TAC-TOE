package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demo.databinding.ActivityMain4Binding
import com.example.demo.utils.showToastLong

class MainActivity4 : AppCompatActivity() {
    lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
        SETUI()
    }

    private fun SETUI() = binding.apply {
//        val userlist = arrayListOf<String>()
//        for (i in 1..100) {
//            userlist.add("User $i")
//        }
//        listview.adapter =
//            ArrayAdapter(this@MainActivity4, R.layout.item_list, R.id.textView6, userlist)

    }
}