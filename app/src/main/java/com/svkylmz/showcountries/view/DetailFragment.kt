package com.svkylmz.showcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.svkylmz.showcountries.R
import com.svkylmz.showcountries.viewmodel.DetailViewModel
import com.svkylmz.showcountries.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoomDatabase()

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                countryName.text = it.countryName
                countryRegion.text = it.countryRegion
                countryCapital.text = it.countryCapital
                countryCurrency.text = it.countryCurrency
                countryLanguage.text = it.countryLanguage
                //image operations will be here later with glide library
            }
        })
    }
}