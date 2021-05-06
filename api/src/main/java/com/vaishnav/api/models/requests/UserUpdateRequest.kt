package com.vaishnav.api.models.requests


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vaishnav.api.models.entities.UserUpdateData

@JsonClass(generateAdapter = true)
data class UserUpdateRequest(
    @Json(name = "user")
    val user: UserUpdateData
)