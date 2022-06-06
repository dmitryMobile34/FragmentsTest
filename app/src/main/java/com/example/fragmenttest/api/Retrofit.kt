package com.example.fragmenttest

import com.example.fragmenttest.datamodel.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServices {
    @GET("v2/top-headlines?country=us&category=sports&q=nba&apiKey=51269123bb0b4b82bbfb73559fe2357f")
    fun getNews() : Call<News>
}