package com.bignerdranch.android.greenspot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.greenspot.databinding.ListItemPlantBinding // This is the binding class for the list item layout.
import java.util.UUID


class PlantHolder(
    private val binding: ListItemPlantBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(plant: Plant, onPlantClicked: (plantId: UUID) -> Unit) { // This is the bind function.
        binding.plantTitle.text = plant.title
        binding.plantDate.text = plant.date.toString()

        binding.root.setOnClickListener {// This is the click listener.
            onPlantClicked(plant.id)
        }

        binding.plantSolved.visibility = if (plant.isSolved) { // This is the visibility code. TODO CHANGE TO PLACE
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}

class PlantListAdapter( // This is the adapter class.
    private val plants: List<Plant>,
    private val onPlantClicked: (plantId: UUID) -> Unit
) : RecyclerView.Adapter<PlantHolder>() {
    override fun onCreateViewHolder( // This is the create view holder function.
        parent: ViewGroup,
        viewType: Int
    ): PlantHolder { // This is the view holder code.
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlantBinding.inflate(inflater, parent, false)
        return PlantHolder(binding)
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) { // This is the bind view holder function.
        val plant = plants[position]
        holder.bind(plant, onPlantClicked)
    }

    override fun getItemCount() = plants.size
}