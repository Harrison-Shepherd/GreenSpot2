package com.bignerdranch.android.greenspot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PlantListViewModel : ViewModel() { // This class is a subclass of ViewModel. It will hold the data for the PlantListFragment. It populates the list with 100 fake items
    private val plantRepository = PlantRepository.get()

    private val _plants: MutableStateFlow<List<Plant>> = MutableStateFlow(emptyList())
    val plants: StateFlow<List<Plant>>
        get() = _plants.asStateFlow()

    init {
        viewModelScope.launch {
            plantRepository.getPlants().collect {
                _plants.value = it
            }
        }
    }


}


