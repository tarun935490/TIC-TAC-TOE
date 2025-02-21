package com.example.demo.fragment

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
import com.example.demo.MainActivity4
import com.example.demo.databinding.FragmentLoginBinding
import com.example.demo.factory.MyViewModelFactory
import com.example.demo.repository.authrepositoryimpl
import com.example.demo.utils.Resource
import com.example.demo.utils.goToActivity
import com.example.demo.utils.goToActivity3
import com.example.demo.utils.showToastLong
import com.example.demo.viewModel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class login_fragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val authrepository = authrepositoryimpl(auth)
        val viewModelFactory = MyViewModelFactory(authrepository)
        loginViewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)[LoginViewModel::class.java]
        setListeners()
        setObserver()
    }

    private fun setListeners() = binding.apply {
        login.setOnClickListener {
            validate()
        }
        signup.setOnClickListener {
            (requireActivity() as MainActivity2).replaceFragment(signup_fragment())
        }

    }

    private fun setObserver() = binding.apply {
        CoroutineScope(Dispatchers.Main).launch {
            loginViewModel.loginLiveData.collect{result ->
                when (result){
                    is Resource.Loading -> binding.progressBar.isVisible = true
                    is Resource.Success ->{
                        binding.progressBar.isVisible = false
                        Username.text.clear()
                        Password.text.clear()
                        requireActivity().showToastLong("Login Successfully")
                        requireActivity().goToActivity3(MainActivity3::class.java)
                    }
                    is Resource.Error -> {
                        binding.progressBar.isVisible = false
                        requireActivity().showToastLong(result.message.toString())
                    }
                }
            }
        }
    }

    private fun validate() = binding.apply {
        if (Username.text.toString().isEmpty()) {
            Username.error = "Username is Empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Username.text.toString()).matches()) {
            Username.error = "Invalid Email"
        }
        if (Password.text.toString().isEmpty()) {
            Password.error = "Password is Empty"
        }
        if (Password.text.toString().length != 8) {
            Password.error = "Password Must be 8 Character"
        }
        val username = Username.text.toString()
        val password = Password.text.toString()
        loginViewModel.loginUser(username, password)
//        CoroutineScope(Dispatchers.Main).launch {
//            val result = auth.signInWithEmailAndPassword(username, password)
//            progressBar.isVisible = true
//            result.await()
//            if (result.isSuccessful) {
//                binding.progressBar.isVisible = false
//                Username.text.clear()
//                Password.text.clear()
//                requireActivity().goToActivity(MainActivity3::class.java)
//                requireActivity().showToastLong("Login Successfully")
//            } else {
//                progressBar.isVisible = false
//                requireActivity().showToastLong("Invalid email Password")
//            }
       // }
    }

}

