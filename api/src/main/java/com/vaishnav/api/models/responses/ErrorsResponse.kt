package com.vaishnav.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vaishnav.api.models.entities.Errors

@JsonClass(generateAdapter = true)
data class ErrorsResponse(
    @Json(name = "errors")
    val errors: Errors
)