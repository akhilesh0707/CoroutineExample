package com.aqube.coroutineexample.network

import com.aqube.coroutineexample.model.NewsArticle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {
    companion object {
        private const val BASE_URL = "https://raw.githubusercontent.com/DevTides/NewsApi/master/"
        private const val NEWS_DELAY = 2000L
    }

    private val newsService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    fun getNewsArticles(): Flow<NewsArticle> {
        return flow {
            val newsSource = newsService.getNewsList()
            newsSource.forEach {
                emit(it)
                delay(NEWS_DELAY)
            }
        }
    }
}