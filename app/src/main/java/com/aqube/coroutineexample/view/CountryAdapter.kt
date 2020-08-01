package com.aqube.coroutineexample.view

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aqube.coroutineexample.R
import com.aqube.coroutineexample.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val interaction: Interaction? = null) :
    ListAdapter<Country, CountryAdapter.CountryViewHolder>(CountryDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false),
        interaction
    )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<Country>) {
        submitList(data.toMutableList())
    }

    inner class CountryViewHolder(itemView: View, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(itemView), OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val clicked = getItem(adapterPosition)
            interaction?.countrySelected(clicked)
        }

        fun bind(item: Country) = with(itemView) {
            textViewCountry.text = item.countryName
            textViewCapital.text = item.capital
        }
    }

    interface Interaction {
        fun countrySelected(model: Country)
    }

    private class CountryDC : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country) =
            oldItem.countryName == newItem.countryName

        override fun areContentsTheSame(oldItem: Country, newItem: Country) = oldItem == newItem
    }
}