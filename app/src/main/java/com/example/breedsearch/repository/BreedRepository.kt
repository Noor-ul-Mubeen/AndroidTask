package com.example.breedsearch.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.breedsearch.aPI.APIQueue
import com.example.breedsearch.model.BreedModel
import com.example.breedsearch.model.DogImageModel
import com.example.breedsearch.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class BreedRepository(application: Context) {
    private val context: Context = application
    val url = "https://api.thedogapi.com/v1"
    val gson = Gson()
    val breedData: MutableLiveData<List<BreedModel>> = MutableLiveData<List<BreedModel>>()

    fun getBreedList(): MutableLiveData<List<BreedModel>> {
        val breedListRequest: JsonArrayRequest = object : JsonArrayRequest(
            Method.GET, url + "/breeds", null,
            Response.Listener {
                val sType = object : TypeToken<List<BreedModel>>() {}.type
                breedData.value = gson.fromJson(it.toString(), sType)

            }, Response.ErrorListener {
                print(it)//To change body of created functions use File | Settings | File Templates.
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                var params: MutableMap<String, String>? = super.getHeaders()
                if (params == null || params.isEmpty())
                    params = HashMap()

                params["x-api-key"] = "ABC123"
                return params
            }
        }

        APIQueue.getInstance(context).addToRequestQueue(breedListRequest)
        return breedData
    }

    fun getImageList(breedId: String): MutableLiveData<List<DogImageModel>> {
        val imagesData: MutableLiveData<List<DogImageModel>> =
            MutableLiveData<List<DogImageModel>>()

        val requestURL =
            url + "/images/search?limit=" + Constants.PAGE_SIZE + "&breed_id=" + breedId

        val imagesRequest: JsonArrayRequest = object : JsonArrayRequest(
            Method.GET,
            requestURL,
            null,
            Response.Listener {
                val sType = object : TypeToken<List<DogImageModel>>() {}.type
                imagesData.value = gson.fromJson(it.toString(), sType)

            },
            Response.ErrorListener {
                print(it)//To change body of created functions use File | Settings | File Templates.
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                var params: MutableMap<String, String>? = super.getHeaders()
                if (params == null || params.isEmpty())
                    params = HashMap()

                params["x-api-key"] = "ABC123"
                return params
            }
        }

        APIQueue.getInstance(context).addToRequestQueue(imagesRequest)
        return imagesData
    }

}
