package com.example.demo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.demo.MainActivity2
import com.example.demo.MainActivity3
import com.example.demo.databinding.FragmentChooseBinding

class chooseFragment:Fragment(){
    lateinit var binding: FragmentChooseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }
    private fun setListeners() = binding.apply{
        emailsign.setOnClickListener{
            (activity as MainActivity2).replaceFragment(login_fragment())
        }
        phonesign.setOnClickListener {
            (activity as MainActivity2).replaceFragment(otp_fragment())
        }
    }
}