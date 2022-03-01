package com.example.breedsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breedsearch.databinding.ImageRecycleItemBinding
import com.example.breedsearch.model.DogImageModel
import android.graphics.Movie
import com.example.breedsearch.utils.Constants


class ImageRecycleAdapter(var context: Context) :
    RecyclerView.Adapter<ImageRecycleAdapter.ItemViewHolder>() {

    lateinit var dataset: List<DogImageModel>

    constructor(context: Context, dataset: List<DogImageModel>) : this(context) {
        this.context = context
        this.dataset = dataset
    }

    class ItemViewHolder(private var binding: ImageRecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogImageModel: DogImageModel, lastElement: Boolean) {
            binding.dogImage = dogImageModel
            binding.lastElement = lastElement
            binding.executePendingBindings()
        }
    }

    fun add(dogImage: DogImageModel) {
        dataset.toMutableList().add(dogImage)
        notifyItemInserted(dataset.size - 1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(ImageRecycleItemBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        val dogImage = dataset[position]
        itemViewHolder.bind(dogImage, position == dataset.size)
    }

    override fun getItemCount(): Int {
        if (dataset.size == Constants.PAGE_SIZE)
            return dataset.size + 1

        return dataset.size
    }

}
