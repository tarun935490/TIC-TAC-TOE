package com.example.demo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.repository.authrepository
import com.example.demo.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class SignupViewModel (private val repository:authrepository) : ViewModel(){
    private val signupMutableLiveData:Channel<Resource<AuthResult>> = Channel()
    val signupLiveData : Flow<Resource<AuthResult>>
        get() = signupMutableLiveData.consumeAsFlow()
    fun registerUser(username:String,password:String){
        viewModelScope.launch {
            repository.loginUser(username,password).collect{result ->
                signupMutableLiveData.send(result)
            }
        }
    }
}