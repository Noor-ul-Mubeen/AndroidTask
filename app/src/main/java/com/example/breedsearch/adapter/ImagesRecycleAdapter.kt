package com.example.breedsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.breedsearch.databinding.ImageRecycleItemBinding
import com.example.domain.model.DogImageModel

class ImageRecycleAdapter(private var context: Context) :
    RecyclerView.Adapter<ImageRecycleAdapter.ItemViewHolder>() {

    private lateinit var dataset: List<DogImageModel>

    constructor(context: Context, dataset: List<DogImageModel>) : this(context) {
        this.context = context
        this.dataset = dataset
    }

    class ItemViewHolder(private var binding: ImageRecycleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dogImageModel: DogImageModel) {
            binding.dogImage = dogImageModel
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(ImageRecycleItemBinding.inflate(LayoutInflater.from(viewGroup.context)))
    }

    override fun onBindViewHolder(itemViewHolder: ItemViewHolder, position: Int) {
        val dogImage = dataset[position]
        itemViewHolder.bind(dogImage)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}
