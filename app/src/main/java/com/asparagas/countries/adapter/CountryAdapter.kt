package com.asparagas.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.asparagas.countries.databinding.ItemCountryBinding
import com.asparagas.countries.model.Country
import com.asparagas.countries.util.downloadFromUrl
import com.asparagas.countries.util.placeholderProgressBar
import com.asparagas.countries.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    inner class CountryViewHolder(val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int = countryList.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.textViewNameItem.text = countryList[position].countryName
        holder.binding.textViewRegionItem.text = countryList[position].countryRegion
        holder.binding.imageViewFlagItem.downloadFromUrl(
            countryList[position].countryImageUrl,
            placeholderProgressBar(holder.binding.root.context)
        )

        holder.binding.itemCountryConstLayout.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}