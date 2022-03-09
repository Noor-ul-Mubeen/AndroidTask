package com.example.breedsearch.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.breedsearch.R

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    Glide
        .with(imgView.context)
        .load(imgUrl)
        .centerCrop()
        .fitCenter()
        .placeholder(R.drawable.loading_animation)
        .into(imgView)
}
