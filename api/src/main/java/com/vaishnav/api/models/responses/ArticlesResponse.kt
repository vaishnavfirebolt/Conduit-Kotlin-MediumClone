package com.vaishnav.api.models.responses


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vaishnav.api.models.entities.Article

@JsonClass(generateAdapter = true)
data class ArticlesResponse(
        @Json(name = "articles")
    val articles: List<Article>,
        @Json(name = "articlesCount")
    val articlesCount: Int
)