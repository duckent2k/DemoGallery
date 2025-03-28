package com.example.demogallery.domain.repository

import com.example.demogallery.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(page: Int, perPage: Int): List<Photo>
}