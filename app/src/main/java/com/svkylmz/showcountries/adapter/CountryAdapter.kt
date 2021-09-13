package com.svkylmz.showcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.svkylmz.showcountries.R
import com.svkylmz.showcountries.databinding.CountryListItemBinding
import com.svkylmz.showcountries.model.Country
import com.svkylmz.showcountries.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.country_list_item.view.*

class CountryAdapter(val countryList: ArrayList<Country>): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryItemClickListener {

    class CountryViewHolder(var view: CountryListItemBinding): RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<CountryListItemBinding>(inflater, R.layout.country_list_item, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()  // refresh the adapter
    }

    override fun onCountryClicked(view: View) {
        val uuid = view.countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToDetailFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
}