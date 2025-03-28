package com.example.demogallery.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.demogallery.data.remote.PexelsApiService
import com.example.demogallery.presentation.viewmodel.PhotoPagingSource
import javax.inject.Inject

class PhotoPagerRepository @Inject constructor(private val apiService: PexelsApiService) {
    fun getPhotos() = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { PhotoPagingSource(apiService) }
    ).flow
}