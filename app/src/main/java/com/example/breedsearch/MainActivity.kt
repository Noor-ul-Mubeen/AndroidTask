package com.example.breedsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.breedsearch.Model.BreedModel
import com.example.breedsearch.ViewModel.MainViewModel
import com.example.breedsearch.databinding.ActivityMainBinding
import androidx.lifecycle.ViewModelProvider




class MainActivity : AppCompatActivity() {
    lateinit var spinner:Spinner
    lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.mainViewModel = viewModel
        getBreedList()

    }

    private fun getBreedList() {
        viewModel.getBreeds().observe(this, Observer<List<BreedModel>>{ breeds ->
            // update UI
        })

    }
}

