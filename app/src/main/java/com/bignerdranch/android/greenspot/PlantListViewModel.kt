package com.bignerdranch.android.greenspot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlantListViewModel : ViewModel() { /* this is a view model for the plant list fragment.
 This is a view model.  It is a layer between the fragment and the repository.
 */
private val plantRepository = PlantRepository.get() // This is the repository.

    private val _plants: MutableStateFlow<List<Plant>> = MutableStateFlow(emptyList()) // This is the list of plants.
    val plants: StateFlow<List<Plant>>
        get() = _plants.asStateFlow()

    init {
        viewModelScope.launch {
            plantRepository.getPlants().collect {// This is the coroutine code.
                _plants.value = it
            }
        }
    }

    suspend fun addPlant(plant: Plant) {
        plantRepository.addPlant(plant)
    }

}


