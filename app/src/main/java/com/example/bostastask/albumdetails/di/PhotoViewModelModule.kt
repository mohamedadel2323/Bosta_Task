package com.example.bostastask.albumdetails.di

import com.example.bostastask.albumdetails.data.remote.PhotoApiClient
import com.example.bostastask.albumdetails.data.remote.PhotosRemoteSource
import com.example.bostastask.albumdetails.data.repository.PhotoRepositoryImp
import com.example.bostastask.albumdetails.domain.repository.IPhotoRepository
import com.example.bostastask.albumdetails.domain.usecases.PhotosUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class PhotoViewModelModule {

    @Binds
    @ViewModelScoped
    abstract fun bindPhotosRemoteSource(photoApiClient: PhotoApiClient): PhotosRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindPhotoRepo(photoRepositoryImp: PhotoRepositoryImp): IPhotoRepository

    companion object{
        @Provides
        @ViewModelScoped
        fun providePhotosUseCase(repository: IPhotoRepository): PhotosUseCase =
            PhotosUseCase(repository)
    }

}