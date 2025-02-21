package com.example.demo.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.repository.authrepository
import com.example.demo.utils.Resource
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val repository:authrepository) : ViewModel(){
    private  val loginMutableLiveData: Channel<Resource<AuthResult>> = Channel()
    val loginLiveData: Flow<Resource<AuthResult>>
        get() = loginMutableLiveData.consumeAsFlow()
    fun loginUser(username:String,password:String){
        viewModelScope.launch {
            repository.loginUser(username,password).collect { result ->
                loginMutableLiveData.send(result)
            }
        }
    }
}


