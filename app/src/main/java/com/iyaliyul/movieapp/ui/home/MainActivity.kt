package com.iyaliyul.movieapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.iyaliyul.movieapp.R
import com.iyaliyul.movieapp.databinding.ActivityMainBinding
import com.iyaliyul.movieapp.utils.Resource
import com.iyaliyul.movieapp.utils.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainViewModel by viewModels()
    private val foodAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up si recycler view
        with(binding.rvFood){
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = foodAdapter
            setHasFixedSize(true)
        }

        viewModel.foodData.observe(this){ response ->
            when(response){
                is Resource.Success -> {
                    response.data.categories.map { food ->
                        foodAdapter.add(MainItem(food){
                            showToast(food.strCategory)
                        })
                    }

                }
                is Resource.Loading -> {

                }
                is Resource.Error -> {

                }

                is Resource.Empty -> {

                }
            }

        }
    }
}