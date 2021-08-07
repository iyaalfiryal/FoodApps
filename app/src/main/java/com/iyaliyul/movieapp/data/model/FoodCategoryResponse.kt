package com.iyaliyul.movieapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FoodCategoryResponse(
    val categories: List<FoodCategoryItem>
) : Parcelable