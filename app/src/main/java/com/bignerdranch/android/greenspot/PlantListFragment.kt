package com.bignerdranch.android.greenspot

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.greenspot.databinding.FragmentPlantListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "PlantListFragment"

class PlantListFragment : Fragment() { // This is the fragment code.

    private var _binding: FragmentPlantListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val plantListViewModel: PlantListViewModel by viewModels()

    override fun onCreateView( // This is the first lifecycle function called on a fragment.
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantListBinding.inflate(inflater, container, false)

        binding.plantRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // This is the second lifecycle function called on a fragment.
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch { // This is the coroutine code.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantListViewModel.plants.collect { plants ->
                    binding.plantRecyclerView.adapter =
                        PlantListAdapter(plants) { plantId -> // This is the click listener.  plantId is the id of the plant that was clicked.
                            findNavController().navigate( // This is the navigation code.
                                PlantListFragmentDirections.showPlantDetail(plantId) // This is the navigation action.
                            )
                        }
                }
            }
        }
    }

    override fun onDestroyView() { // This is the last lifecycle function called on a fragment.
        super.onDestroyView()
        _binding = null
    }
}