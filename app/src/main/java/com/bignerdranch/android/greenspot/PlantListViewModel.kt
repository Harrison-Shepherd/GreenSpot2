package com.bignerdranch.android.greenspot

import androidx.lifecycle.ViewModel
import java.util.Date
import java.util.UUID

class PlantListViewModel : ViewModel() { // This class is a subclass of ViewModel. It will hold the data for the PlantListFragment. It populates the list with 100 fake items
    val plants = mutableListOf<Plant>()
    init {
        for (i in 0 until 100) {
            val plant = Plant(
                id = UUID.randomUUID(),
                title ="Plant #$i",
                date = Date(),
                isSolved = i % 2 == 0
            )
            plants += plant
        }
    }
}



