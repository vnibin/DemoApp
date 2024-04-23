package com.example.demoapp.core.di

import com.example.demoapp.catmodule.data.datasource.remote.ApiInterface
import com.example.demoapp.catmodule.data.datasource.remote.CatListRepoImpl
import com.example.demoapp.catmodule.data.model.CatResModel
import com.example.demoapp.catmodule.data.repository.CatListRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        return Retrofit.Builder().baseUrl("https://api.thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
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