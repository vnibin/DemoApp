package com.example.demoapp.di

import android.provider.SyncStateContract.Constants
import com.example.demoapp.core.common.AppConstants
import com.example.demoapp.data.datasource.remote.ApiInterface
import com.example.demoapp.data.repository.CatListRepoImpl
import com.example.demoapp.domain.repository.CatListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit
    {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        httpClient.addInterceptor(logging)
        return Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): ApiInterface
    {
        return retrofit.create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(apiInterface: ApiInterface) : CatListRepo
    {
        return CatListRepoImpl(apiInterface)
    }


}