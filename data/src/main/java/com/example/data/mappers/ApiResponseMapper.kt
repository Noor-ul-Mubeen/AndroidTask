package com.example.data.mappers

import com.example.data.model.BreedItem
import com.example.data.model.ImageItem
import com.example.domain.model.BreedModel
import com.example.domain.model.DogImageModel

class BreedsApiResponseMapper {
    fun toBreedList(response: List<BreedItem>): List<BreedModel> {
        return response.map {
            BreedModel(
                it.id,
                it.name
            )
        }
    }

    fun toImagesList(response: List<ImageItem>): List<DogImageModel> {
        return response.map {
            DogImageModel(
                it.id,
                it.url
            )
        }
    }
}
