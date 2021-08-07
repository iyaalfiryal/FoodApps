package com.iyaliyul.movieapp.ui.home

import android.view.View
import com.iyaliyul.movieapp.R
import com.iyaliyul.movieapp.data.model.FoodCategoryItem
import com.iyaliyul.movieapp.databinding.FoodItemViewBinding
import com.iyaliyul.movieapp.utils.loadImage
import com.xwray.groupie.viewbinding.BindableItem
import kotlin.contracts.Returns

//todo 8
class MainItem(
    private val food: FoodCategoryItem,
    private val onclick: (FoodCategoryItem) -> Unit
): BindableItem<FoodItemViewBinding>() {

    //high order function / closure / lambda
    //yourfunction: () -> unit

    override fun bind(viewBinding: FoodItemViewBinding, position: Int) {
        viewBinding.apply {
            itemImg.loadImage(food.strCategoryThumb)
            itemName.text = food.strCategory
            itemDesc.text = food.strCategoryDescription
            itemCard.setOnClickListener {
                onclick(food)
            }
        }
    }

    override fun getLayout(): Int = R.layout.food_item_view


    override fun initializeViewBinding(view: View): FoodItemViewBinding {
        return FoodItemViewBinding.bind(view)

    }

}