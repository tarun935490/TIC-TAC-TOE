package com.example.demo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.demo.repository.authrepository
import com.example.demo.viewModel.LoginViewModel
import com.example.demo.viewModel.SignupViewModel

class MyViewModelFactory(private val repository: authrepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            return SignupViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Unknown View Model Class")
        }
    }
}