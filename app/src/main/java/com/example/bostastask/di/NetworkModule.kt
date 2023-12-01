package com.example.bostastask.di

import android.content.Context
import com.example.bostastask.BuildConfig.PLACE_HOLDER_BASE_URL
import com.example.bostastask.data.remote.ApiServiceManager
import com.example.bostastask.data.remote.PlaceholderApiService
import com.example.bostastask.utils.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Provides
    @Singleton
    fun provideOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder().addInterceptor(okHttpLoggingInterceptor).build()

    @Provides
    @Singleton
    fun provideRetrofitHelper(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder().client(okHttpClient).addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun providePlaceHolderApi(builder: Retrofit.Builder): PlaceholderApiService =
        ApiServiceManager(builder).provideService(PlaceholderApiService::class.java, PLACE_HOLDER_BASE_URL)

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): NetworkConnectivityObserver =
        NetworkConnectivityObserver(context = context)
}