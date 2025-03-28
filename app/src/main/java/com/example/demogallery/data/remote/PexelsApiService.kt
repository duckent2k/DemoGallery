package com.example.demogallery.data.remote

import com.example.demogallery.data.model.PhotoDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface PexelsApiService {
    @GET("v1/curated")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PexelsResponse
}

data class PexelsResponse(val photos: List<PhotoDTO>)