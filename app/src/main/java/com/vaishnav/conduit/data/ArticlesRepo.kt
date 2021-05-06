package com.vaishnav.conduit.data

import com.vaishnav.api.ConduitClient

object ArticlesRepo {

    val api = ConduitClient().api

    suspend fun getGlobalFeed() = api.getArticles()
}