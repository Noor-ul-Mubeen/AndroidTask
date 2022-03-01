package com.example.breedsearch.model

data class BreedWeight(
    var imperial: String? = null,
    var metric: String? = null)


data class BreedHeight(
    var imperial: String? = null,
    var metric: String? = null)

data class BreedImage(
    var id: String? = null,
    var height: String? = null,
    var url: String? = null,
    var width: String? = null)




data class BreedModel(
    val id: String,
    val weight: BreedWeight,
    val image: BreedImage,
    val height: BreedHeight,
    val name: String,
    val bred_for: String,
    val breed_group: String,
    val life_span: String,
    val temperament: String,
    val origin: String,
    val reference_image_id: String,

)