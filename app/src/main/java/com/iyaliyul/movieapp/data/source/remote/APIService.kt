package com.iyaliyul.movieapp.data.source.remote

import com.iyaliyul.movieapp.data.model.FoodCategoryResponse
import retrofit2.http.GET

interface APIService {

    @GET("categories.php")
    suspend fun fetchFoodCategories(): FoodCategoryResponse

}