package com.example.test.presentation.fragment.air_tickets.air_tickets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.ItemRecyclerBinding
import com.example.test.entity.info_preview.Offer

class Adapter(
    private val listInfo: List<Offer>
) : RecyclerView.Adapter<CollectionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context))
        return CollectionViewHolder(binding)
    }

    override fun getItemCount(): Int = listInfo.size

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val index = listInfo[position]
        val list = listOf(R.drawable.one, R.drawable.two, R.drawable.three)
        with(holder.binding) {
            price.text = "от ${index.price.value.toString()}Р"
            name.text = index.title
            city.text = index.town
            photo.setImageDrawable(photo.resources.getDrawable(list[position]))
        }
    }

}

class CollectionViewHolder(val binding: ItemRecyclerBinding) :
    RecyclerView.ViewHolder(binding.root)