package com.example.demo.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.demo.MainActivity2
import com.example.demo.MainActivity3
import com.example.demo.databinding.FragmentSignupBinding
import com.example.demo.factory.MyViewModelFactory
import com.example.demo.repository.authrepositoryimpl
import com.example.demo.utils.Resource
import com.example.demo.utils.goToActivity
import com.example.demo.utils.goToActivity3
import com.example.demo.utils.showToastLong
import com.example.demo.viewModel.LoginViewModel
import com.example.demo.viewModel.SignupViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class signup_fragment: Fragment() {
    private lateinit var binding:FragmentSignupBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var signupViewModel: SignupViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser !=null){
            requireActivity().goToActivity(MainActivity3::class.java)
            requireActivity().finish()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authrepository = authrepositoryimpl(auth)
        val viewModelFactory = MyViewModelFactory(authrepository)
        signupViewModel = ViewModelProvider(requireActivity(),viewModelFactory)[SignupViewModel::class.java]
        setListeners()
        setObserver()
    }
    private fun setListeners() = binding.apply {
        pleaselogin.setOnClickListener {
            (requireActivity() as MainActivity2).replaceFragment(login_fragment())
        }
        signup.setOnClickListener {
            validate()

        }
    }
    private fun setObserver () = binding.apply {
        CoroutineScope(Dispatchers.Main).launch {
            signupViewModel.signupLiveData.collect{result ->
                when(result){
                    is Resource.Loading ->binding.signupBar.isVisible = true
                    is Resource.Success ->{
                        binding.signupBar.isVisible = false
                        upusername.text.clear()
                        uppassword.text.clear()
                        confirmpassword.text.clear()
                        requireActivity().showToastLong("Registered Successfully")
                        requireActivity().goToActivity3(MainActivity3::class.java)
                    }
                    is Resource.Error -> {
                        binding.signupBar.isVisible = false
                        requireActivity().showToastLong(result.message.toString())
                    }
                }
            }
        }
    }

    private fun validate() = binding.apply {
        if (upusername.text.toString().isEmpty()) {
            upusername.error = "Username is Empty"}
        if(!Patterns.EMAIL_ADDRESS.matcher(upusername.text.toString()).matches()){
            upusername.error = "Invalid Email"
        }
        if (uppassword.text.toString().isEmpty()) {
            uppassword.error = "Password is Empty"}
         if (uppassword.text.toString().length != 8) {
            uppassword.error = "Password must be 8 Character"}
         if (confirmpassword.text.toString().isEmpty()) {
            confirmpassword.error = "Password is Empty"}
         if (confirmpassword.text.toString().length != 8) {
            confirmpassword.error = "Password must be 8 Character"}
        if (uppassword.text.toString() != confirmpassword.text.toString()) {
            confirmpassword.error = "password do no match"
            uppassword.error = "password do no match"
        }
        val email = upusername.text.toString()
        val password = uppassword.text.toString()
        val cnfrmpassword = confirmpassword.text.toString()
        signupViewModel.registerUser(email,password)


//            auth.createUserWithEmailAndPassword(email,password)
//                .addOnCompleteListener{task ->
//                    if (task.isSuccessful){
//                       val intent = Intent(requireActivity(), MainActivity3::class.java)
//                        intent.putExtra("Username",email)
//                        intent.putExtra("Password",password)
//                        intent.putExtra("Confirmpassword",cnfrmpassword)
//                        startActivity(intent)
//                        requireActivity().showToastLong("Signup Successfully")
//                    }else{
//                        requireActivity().showToastLong("Signup Failed")
//                    }
//
//                }


        }
    }