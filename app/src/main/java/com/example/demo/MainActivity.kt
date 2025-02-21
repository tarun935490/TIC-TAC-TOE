package com.example.demo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demo.databinding.ActivityMainBinding
import com.example.demo.utils.goToActivity
import com.example.demo.utils.showToastLong
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
lateinit var imageView: ImageView
lateinit var textView: TextView
lateinit var auth:FirebaseAuth
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView2)
        textView = findViewById(R.id.textView2)
        Handler().postDelayed({
            val currentUser = auth.currentUser
            if(currentUser !=null){
                goToActivity(MainActivity3::class.java)
            }else
            goToActivity(MainActivity2::class.java)
        },4000)











        val animation1 = AnimationUtils.loadAnimation(this,R.anim.textanim)
        textView.startAnimation(animation1)


       }




}