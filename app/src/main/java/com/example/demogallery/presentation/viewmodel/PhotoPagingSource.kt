package com.example.demogallery.presentation.viewmodel

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.demogallery.data.remote.PexelsApiService
import com.example.demogallery.domain.model.Photo

class PhotoPagingSource(private val apiService: PexelsApiService) : PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPage = params.key ?: 1
            val response = apiService.getPhotos(page = currentPage, perPage = params.loadSize)
            val photos = response.photos.map { it.toDomain() }

            LoadResult.Page(
                data = photos,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (photos.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}