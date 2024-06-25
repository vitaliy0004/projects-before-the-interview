package com.example.test.presentation.fragment.air_tickets.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.dto.flight.TicketsOfferDto
import com.example.test.databinding.ItemFlightBinding

class AdapterFlight(
    private val listInfo: List<TicketsOfferDto>,
    private val context: Context
) : RecyclerView.Adapter<FlightViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding)
    }

    override fun getItemCount(): Int = listInfo.size

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val index = listInfo[position]
        val list = listOf(R.color.red, R.color.purple_200, R.color.white)
        var times = ""
        index.timeRange.forEach { times += " $it" }
        with(holder.binding) {
            lens.setColorFilter(ContextCompat.getColor(context, list[position]))
            companyFlight.text = index.title
            price.text = index.price.value.toString()
            textTime.text = if (times.length > 29) times.filterIndexed { index, _ ->
                index < 30
            } + "..."
            else times
        }
    }
}

class FlightViewHolder(val binding: ItemFlightBinding) :
    RecyclerView.ViewHolder(binding.root)