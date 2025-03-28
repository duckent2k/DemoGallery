package com.example.demogallery.data.model

import com.example.demogallery.domain.model.Photo
import com.google.gson.annotations.SerializedName

data class PhotoDTO(
    val id: Int,
    val width: Int,
    val height: Int,
    val url: String,
    @SerializedName("photographer") val photographer: String,
    @SerializedName("photographer_url") val photographerUrl: String,
    @SerializedName("avg_color") val avgColor: String,
    val src: SrcDto
) {
    fun toDomain(): Photo {
        return Photo(
            id = id,
            width = width,
            height = height,
            url = url,
            photographer = photographer,
            photographerUrl = photographerUrl,
            avgColor = avgColor,
            imageUrl = src.large
        )
    }
}

data class SrcDto(
    val original: String,
    val large: String,
    val medium: String
)
