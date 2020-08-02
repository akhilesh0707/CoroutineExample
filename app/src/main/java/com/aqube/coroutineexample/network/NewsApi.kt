package com.aqube.coroutineexample.network

import com.aqube.coroutineexample.model.NewsArticle
import retrofit2.http.GET

interface NewsApi {

    @GET("news.json")
    suspend fun getNewsList(): List<NewsArticle>
}