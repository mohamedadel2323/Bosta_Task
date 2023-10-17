package com.example.bostastask.profile.di

import com.example.bostastask.profile.data.remote.albums.AlbumsApiClient
import com.example.bostastask.profile.data.remote.albums.AlbumsRemoteSource
import com.example.bostastask.profile.data.remote.users.UsersApiClient
import com.example.bostastask.profile.data.remote.users.UsersRemoteSource
import com.example.bostastask.profile.data.repository.ProfileRepositoryImp
import com.example.bostastask.profile.domain.repository.IProfileRepository
import com.example.bostastask.profile.domain.usecases.ProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ProfileViewModelModule {
    @Binds
    @ViewModelScoped
    abstract fun bindUsersRemoteSource(usersApiClient: UsersApiClient): UsersRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindAlbumsRemoteSource(albumsApiClient: AlbumsApiClient): AlbumsRemoteSource

    @Binds
    @ViewModelScoped
    abstract fun bindProfileRepo(profileRepositoryImp: ProfileRepositoryImp): IProfileRepository

    companion object {
        @Provides
        @ViewModelScoped
        fun provideProfileUseCase(repository: IProfileRepository): ProfileUseCase =
            ProfileUseCase(repository)
    }


}