package com.example.demo.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.example.demo.MainActivity3
import com.example.demo.databinding.CodeFragmentBinding
import com.example.demo.utils.goToActivity
import com.example.demo.utils.showSoftKeyBoard
import com.example.demo.utils.showToastLong
import com.example.demo.utils.showToastShort
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class code_fragment:Fragment() {
    private lateinit var binding:CodeFragmentBinding
    private lateinit var auth:FirebaseAuth
    var otp = ""
    var resendToken:PhoneAuthProvider.ForceResendingToken?=null
    var phoneNumber = ""
    var otpFromUser = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            otp = it.getString("otp")?:""
            resendToken = it.getParcelable("resendToken")
            phoneNumber = it.getString("Mobileno")?:""
        }

        setListeners()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CodeFragmentBinding.inflate(inflater,container,false)
        return binding.root


    }
    private fun setListeners() = binding.apply {
        psuedoEditText.requestFocus()
        psuedoEditText.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus){
                requireActivity().showSoftKeyBoard(v)
            }
        }
        psuedoEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                otpFromUser = s.toString()

                when(s?.length){
                    0 -> {
                        binding.otp1.text = ""
                        binding.otp2.text = ""
                        binding.otp3.text = ""
                        binding.otp4.text = ""
                        binding.otp5.text = ""
                        binding.otp6.text = ""
                    }
                    1 -> {
                        binding.otp1.text = "${s.toString().toCharArray()[0]}"
                        binding.otp2.text = ""
                        binding.otp3.text = ""
                        binding.otp4.text = ""
                        binding.otp5.text = ""
                        binding.otp6.text = ""
                    }
                    2 -> {
                        binding.otp2.text = "${s.toString().toCharArray()[1]}"
                        binding.otp3.text = ""
                        binding.otp4.text = ""
                        binding.otp5.text = ""
                        binding.otp6.text = ""
                    }
                    3 -> {
                        binding.otp3.text = "${s.toString().toCharArray()[2]}"
                        binding.otp4.text = ""
                        binding.otp5.text = ""
                        binding.otp6.text = ""
                    }
                    4 -> {
                        binding.otp4.text = "${s.toString().toCharArray()[3]}"
                        binding.otp5.text = ""
                        binding.otp6.text = ""
                    }
                    5 -> {
                        binding.otp5.text = "${s.toString().toCharArray()[4]}"
                        binding.otp6.text = ""
                    }
                    6 -> {
                        binding.otp6.text = "${s.toString().toCharArray()[5]}"
                    }
                }

            }

        })
        verifyotp.setOnClickListener {
        validate()
    }}
    private fun validate(){
        if (otpFromUser.isEmpty()){
            requireActivity().showToastShort("Please enter OTP")
        }else if (otpFromUser.length!=6){
            requireActivity().showToastShort("Please enter valid OTP")
        }else{
            val credential = PhoneAuthProvider.getCredential(otp, otpFromUser)
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user

                    requireActivity().goToActivity(MainActivity3::class.java)
                } else {
                    requireActivity().showToastLong("Something Went Wrong")
                }
            }
    }
}