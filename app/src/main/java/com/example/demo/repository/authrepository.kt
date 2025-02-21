package com.example.demo.repository

import com.example.demo.utils.Resource
import com.google.common.io.Resources
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
interface authrepository {
    fun loginUser(email:String,password:String): Flow<Resource<AuthResult>>
    fun registerUser(email:String,password:String): Flow<Resource<AuthResult>>
}