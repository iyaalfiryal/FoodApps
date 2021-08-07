package com.iyaliyul.movieapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.iyaliyul.movieapp.R
import com.iyaliyul.movieapp.data.model.FoodCategoryItem
import com.iyaliyul.movieapp.databinding.ActivityDetailBinding
import com.iyaliyul.movieapp.utils.loadImage
import com.iyaliyul.movieapp.utils.showToast

class DetailActivity : AppCompatActivity() {

    private val binding: ActivityDetailBinding by viewBinding()
    private var foodItem: FoodCategoryItem? = null

    companion object{
        const val DETAIL_EXTRA = "detail"
    }

    // key and value

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // ? (nullable)= nullable or can be null
        // !! (bang operator) = cant be null if null DIE
        // ?: (elvish operator)  = if a is null, otherwise be


        foodItem = intent?.extras?.getParcelable<FoodCategoryItem>(DETAIL_EXTRA)
        foodItem?.let {
            //kotlin scope
            binding.apply {
                detailImg.loadImage(it.strCategoryThumb)
                detailName.text = it.strCategory
                detailDesc.text = it.strCategoryDescription
            }
        }
    }
}