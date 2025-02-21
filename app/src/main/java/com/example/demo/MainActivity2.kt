package com.example.demo

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.demo.databinding.ActivityMain2Binding
import com.example.demo.fragment.chooseFragment
import com.example.demo.fragment.login_fragment

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment()
//        replaceFragment(login_fragment(), false)
    }

    fun addFragment() {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.add(R.id.fragmentContainer, chooseFragment())
        transaction.commit()
    }

    fun replaceFragment(fragment: Fragment, isAddToBackStack: Boolean = true) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        if (isAddToBackStack) {
            transaction.addToBackStack(fragment.javaClass.simpleName)
        }
        transaction.commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        if (fragment != null) {
            if (fragment is chooseFragment) {
                finish()
            } else {
                super.onBackPressed()

            }
        }

    }


}



