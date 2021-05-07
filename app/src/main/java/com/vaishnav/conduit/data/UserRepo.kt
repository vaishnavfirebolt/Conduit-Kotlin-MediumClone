package com.vaishnav.conduit.data

import com.vaishnav.api.ConduitClient
import com.vaishnav.api.models.entities.LoginData
import com.vaishnav.api.models.requests.LoginRequest
import com.vaishnav.api.models.responses.UserResponse

object UserRepo {

    private val api = ConduitClient().api

    suspend fun loginUser(email : String , password : String): UserResponse? {
        val response = api.loginUser(LoginRequest(LoginData(email , password)))
        return response.body()
    }

}