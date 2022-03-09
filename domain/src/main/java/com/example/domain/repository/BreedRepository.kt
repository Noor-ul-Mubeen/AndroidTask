package com.example.domain.repository // ktlint-disable filename

import com.example.domain.common.Result
import com.example.domain.model.BreedModel
import com.example.domain.model.DogImageModel

interface BreedsRepository {

    suspend fun getBreeds(): Result<List<BreedModel>>

    suspend fun getImagesList(breed: BreedModel): Result<List<DogImageModel>>
}
