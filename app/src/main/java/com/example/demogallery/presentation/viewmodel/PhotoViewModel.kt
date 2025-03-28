package com.example.demogallery.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.demogallery.domain.model.Photo
import com.example.demogallery.domain.repository.PhotoPagerRepository
import com.example.demogallery.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase,
    private val photoRepository: PhotoPagerRepository,
) :
    ViewModel() {

    val photos : Flow<PagingData<Photo>> = photoRepository.getPhotos().cachedIn(viewModelScope)
}