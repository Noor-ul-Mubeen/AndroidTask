package com.example.breedsearch.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.breedsearch.callbackInterface.BreedSelectInterface
import com.example.breedsearch.R
import com.example.breedsearch.model.BreedModel


class BreedBottomAdapter(private var context: Context) :
    RecyclerView.Adapter<BreedBottomAdapter.ViewHolder>() {

    private lateinit var listener: BreedSelectInterface
    private lateinit var dataset: List<BreedModel>

    constructor(context: Context, dataset: List<BreedModel>, listener:BreedSelectInterface) : this(context) {
        this.dataset = dataset
        this.listener = listener
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textview: TextView = view.findViewById(R.id.breedName)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.breed_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textview.apply {
            text = dataset[position].name
            setOnClickListener {
                listener.breedSelected(dataset[position])
            }
        }
    }

    override fun getItemCount() = dataset.size


}
