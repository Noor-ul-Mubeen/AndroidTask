package com.example.breedsearch.di

import com.example.breedsearch.BuildConfig
import com.example.data.api.NetworkModule
import com.example.data.mappers.BreedsApiResponseMapper
import com.example.data.repository.breeds.BreedsRepositoryImpl

object ServiceLocator {
    private val networkModule by lazy {
        NetworkModule()
    }

    @Volatile
    var breedsRepository: BreedsRepositoryImpl? = null

    fun provideBreedRepository(): BreedsRepositoryImpl {
        synchronized(this) {
            return breedsRepository ?: createBreedsRepository()
        }
    }

    private fun createBreedsRepository(): BreedsRepositoryImpl {
        val newRepo =
            BreedsRepositoryImpl(
                networkModule.createBreedsApi(BuildConfig.BREEDS_APIS_ENDPOINT),
                BreedsApiResponseMapper()
            )
        breedsRepository = newRepo
        return newRepo
    }
}
