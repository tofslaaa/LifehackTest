package com.lifehackstudio.lifehacktest.web

data class Cards(
    val id: String,
    val name: String,
    val img: String
)

data class Card(
    val id: String,
    val name: String,
    val img: String,
    val description: String,
    val lat: Double,
    val lon: Double,
    val www: String,
    val phone: String
)