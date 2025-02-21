package com.example.demo

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demo.Adapter.NormalviewAdapter
import com.example.demo.databinding.ActivityMain5Binding
import com.example.demo.databinding.ActivityMain6Binding
import com.example.demo.utils.showToastLong

class MainActivity6 : AppCompatActivity() {
    lateinit var binding: ActivityMain6Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMain6Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val imagelist = ArrayList<Int>()
        imagelist.add(R.drawable.app)
        imagelist.add(R.drawable.login)
        imagelist.add(R.drawable.icmail_24)
        imagelist.add(R.drawable.baseline_phonelink_lock_24)
        binding.viewPager.adapter = NormalviewAdapter(this, imagelist)
        SETUI()
    }

    private fun SETUI() = binding.apply {
        tablayout.tabTextColors = ColorStateList.valueOf(ResourcesCompat.getColor(resources,R.color.black,theme))
        val hometab = tablayout.newTab().setText(ContextCompat.getString(this@MainActivity6,R.string.home))
        val feedtab = tablayout.newTab().setText(ContextCompat.getString(this@MainActivity6,R.string.feed))
        val chattab = tablayout.newTab().setText(ContextCompat.getString(this@MainActivity6,R.string.chat))
        tablayout.addTab(hometab)
        tablayout.addTab(feedtab)
        tablayout.addTab(chattab)
        
    }
}