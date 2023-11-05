package com.bignerdranch.android.greenspot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.bignerdranch.android.greenspot.databinding.FragmentPlantDetailBinding
import java.util.Date
import java.util.UUID

class PlantDetailFragment : Fragment() {


    private var _binding: FragmentPlantDetailBinding? = null // This property is only valid between onCreateView and onDestroyView.
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. is the view visible?"
        }

    private lateinit var plant: Plant

    override fun onCreate(savedInstanceState: Bundle?) { // Prepares the layout and initializes the fragment’s data.
        super.onCreate(savedInstanceState)
        plant = Plant(
            id = UUID.randomUUID(),
            title = "",
            date = Date(),
            isSolved = false
        )
    }

    override fun onCreateView( // This is the first method called when the fragment is created. Inflates the layout and returns the root view.
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { // This is the second method called when the fragment is created. Sets up the fragment’s view.
        super.onViewCreated(view, savedInstanceState)
        binding.apply {

            plantTitle.doOnTextChanged { text, _, _, _ -> // Updates the plant’s title when the user types in the text field.
                plant = plant.copy(title = text.toString())
            }

            plantDate.apply {// Updates the plant’s date when the user selects a date from the date picker.
                text = plant.date.toString()
                isEnabled = false
            }

            plantSolved.setOnCheckedChangeListener { _, isChecked -> // Updates the plant’s solved property when the user checks or unchecks the checkbox.
                plant = plant.copy(isSolved = isChecked)

            }
        }
    }

    override fun onDestroyView() { // This is the last method called when the fragment is destroyed. Cleans up any references to the binding class instance.
        super.onDestroyView()
        _binding = null
    }


}