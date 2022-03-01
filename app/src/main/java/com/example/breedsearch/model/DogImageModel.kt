package com.example.breedsearch.model


data class ImageBreeds(
    var weight: BreedWeight,
    var height: BreedHeight,
    var id: String? = null,
    var name: String? = null,
    var bred_for: String? = null,
    var breed_group: String? = null,
    var life_span: String? = null,
    var temperament: String? = null,
    var origin: String? = null,
    var reference_image_id: String? = null
)

data class DogImageModel(
    val id: String,
    val url: String,
    val width: String,
    val height: String,
    val breeds: List<ImageBreeds>,
)