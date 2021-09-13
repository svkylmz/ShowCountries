package com.svkylmz.showcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.svkylmz.showcountries.R
import com.svkylmz.showcountries.databinding.FragmentDetailBinding
import com.svkylmz.showcountries.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var countryUuid = 0
    private lateinit var dataBinding: FragmentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = DetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.getDataFromRoomDatabase(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                dataBinding.selectedCountry = it
            }
        })
    }
}