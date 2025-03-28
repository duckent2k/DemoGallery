package com.example.demogallery.domain.model

data class Photo(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    val photographer: String,
    val photographerUrl: String,
    val avgColor: String,
    val imageUrl: String
)
