package com.example.breedsearch.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breedsearch.model.BreedModel
import com.example.breedsearch.repository.BreedRepository
import com.example.breedsearch.model.DogImageModel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel() {
    private val breedRepository: BreedRepository = BreedRepository(application)

    var selectedBreed: MutableLiveData<BreedModel>?= MutableLiveData()
    var breedData: MutableLiveData<List<BreedModel>>?= MutableLiveData()
    var imagesData: MutableLiveData<List<DogImageModel>>?= MutableLiveData()
    val isProcessing: MutableLiveData<Boolean> = MutableLiveData(true)


    fun getBreeds() {
        isProcessing.value = true
        viewModelScope.launch {
            breedData = breedRepository.getBreedList()
        }
    }

    fun getImages(selectedBreed: BreedModel) {
        this.selectedBreed?.value = selectedBreed
        isProcessing.value = true
        viewModelScope.launch {
            imagesData = breedRepository.getImageList(selectedBreed.id)
        }
    }
}