package com.example.breedsearch

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breedsearch.adapter.BreedBottomAdapter
import com.example.breedsearch.model.BreedModel
import com.example.breedsearch.viewModel.MainViewModel
import com.example.breedsearch.databinding.ActivityMainBinding
import com.example.breedsearch.viewModel.MainViewModelFactory

import androidx.recyclerview.widget.RecyclerView
import com.example.breedsearch.callbackInterface.BreedSelectInterface
import com.example.breedsearch.adapter.ImageRecycleAdapter

import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity(), BreedSelectInterface {
    private val viewModel by viewModels<MainViewModel> {
        MainViewModelFactory(application)
    }

    lateinit var binding: ActivityMainBinding
    lateinit var bottomSheetDialog: BottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        binding.apply{
            lifecycleOwner = this@MainActivity
            mainViewModel = viewModel

        }
        getBreedList()
        observerBreedData()
        observeImageList()


    }

    private fun observeImageList() {
        viewModel.imagesData?.observe(this, {
            viewModel.isProcessing.value = false
            binding.imagesView.apply{
                adapter = ImageRecycleAdapter(this@MainActivity, it)
                layoutManager = GridLayoutManager(this@MainActivity, 3)
            }
        })
    }

    private fun observerBreedData() {
        viewModel.breedData?.observe(this,{
            viewModel.isProcessing.value = false
            binding.selectedBreed.setOnClickListener { view ->
                showBottomSheetDialog(it)
            }
        })


    }

    private fun getBreedList() {
        viewModel.getBreeds()
    }

    override fun breedSelected(selectedBreed: BreedModel?) {
        if (selectedBreed != null) {
            getDogImage(selectedBreed)
            bottomSheetDialog.dismiss()
        }
    }

    private fun showBottomSheetDialog(breeds: List<BreedModel>) {
        bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.breed_bottom_layout)
        bottomSheetDialog.findViewById<RecyclerView>(R.id.breedListView)?.apply {
            adapter = BreedBottomAdapter(applicationContext, breeds, this@MainActivity)
            layoutManager = LinearLayoutManager(applicationContext)
            bottomSheetDialog.show()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun getDogImage(breedModel: BreedModel) {
        viewModel.getImages( breedModel)
        observeImageList()

    }
}


