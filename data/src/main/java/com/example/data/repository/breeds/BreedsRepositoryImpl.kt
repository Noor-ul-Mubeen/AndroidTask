package com.example.data.repository.breeds

import com.example.data.api.BreedApi
import com.example.data.mappers.BreedsApiResponseMapper
import com.example.domain.common.Result
import com.example.domain.model.BreedModel
import com.example.domain.model.DogImageModel
import com.example.domain.repository.BreedsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreedsRepositoryImpl(
    private val service: BreedApi,
    private val mapper: BreedsApiResponseMapper
) : BreedsRepository {
    override suspend fun getBreeds(): Result<List<BreedModel>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getBreeds()
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toBreedList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }

    override suspend fun getImagesList(breed: BreedModel): Result<List<DogImageModel>> =
        withContext(Dispatchers.IO) {
            try {
                val response = service.getImages(breed.id, "52")
                if (response.isSuccessful) {
                    return@withContext Result.Success(mapper.toImagesList(response.body()!!))
                } else {
                    return@withContext Result.Error(Exception(response.message()))
                }
            } catch (e: Exception) {
                return@withContext Result.Error(e)
            }
        }
}
