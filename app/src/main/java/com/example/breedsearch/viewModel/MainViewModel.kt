package com.example.breedsearch.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.breedsearch.callbackInterface.ErrorInterface
import com.example.breedsearch.model.BreedModel
import com.example.breedsearch.repository.BreedRepository
import com.example.breedsearch.model.DogImageModel
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : ViewModel(),ErrorInterface {
    private val breedRepository: BreedRepository = BreedRepository(application, this)

    var selectedBreed: MutableLiveData<BreedModel>?= MutableLiveData()
    var breedData: MutableLiveData<List<BreedModel>>?= MutableLiveData()
    var imagesData: MutableLiveData<List<DogImageModel>>?= MutableLiveData()
    val isProcessing: MutableLiveData<Boolean> = MutableLiveData(true)
    val errorLiveMessage: MutableLiveData<String> = MutableLiveData("")

    override fun errorCallback(errorMessage: String?) {
        if (!errorMessage.isNullOrEmpty()){
            isProcessing.value = false
            errorLiveMessage.value = errorMessage

        }
    }

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