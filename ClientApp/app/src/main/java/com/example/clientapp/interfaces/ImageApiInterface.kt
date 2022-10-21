package com.example.clientapp.interfaces

import com.example.clientapp.data.ImageData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

const val Base_URL="http://10.0.2.2:5276"

interface ImageService {
    @GET("/api/Image")
    fun getRandomImage():Call<ImageData>
    @POST("/api/Image")
    fun uploadImage(@Body obj:ImageData):Call<ImageData>
}