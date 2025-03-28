package com.example.demogallery.domain.usecase

import com.example.demogallery.domain.repository.PhotoRepository

class GetPhotosUseCase(private val repository: PhotoRepository) {
    suspend operator fun invoke(page: Int, perPage: Int) = repository.getPhotos(page, perPage)
}