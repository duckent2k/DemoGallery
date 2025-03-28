package com.example.demogallery.data.repository

import com.example.demogallery.data.remote.PexelsApiService
import com.example.demogallery.domain.model.Photo
import com.example.demogallery.domain.repository.PhotoRepository

class PhotoRepositoryImpl(private val apiService: PexelsApiService) : PhotoRepository {
    override suspend fun getPhotos(page: Int, perPage: Int): List<Photo> {
        return apiService.getPhotos(page, perPage).photos.map { it.toDomain() }
    }

}