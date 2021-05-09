package com.vaishnav.api

import com.vaishnav.api.services.ConduitAPI
import com.vaishnav.api.services.ConduitAuthApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ConduitClient {

    var authToken : String? = null

    private val authInterceptor = Interceptor { chain ->
        var req = chain.request()
        authToken?.let {
            req = req.newBuilder()
                .header("Authorization" , "Token $it")
                .build()
        }
        chain.proceed(req)
    }

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl("https://conduit.productionready.io/api/")
        .addConverterFactory(MoshiConverterFactory.create())

    val publicAPI: ConduitAPI = retrofitBuilder
        .client(okHttpBuilder.build())
        .build()
        .create(ConduitAPI::class.java)

    val authAPI: ConduitAuthApi = retrofitBuilder
        .client(okHttpBuilder.addInterceptor(authInterceptor).build())
        .build()
        .create(ConduitAuthApi::class.java)
}