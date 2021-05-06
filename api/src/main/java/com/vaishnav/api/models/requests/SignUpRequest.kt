package com.vaishnav.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vaishnav.api.models.entities.SignUpData

@JsonClass(generateAdapter = true)
data class SignUpRequest(
    @Json(name = "user")
    val user: SignUpData
)