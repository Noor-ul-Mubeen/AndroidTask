package com.example.breedsearch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.common.Result
import com.example.domain.model.BreedModel
import com.example.domain.model.DogImageModel
import com.example.domain.useCases.GetBreedsUseCase
import com.example.domain.useCases.GetImageUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getBreedsUseCase: GetBreedsUseCase,
    private val getImageUseCase: GetImageUseCase,
) : ViewModel() {

    var selectedBreed: MutableLiveData<BreedModel>? = MutableLiveData()
    private val _breedData = MutableLiveData<List<BreedModel>>()
    val breedData = _breedData

    private val _imagesData = MutableLiveData<List<DogImageModel>>()
    val imagesData = _imagesData

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getBreeds() {
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val breedResults = getBreedsUseCase.invoke()) {
                is Result.Success -> {
                    _breedData.value = breedResults.data
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _breedData.value = emptyList()
                    _error.postValue(breedResults.exception.message)
                }
            }
        }
    }

    fun getImages(selectedBreed: BreedModel) {
        this.selectedBreed?.value = selectedBreed
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when (val imagesResult = getImageUseCase.invoke(selectedBreed)) {
                is Result.Success -> {
                    _imagesData.value = imagesResult.data
                    _dataLoading.postValue(false)
                }

                is Result.Error -> {
                    _dataLoading.postValue(false)
                    _breedData.value = emptyList()
                    _error.postValue(imagesResult.exception.message)
                }
            }
        }
    }
}
