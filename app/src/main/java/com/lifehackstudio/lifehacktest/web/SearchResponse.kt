package com.lifehackstudio.lifehacktest.web

data class Cards(
    val id: Long,
    val name: String,
    val img: String
)

data class Card(
    val id: Long,
    val name: String,
    val img: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val site: String,
    val phone: String
)