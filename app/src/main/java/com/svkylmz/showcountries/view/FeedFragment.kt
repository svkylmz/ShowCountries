package com.svkylmz.showcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.svkylmz.showcountries.R
import com.svkylmz.showcountries.adapter.CountryAdapter
import com.svkylmz.showcountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {
    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        countryList.layoutManager = LinearLayoutManager(context)
        countryList.adapter = countryAdapter

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                countryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })
        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    loadingError.visibility = View.VISIBLE
                } else {
                    loadingError.visibility = View.GONE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    countryLoading.visibility = View.VISIBLE
                    countryList.visibility = View.GONE
                    loadingError.visibility = View.GONE
                } else {
                    countryLoading.visibility = View.GONE
                }
            }
        })
    }
}