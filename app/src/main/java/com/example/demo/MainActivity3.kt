package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demo.databinding.ActivityMain3Binding
import com.example.demo.utils.goToActivity

import com.example.demo.utils.showToastLong
import com.google.firebase.auth.FirebaseAuth

class MainActivity3 : AppCompatActivity() {
    lateinit var auth:FirebaseAuth
    lateinit var binding: ActivityMain3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        binding = ActivityMain3Binding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        setListeners()
    }
    private fun setListeners() = binding.apply {
        if (auth.currentUser!=null){
            mail.text = auth.currentUser?.email
        }else{
            goToActivity(MainActivity2::class.java)
        }
        listview1.setOnClickListener {
            goToActivity(MainActivity6::class.java)
        }
        logout.setOnClickListener {
            auth.signOut()
            goToActivity(MainActivity2::class.java)
        }

    }



}