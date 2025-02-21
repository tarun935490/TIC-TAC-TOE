package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.Adapter.UserAdapter
import com.example.demo.databinding.ActivityMain5Binding
import com.google.android.play.integrity.internal.i

class MainActivity5 : AppCompatActivity() {
    lateinit var binding: ActivityMain5Binding

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain5Binding.inflate(layoutInflater)
        setContentView(binding.root)
        setui()
    }

    private fun setui() = binding.apply {
        val userlist = arrayListOf<userdata>()
        for(i in 1..100){
            userlist.add(userdata(R.drawable.app, "Recycler view $i"))
        }
        userlist.add(userdata(R.drawable.app,"Recycler view"))
        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity5)
        recyclerview.adapter = UserAdapter(this@MainActivity5, userlist)
    }
}
