package com.example.demo.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.demo.MainActivity2
import com.example.demo.MainActivity3
import com.example.demo.databinding.CodeFragmentBinding
import com.example.demo.databinding.OtpFragmentBinding
import com.example.demo.utils.goToActivity
import com.example.demo.utils.isValidIndianPhoneNumber
import com.example.demo.utils.showToastLong
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class otp_fragment : Fragment() {
    private lateinit var binding: OtpFragmentBinding
    private lateinit var auth: FirebaseAuth
    override fun onStart() {
        super.onStart()
        val currentuser = auth.currentUser
        if (currentuser != null) {
            requireActivity().goToActivity(MainActivity3::class.java)
            requireActivity().finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = OtpFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() = binding.apply {
        sendotp.setOnClickListener {
            validate()
        }
    }

    private fun validate() = binding.apply {
        if (mobileno.text.toString().isEmpty()) {
            mobileno.error = "Mobile no is Empty"
        } else if (mobileno.text.toString().length != 10) {
            mobileno.error = "Must be 10 digit Mobile no"
        } else if (!isValidIndianPhoneNumber(mobileno.text.toString())) {
            mobileno.error = "Enter valid Phone Number"
        }else{
            val options = PhoneAuthOptions.newBuilder(auth)
                .setActivity(requireActivity())
                .setTimeout(60L,TimeUnit.SECONDS)
                .setPhoneNumber("+91${mobileno.text}")
                .setCallbacks(callbacks)
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }

    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            requireActivity().showToastLong(e.message.toString())
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            val bundle = Bundle()
            bundle.putString("otp", verificationId)
            bundle.putString("Mobileno", binding.mobileno.text.toString())
            bundle.putParcelable("resendToken", token)

            val codeFragment = code_fragment()
            codeFragment.arguments = bundle
            requireActivity().showToastLong("Otp is : $verificationId")


            (requireActivity() as MainActivity2).replaceFragment(codeFragment)

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