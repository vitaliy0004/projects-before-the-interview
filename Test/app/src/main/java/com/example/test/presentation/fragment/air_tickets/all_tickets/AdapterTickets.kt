package com.example.test.presentation.fragment.air_tickets.all_tickets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.data.dto.tickets.TicketDto
import com.example.test.databinding.ItemTicketsBinding

class AdapterTickets(
    private val listInfo: List<TicketDto>
) : RecyclerView.Adapter<TicketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketViewHolder {
        val binding = ItemTicketsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TicketViewHolder(binding)
    }

    override fun getItemCount(): Int = listInfo.size

    private fun timFormat(data: String): String {
        return data.filterIndexed { index, c -> index in 11..15 }
    }

    private fun fromStringToInt(time: String): Double {
        var hours = ""
        var minut = ""
        time.forEachIndexed { index, c ->
            when (index) {
                0 -> {
                    hours = if (c == '0') ""
                    else c.toString()
                }

                1 -> {
                    hours += c
                }

                3 -> {
                    minut = if (c == '0') ""
                    else c.toString()
                }

                4 -> {
                    minut += c
                }
            }
        }
        return hours.toDouble() * 60.0 + minut.toDouble()

    }

    private fun range(timeFrom: String, timeTo: String): String {
        val to = fromStringToInt(timeTo)
        val from = fromStringToInt(timeFrom)
        val time = if (from > to) (to + 24 * 60 - from) / 60
        else (to - from) / 60
        return time.toString().filterIndexed { index, c ->
            index < 3
        }

    }

    override fun onBindViewHolder(holder: TicketViewHolder, position: Int) {
        val index = listInfo[position]
        val plusInfoText = if (index.hasTransfer) ""
        else "/Без пересадок"
        val timeTo = timFormat(index.arrival.date)
        val timeFrom = timFormat(index.departure.date)
        with(holder.binding) {
            if (index.badge == null) plusInfo.visibility = View.GONE
            else plusInfo.text = index.badge
            textFullInfo.text = "${timeFrom}-${timeTo} " +
                    "${range(timeFrom, timeTo)}ч в пути$plusInfoText"
            textLocation.text = "${index.departure.airport}    ${index.arrival.airport}"
            price.text = index.price.value.toString()
        }
    }
}

class TicketViewHolder(val binding: ItemTicketsBinding) :
    RecyclerView.ViewHolder(binding.root)