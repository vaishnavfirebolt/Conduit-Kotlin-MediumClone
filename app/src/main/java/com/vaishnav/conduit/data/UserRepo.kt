package com.vaishnav.conduit.data

import com.vaishnav.api.ConduitClient
import com.vaishnav.api.models.entities.LoginData
import com.vaishnav.api.models.entities.SignUpData
import com.vaishnav.api.models.entities.User
import com.vaishnav.api.models.entities.UserUpdateData
import com.vaishnav.api.models.requests.LoginRequest
import com.vaishnav.api.models.requests.SignUpRequest
import com.vaishnav.api.models.requests.UserUpdateRequest

object UserRepo {

    private val api = ConduitClient.publicAPI
    private val authAPI = ConduitClient.authAPI

    suspend fun loginUser(email : String , password : String): User? {
        val response = api.loginUser(LoginRequest(LoginData(email , password)))
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }
    suspend fun signUpUser(email : String , password : String , username : String): User? {
        val response = api.signupUser(SignUpRequest(SignUpData(email , password , username)))
        ConduitClient.authToken = response.body()?.user?.token
        return response.body()?.user
    }

    suspend fun getUserProfile() = authAPI.getCurrentUser().body()?.user

    suspend fun updateUser(
        bio : String?,
        username : String?,
        image : String?,
        email : String?,
        password : String?
    ): User? {
        val response = authAPI.updateCurrentUser(UserUpdateRequest(UserUpdateData(bio, email, image, username, password)))
        return response.body()?.user
    }

    suspend fun getCurrentUser(token: String): User? {
        ConduitClient.authToken = token
        return authAPI.getCurrentUser().body()?.user
    }
}