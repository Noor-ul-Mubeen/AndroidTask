package com.example.domain.useCases

import com.example.domain.repository.BreedsRepository

class GetBreedsUseCase(private val breedsRepository: BreedsRepository) {
    suspend operator fun invoke() = breedsRepository.getBreeds()
}
