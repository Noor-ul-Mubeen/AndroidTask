package com.example.domain.useCases

import com.example.domain.model.BreedModel
import com.example.domain.repository.BreedsRepository

class GetImageUseCase(private val breedsRepository: BreedsRepository) {
    suspend operator fun invoke(selectedBreed: BreedModel) =
        breedsRepository.getImagesList(selectedBreed)
}
