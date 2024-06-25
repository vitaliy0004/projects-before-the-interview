package com.example.test.presentation.fragment.air_tickets.air_tickets

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.DialogBackBinding
import com.example.test.databinding.DialogToBinding
import com.example.test.databinding.FragmentAirTicketsBinding
import com.example.test.presentation.Constant
import com.example.test.presentation.MainActivity
import com.example.test.presentation.ViewModuleActivity
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.launch
import javax.inject.Inject

class AirTicketsFragment : Fragment() {
    private var _binding: FragmentAirTicketsBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter

    @Inject
    lateinit var factory: Factory
    private val airViewModule: ViewModuleAirTickets by viewModels { factory }
    private val mainViewModel: ViewModuleActivity by activityViewModels()
    private lateinit var getPrefers: SharedPreferences
    private lateinit var prefrs: SharedPreferences.Editor

    private lateinit var dialog: BottomSheetDialog
    private lateinit var dialogIconName: BottomSheetDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAirTicketsBinding.inflate(layoutInflater)
        prefrs = context?.getSharedPreferences(Constant.GET_CITY, Context.MODE_PRIVATE)?.edit()!!
        (activity as MainActivity).appComponent.inject(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel.bottomAirTickets = R.id.airTicketsFragment
        getPrefers = context?.getSharedPreferences(Constant.GET_CITY, Context.MODE_PRIVATE)!!
        requireActivity().onBackPressedDispatcher.addCallback() {
            requireActivity().finish()
        }
        binding.input.inputFrom.setOnClickListener {
            prefrs.putString(Constant.CITY_FROM, binding.input.inputFrom.text.toString()).apply()
        }
        binding.input.inputTo.setOnClickListener {
            dialog = BottomSheetDialog(requireContext())
            dialogTo()
        }
        binding.input.inputFrom.hint = resources.getString(
            R.string.input_from,
            getPrefers.getString(Constant.CITY_FROM, "Москва")
        )
        binding.input.inputTo.text = resources.getString(
            R.string.input_to,
            getPrefers.getString(Constant.CITY_TO, "Турция")!!
        )
        lifecycleScope.launch {
            isOnline()
        }
    }

    private suspend fun isOnline() {
        binding.layoutWifi.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.INVISIBLE
        if (mainViewModel.isOnline(requireContext())) {
            adapter = Adapter(airViewModule.info().offers)
            binding.recyclerView.adapter = adapter
            binding.recyclerView.visibility = View.VISIBLE
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

    private fun dialogTo() {
        val bindingDialog = DialogToBinding.inflate(layoutInflater)
        bindingDialog.inputText.inputTo.hint = resources.getString(
            R.string.input_to,
            getPrefers.getString(Constant.CITY_TO, "Турция")!!
        )
        if (binding.input.inputFrom.text.isEmpty()) bindingDialog.inputText.inputFrom.setText("Москва")
        else bindingDialog.inputText.inputFrom.text = binding.input.inputFrom.text
        bindingDialog.view.setOnClickListener {
            dialog.dismiss()
        }
        bindingDialog.inputText.inputTo.setOnClickListener {
            filledToCity(bindingDialog.inputText.inputTo.text.toString(), bindingDialog)
        }
        clickIcon(bindingDialog)
        bindingDialog.iconRandom.setOnClickListener {
            val city = airViewModule.listCity.random()
            bindingDialog.inputText.inputTo.setText(city)
            filledToCity(city, bindingDialog)
        }
        cityName(bindingDialog)
        dialog.setCancelable(false)
        dialog.setContentView(bindingDialog.root)
        dialog.show()
    }

    private fun cityName(bindingDialog: DialogToBinding) {
        with(bindingDialog) {
            city2.city.text = getString(R.string.city_1)
            city2.photoCity.setImageDrawable(bindingDialog.city2.photoCity.resources.getDrawable(R.drawable.five_city))
            city3.city.text = getString(R.string.city_2)
            city3.photoCity.setImageDrawable(bindingDialog.city3.photoCity.resources.getDrawable(R.drawable.three_city))
            city1.cardCity.setOnClickListener {
                inputText.inputTo.setText(city1.city.text)
                filledToCity(city1.city.text.toString(), this)
            }
            city2.cardCity.setOnClickListener {
                inputText.inputTo.setText(city2.city.text)
                filledToCity(city2.city.text.toString(), this)
            }
            city3.cardCity.setOnClickListener {
                inputText.inputTo.setText(city3.city.text)
                filledToCity(city3.city.text.toString(), this)
            }
        }
    }

    private fun clickIcon(bindingDialog: DialogToBinding) {
        with(bindingDialog) {
            iconRoute.setOnClickListener {
                dialogNameIcon(iconText1.text.toString())
            }
            iconWeekend.setOnClickListener {
                dialogNameIcon(iconText3.text.toString())
            }
            iconHot.setOnClickListener {
                dialogNameIcon(iconText4.text.toString())
            }
        }
    }

    private fun dialogNameIcon(nameIcon: String) {
        dialogIconName = BottomSheetDialog(requireContext())
        val bindingDialog = DialogBackBinding.inflate(layoutInflater)
        bindingDialog.delete.setOnClickListener {
            dialogIconName.dismiss()
        }
        bindingDialog.nameIcon.text = nameIcon
        dialogIconName.setCancelable(false)
        dialogIconName.setContentView(bindingDialog.root)
        dialogIconName.show()
    }

    private fun filledToCity(city: String, bindingDialog: DialogToBinding) {
        val cityFrom = bindingDialog.inputText.inputFrom.text.toString()
        if (city.isEmpty() || cityFrom.isEmpty() || cityFrom[0] == ' ' || city[0] == ' ')
            Toast.makeText(
                requireContext(),
                getString(R.string.error_from_to),
                Toast.LENGTH_SHORT
            ).show()
        else {
            prefrs.putString(Constant.CITY_FROM, cityFrom).apply()
            prefrs.putString(Constant.CITY_TO, city).apply()
            mainViewModel.cityTO = city
            mainViewModel.cityFrom = cityFrom
            dialog.dismiss()
            findNavController().navigate(R.id.action_airTicketsFragment_to_searchFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}