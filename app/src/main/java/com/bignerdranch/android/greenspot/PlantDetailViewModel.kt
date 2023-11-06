package com.bignerdranch.android.greenspot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class PlantDetailViewModel(plantId: UUID) : ViewModel() {
    private val plantRepository = PlantRepository.get()

    private val _plant: MutableStateFlow<Plant?> = MutableStateFlow(null)
    val plant: StateFlow<Plant?> = _plant.asStateFlow()

    init {
        viewModelScope.launch {
            _plant.value = plantRepository.getPlant(plantId)
        }
    }
}

class PlantDetailViewModelFactory(
    private val plantId: UUID
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantId) as T
    }
}
