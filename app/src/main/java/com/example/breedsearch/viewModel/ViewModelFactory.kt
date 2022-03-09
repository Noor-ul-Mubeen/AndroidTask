package com.example.breedsearch.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.useCases.GetBreedsUseCase
import com.example.domain.useCases.GetImageUseCase

class BreedsViewModelFactory(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val getImageUseCase: GetImageUseCase
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            getBreedsUseCase,
            getImageUseCase
        ) as T
    }
}
