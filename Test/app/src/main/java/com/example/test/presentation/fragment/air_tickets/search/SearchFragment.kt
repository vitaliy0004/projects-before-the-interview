package com.example.test.presentation.fragment.air_tickets.search

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentSearchBinding
import com.example.test.presentation.MainActivity
import com.example.test.presentation.ViewModuleActivity
import androidx.activity.addCallback
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val mainViewModule: ViewModuleActivity by activityViewModels()

    @Inject
    lateinit var factory: FactorySearch
    private val searchViewModule: SearchViewModule by viewModels { factory }
    lateinit var adapterFlight: AdapterFlight

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater)
        (activity as MainActivity).appComponent.inject(this)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModule.bottomAirTickets = R.id.searchFragment
        binding.cityText.search.setOnClickListener {
            findNavController().navigate(R.id.action_searchFragment_to_airTicketsFragment)
        }
        requireActivity().onBackPressedDispatcher.addCallback() {
            mainViewModule.navController.navigate(R.id.airTicketsFragment)
        }
        cityTOAndFrom()
        binding.cityText.swap.setOnClickListener {
            val cityTo = mainViewModule.cityTO
            mainViewModule.cityTO = mainViewModule.cityFrom
            mainViewModule.cityFrom = cityTo
            cityTOAndFrom()
        }
        binding.cardFly.setOnClickListener {
            val constraint = CalendarConstraints.Builder()
                //  .setValidator(DateValidatorPointBackward.before(Calendar.getInstance().timeInMillis))
                .setValidator(DateValidatorPointForward.from(Calendar.getInstance().timeInMillis))
                .build()
            val dataDialog = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(getString(R.string.choose_day))
                .setSelection(mainViewModule.dayFrom.timeInMillis)
                .setCalendarConstraints(constraint)
                .build()
            dataDialog.addOnPositiveButtonClickListener {
                mainViewModule.dayFrom.timeInMillis = it
                binding.dayMonth.text = resources.getString(
                    R.string.day_string_preview,
                    mainViewModule.dayStringPreview(mainViewModule.dayFrom).dropLast(2)
                )
                binding.week.text =
                    mainViewModule.dayStringPreview(mainViewModule.dayFrom).takeLast(2)
            }
            dataDialog.show(childFragmentManager, "dataPiker")
        }
        binding.cardBack.setOnClickListener {
            val constraint = CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.from(mainViewModule.dayFrom.timeInMillis))
                .build()
            val dataDialog = MaterialDatePicker.Builder
                .datePicker()
                .setTitleText(getString(R.string.choose_day))
                .setSelection(mainViewModule.dayTo.timeInMillis)
                .setCalendarConstraints(constraint)
                .build()
            dataDialog.addOnPositiveButtonClickListener {
                mainViewModule.dayTo.timeInMillis = it
                binding.cardBackText.text = mainViewModule.dayStringPreview(mainViewModule.dayTo)
            }
            dataDialog.show(childFragmentManager, "dataPiker")
        }
        lifecycleScope.launch {
            isOnline()
        }
        binding.allTickets.setOnClickListener {
            if (mainViewModule.dayFrom.timeInMillis > mainViewModule.dayTo.timeInMillis)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.error_day_from_to),
                    Toast.LENGTH_SHORT
                ).show()
            else findNavController().navigate(R.id.action_searchFragment_to_allTicketsFragment)
        }

    }

    private suspend fun isOnline() {
        binding.layoutWifi.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutSwitch.visibility = View.INVISIBLE
        binding.allTickets.visibility = View.INVISIBLE
        binding.tickets.visibility = View.INVISIBLE
        if (mainViewModule.isOnline(requireContext())) {
            adapterFlight = AdapterFlight(
                searchViewModule.flight().ticketsOffers,
                requireContext()
            )
            binding.flightRecycler.adapter = adapterFlight
            binding.tickets.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            binding.layoutSwitch.visibility = View.VISIBLE
            binding.allTickets.visibility = View.VISIBLE
        } else {
            binding.layoutWifi.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
            binding.wifi.textView.setOnClickListener {
                lifecycleScope.launch {
                    isOnline()
                }
            }
        }
    }

    private fun cityTOAndFrom() {
        binding.cityText.inputFrom.text = mainViewModule.cityFrom
        binding.cityText.inputTo.text = mainViewModule.cityTO
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}