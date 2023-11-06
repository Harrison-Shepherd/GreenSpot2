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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.greenspot.databinding.FragmentPlantListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private const val TAG = "PlantListFragment"

class PlantListFragment : Fragment(){

    private var _binding: FragmentPlantListBinding? = null // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. is the view visible?"
        }

    private val plantListViewModel: PlantListViewModel by viewModels () // Creates a PlantListViewModel

    override fun onCreateView( // This is the first method called when the fragment is created. Inflates the layout and returns the root view.
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentPlantListBinding.inflate(inflater, container, false)

        binding.plantRecyclerView.layoutManager = LinearLayoutManager(context) // Sets up the RecyclerView’s layout manager and adapter.



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                plantListViewModel.plants.collect { plants ->
                    binding.plantRecyclerView.adapter =
                        PlantListAdapter(plants)
                }
            }
        }
    }

    override fun onDestroyView() { // This is the last method called when the fragment is destroyed. Cleans up any references to the binding class instance.
        super.onDestroyView()
        _binding = null
    }



} // End of class PlantListFragment