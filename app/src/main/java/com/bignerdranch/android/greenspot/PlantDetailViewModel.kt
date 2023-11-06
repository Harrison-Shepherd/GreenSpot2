package com.bignerdranch.android.greenspot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

class PlantDetailViewModel(plantId: UUID) : ViewModel() { // This is a view model for the plant detail fragment.
    private val plantRepository = PlantRepository.get()

    private val _plant: MutableStateFlow<Plant?> = MutableStateFlow(null)
    val plant: StateFlow<Plant?> = _plant.asStateFlow() // This is the plant.

    init {
        viewModelScope.launch { // This is the coroutine code.
            _plant.value = plantRepository.getPlant(plantId)
        }
    }

    fun updatePlant(onUpdate: (Plant) -> Plant) { // This is the update function.
        _plant.update { oldPlant ->
            oldPlant?.let { onUpdate(it) }
        }
    }

    override fun onCleared() { // This is the onCleared function.
        super.onCleared()
            plant.value?.let { plantRepository.updatePlant(it) }

    }
}

class PlantDetailViewModelFactory( // This is the view model factory.
    private val plantId: UUID
) : ViewModelProvider.Factory { // This is the view model provider factory.
    override fun <T : ViewModel> create(modelClass: Class<T>): T { // This is the create function.
        return PlantDetailViewModel(plantId) as T
    }
}
