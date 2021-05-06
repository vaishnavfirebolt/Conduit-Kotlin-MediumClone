package com.vaishnav.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vaishnav.api.models.entities.LoginData

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "user")
    val loginData: LoginData
)