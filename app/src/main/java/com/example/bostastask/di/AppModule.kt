package com.example.bostastask.di

import android.content.Context
import com.example.bostastask.BuildConfig.BASE_URL
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
object AppModule {

    @Provides
    @Singleton
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        return logging
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providePlaceHolderApi(okHttpClient: OkHttpClient): PlaceholderApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
            .create(PlaceholderApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): NetworkConnectivityObserver =
        NetworkConnectivityObserver(context = context)
}