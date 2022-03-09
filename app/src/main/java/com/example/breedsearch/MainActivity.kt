package com.example.breedsearch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breedsearch.adapter.BreedBottomAdapter
import com.example.breedsearch.adapter.ImageRecycleAdapter
import com.example.breedsearch.callbackInterface.BreedSelectInterface
import com.example.breedsearch.databinding.ActivityMainBinding
import com.example.breedsearch.viewModel.BreedsViewModelFactory
import com.example.breedsearch.viewModel.MainViewModel
import com.example.domain.model.BreedModel
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels {
        BreedsViewModelFactory(
            ((applicationContext) as BreedSearchApplication).getBreedsUseCase,
            ((applicationContext) as BreedSearchApplication).getImagesUseCase
        )
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel
        }
        getBreedList()
        observerViewModel()
    }

    private fun observerViewModel() {
        with(viewModel) {
            breedData.observe(this@MainActivity) {
                binding.selectedBreed.setOnClickListener { _ ->
                    showBottomSheetDialog(it)
                }
            }
            error.observe(this@MainActivity) {
                Toast.makeText(
                    this@MainActivity,
                    getString(R.string.an_error_has_occurred, it),
                    Toast.LENGTH_SHORT
                ).show()
            }
            imagesData.observe(this@MainActivity) {
                binding.imagesView.apply {
                    adapter = ImageRecycleAdapter(this@MainActivity, it)
                    layoutManager = GridLayoutManager(this@MainActivity, 3)
                }
            }
        }
    }

    private fun getBreedList() {
        viewModel.getBreeds()
    }

    private fun showBottomSheetDialog(breeds: List<BreedModel>) {
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.apply {
            setContentView(R.layout.breed_bottom_layout)
            findViewById<RecyclerView>(R.id.breedListView)?.apply {
                adapter = BreedBottomAdapter(
                    breeds,
                    object : BreedSelectInterface {
                        override fun breedSelected(selectedBreed: BreedModel) {
                            getDogImage(selectedBreed)
                        }
                    }
                )
                layoutManager = LinearLayoutManager(applicationContext)
                bottomSheetDialog.show()
            }
        }
    }

    private fun getDogImage(breedModel: BreedModel) {
        viewModel.getImages(breedModel)
        bottomSheetDialog.dismiss()
    }
}
