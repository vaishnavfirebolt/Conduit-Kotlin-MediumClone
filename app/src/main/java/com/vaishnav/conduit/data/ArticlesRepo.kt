package com.vaishnav.conduit.data

import com.vaishnav.api.ConduitClient

object ArticlesRepo {

    private val api = ConduitClient.publicAPI
    private val authApi = ConduitClient.authAPI

    suspend fun getGlobalFeed() = api.getArticles().body()?.articles
    suspend fun getMyFeed() = authApi.getFeedArticles().body()?.articles
}