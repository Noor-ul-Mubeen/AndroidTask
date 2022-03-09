package com.example.breedsearch

import android.app.Application
import com.example.breedsearch.di.ServiceLocator
import com.example.data.repository.breeds.BreedsRepositoryImpl
import com.example.domain.useCases.GetBreedsUseCase
import com.example.domain.useCases.GetImageUseCase
import timber.log.Timber

class BreedSearchApplication : Application() {
    private val booksRepository: BreedsRepositoryImpl
        get() = ServiceLocator.provideBreedRepository()

    val getBreedsUseCase: GetBreedsUseCase
        get() = GetBreedsUseCase(booksRepository)

    val getImagesUseCase: GetImageUseCase
        get() = GetImageUseCase(booksRepository)

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
