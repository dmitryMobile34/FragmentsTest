package com.example.fragmenttest.datamodel

data class News (
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)