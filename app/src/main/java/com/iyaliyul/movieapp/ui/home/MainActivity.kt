package com.iyaliyul.movieapp.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.iyaliyul.movieapp.R
import com.iyaliyul.movieapp.databinding.ActivityMainBinding
import com.iyaliyul.movieapp.ui.detail.DetailActivity
import com.iyaliyul.movieapp.utils.Resource
import com.iyaliyul.movieapp.utils.showToast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // singleton: Firebase.getInstance().state
    // non-singleton: Firebase().state
    // companion: Firebase.state

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

        //todo 5
        //observe state
        viewModel.foodData.observe(this){ response ->
            when(response){
                is Resource.Success -> {

                    binding.progressCircular.isVisible = false
                    response.data.categories.map { food ->
                        foodAdapter.add(MainItem(food){
//                            showToast(food.strCategory)
                            //ketika user klik item data akan dikirim melalui parcelable dan akan ditampilkan ke
                            //detail activity
                            startActivity(Intent(this, DetailActivity::class.java)
                                .putExtra(DetailActivity.DETAIL_EXTRA, it)
                            )
                        })
                    }

                }
                is Resource.Loading -> {
                    binding.progressCircular.isVisible = true
                }

                is Resource.Error -> {
                    binding.progressCircular.isVisible = false
                    showToast(response.message)
                }

                is Resource.Empty -> {
                    binding.progressCircular.isVisible = false
                    showToast("Data is empty")
                }
            }

        }
    }
}