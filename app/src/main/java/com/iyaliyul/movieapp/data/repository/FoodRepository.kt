package com.iyaliyul.movieapp.data.repository

import com.iyaliyul.movieapp.data.model.FoodCategoryResponse
import com.iyaliyul.movieapp.data.source.remote.APIService
import com.iyaliyul.movieapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import retrofit2.HttpException
import javax.inject.Inject

//TODO 3
class FoodRepository @Inject constructor(private val apiService: APIService){

    suspend fun fetchFoodCategories(): Flow<Resource<FoodCategoryResponse>> {
       return flow {
           val response = apiService.fetchFoodCategories()
           try {
               if (response.categories.isNotEmpty()){
                   emit(Resource.Success(response))
               }else{
                   emit(Resource.Empty)
               }
           } catch (e: HttpException) {
               emit(Resource.Error(e.toString(), response))
           }
       }
    }
}
