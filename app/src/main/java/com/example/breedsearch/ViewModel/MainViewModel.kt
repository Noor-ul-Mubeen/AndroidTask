package com.example.breedsearch.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.breedsearch.Model.BreedModel

class MainViewModel : ViewModel() {
    private val breedNames: MutableLiveData<List<BreedModel>> by lazy {
        MutableLiveData<List<BreedModel>>().also {
            loadBreeds()
        }
    }

    fun getBreeds(): LiveData<List<BreedModel>> {
        return breedNames
    }

    private fun loadBreeds() {
        // Do an asynchronous operation to fetch users.
    }

}