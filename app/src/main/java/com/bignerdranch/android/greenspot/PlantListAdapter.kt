package com.bignerdranch.android.greenspot

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.greenspot.databinding.ListItemPlantBinding // This is the binding class for the list item layout.



class PlantHolder( // This class is a subclass of RecyclerView.ViewHolder. It holds a reference to the binding class instance.
    private val binding: ListItemPlantBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(plant: Plant) { // Binds the plant’s data to the ViewHolder’s View.
        binding.plantTitle.text = plant.title
        binding.plantDate.text = plant.date.toString()

        binding.root.setOnClickListener {// Sets up a click listener on the ViewHolder’s View.
            Toast.makeText(
                binding.root.context,
                "${plant.title} clicked!", // Shows a Toast when the user clicks on the ViewHolder’s View.
                Toast.LENGTH_SHORT
            ).show() // Shows a Toast when the user clicks on the ViewHolder’s View.
        }
    }
}







class PlantListAdapter( // This class is a subclass of RecyclerView.Adapter. It binds the data to the ViewHolder.
    private val plants: List<Plant>
) : RecyclerView.Adapter<PlantHolder>() { // This class is a subclass of RecyclerView.Adapter. It binds the data to the ViewHolder.
    override fun onCreateViewHolder( // This method is called by the RecyclerView when it needs a new ViewHolder to display an item with.
        parent: ViewGroup,
        viewType: Int
    ) : PlantHolder { // Creates a PlantHolder
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemPlantBinding.inflate(inflater, parent, false)
        return PlantHolder(binding) // Returns a PlantHolder
    }
    override fun onBindViewHolder(holder: PlantHolder, position: Int) { // This method is called by the RecyclerView when it wants to bind a ViewHolder to a plant.
        val plant = plants[position]
        holder.bind(plant)
    }
    override fun getItemCount() = plants.size // This method tells the RecyclerView how many items are in the list.
}