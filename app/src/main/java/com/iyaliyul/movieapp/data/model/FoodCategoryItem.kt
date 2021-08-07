package com.iyaliyul.movieapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//TODO 2
@Parcelize
data class FoodCategoryItem(
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
) : Parcelable