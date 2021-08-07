package com.iyaliyul.movieapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iyaliyul.movieapp.data.model.FoodCategoryResponse
import com.iyaliyul.movieapp.data.repository.FoodRepository
import com.iyaliyul.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

//todo 4
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel() {

    //dua variable, 1 transaksi di dalam view model dan yang lainnya di dalam view (activity/fragment)
    private val _foodData = MutableLiveData<Resource<FoodCategoryResponse>>()
    val foodData = _foodData

    init {
        fetchFoodCategories()
    }
    fun fetchFoodCategories() = viewModelScope.launch {
        _foodData.value = Resource.Loading
        repository.fetchFoodCategories().collect {
            food ->
            _foodData.value = food

        }
    }
}