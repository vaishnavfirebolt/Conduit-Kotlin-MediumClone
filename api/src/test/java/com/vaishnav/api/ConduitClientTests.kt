package com.vaishnav.api

import com.vaishnav.api.models.entities.SignUpData
import com.vaishnav.api.models.requests.SignUpRequest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import kotlin.random.Random

class ConduitClientTests {

    private val conduitClient = ConduitClient

    @Test
    fun `GET articles`(){
        runBlocking{
            val articles = conduitClient.publicAPI.getArticles()
            assertNotNull(articles.body()?.articles)
        }
    }
    @Test
    fun `GET articles by author`(){
        runBlocking{
            val articles = conduitClient.publicAPI.getArticles(author = "redhemd")
            assertNotNull(articles.body()?.articles)
        }
    }
    @Test
    fun `GET articles by favrited`(){
        runBlocking{
            val articles = conduitClient.publicAPI.getArticles(favorited = "redhemd")
            assertNotNull(articles.body()?.articles)
        }
    }
    @Test
    fun `GET articles by tag`(){
        runBlocking{
            val articles = conduitClient.publicAPI.getArticles(tag = "dragons")
            assertNotNull(articles.body()?.articles)
        }
    }
    @Test
    fun  `POST users - signUp user`(){
        val userCreds = SignUpData(
                password = "${Random.nextInt(9999999,99999999)}",
                email = "test${Random.nextInt(999,9999)}@test.com",
                username = "anom_user${Random.nextInt(99 , 999)}"
        )
        runBlocking {
            val response = conduitClient.publicAPI.signupUser(SignUpRequest(userCreds))
            assertEquals(userCreds.username , response.body()?.user?.username)
        }
    }
}