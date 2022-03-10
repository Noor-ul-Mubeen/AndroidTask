package com.example.data.api

import com.example.data.model.BreedItem
import com.example.data.model.ImageItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BreedApi {
    @GET("/v1/breeds")
    suspend fun getBreeds(): Response<List<BreedItem>>

    @GET("/v1/images/search")
    suspend fun getImages(
        @Query("breed_id") breedId: String,
        @Query("limit") pageLimit: String
    ): Response<List<ImageItem>>
}
