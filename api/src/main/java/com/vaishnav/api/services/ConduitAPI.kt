package com.vaishnav.api.services

import com.vaishnav.api.models.requests.LoginRequest
import com.vaishnav.api.models.requests.SignUpRequest
import com.vaishnav.api.models.responses.ArticleResponse
import com.vaishnav.api.models.responses.ArticlesResponse
import com.vaishnav.api.models.responses.TagsResponse
import com.vaishnav.api.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ConduitAPI {


    @GET("articles")
    suspend fun getArticles(
            @Query("author") author : String? = null,
            @Query("tag") tag : String? = null,
            @Query("favorited") favorited : String? = null
    ) : Response<ArticlesResponse>

    @POST("users")
    suspend fun signupUser(
            @Body userCreds: SignUpRequest
    ) : Response<UserResponse>

    @POST("users/login")
    suspend fun loginUser(
            @Body userCreds: LoginRequest
    ) : Response<UserResponse>

    @GET("articles/{slug}")
    suspend fun getArticleBySlug(
            @Path("slug") slug: String
    ): Response<ArticleResponse>

    @GET("tags")
    suspend fun getTags(): Response<TagsResponse>


}