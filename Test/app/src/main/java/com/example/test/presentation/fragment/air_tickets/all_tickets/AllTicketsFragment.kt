package com.example.test.presentation.fragment.air_tickets.all_tickets

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FragmentAllTicketsBinding
import com.example.test.presentation.MainActivity
import com.example.test.presentation.ViewModuleActivity
import kotlinx.coroutines.launch
import javax.inject.Inject


class AllTicketsFragment : Fragment() {
    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!
    private val mainViewModule: ViewModuleActivity by activityViewModels()

    @Inject
    lateinit var factory: FactoryTickets
    private val allViewModule: TicketsViewModule by viewModels { factory }
    lateinit var adapter: AdapterTickets
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllTicketsBinding.inflate(layoutInflater)
        (activity as MainActivity).appComponent.inject(this)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModule.bottomAirTickets = R.id.allTicketsFragment
        requireActivity().onBackPressedDispatcher.addCallback() {
            mainViewModule.navController.navigate(R.id.searchFragment)
        }
        binding.cityFromTo.text = resources.getString(
            R.string.city_from_to,
            mainViewModule.cityFrom,
            mainViewModule.cityTO
        )
        binding.day.text = resources.getString(
            R.string.day_to,
            mainViewModule.dayStringTo()
        )
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_allTicketsFragment_to_searchFragment)
        }
        lifecycleScope.launch {
            isOnline()
        }
    }

    private suspend fun isOnline() {
        binding.recyclerTickets.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.layoutWifi.visibility = View.INVISIBLE
        if (mainViewModule.isOnline(requireContext())) {
            adapter = AdapterTickets(allViewModule.ticked().tickets)
            binding.recyclerTickets.adapter = adapter
            binding.recyclerTickets.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}