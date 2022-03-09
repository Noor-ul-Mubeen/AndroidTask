package com.example.data.model

import com.squareup.moshi.Json

data class BreedItem(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String
)

data class ImageItem(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "url")
    val url: String
)
