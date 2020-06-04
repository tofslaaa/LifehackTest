package com.lifehackstudio.lifehacktest.web

data class Cards(
    val id: Long,
    val name: String,
    val image: String
)

data class Card(
    val id: Long,
    val name: String,
    val image: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val site: String,
    val phone: String
)