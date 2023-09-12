package com.asparagas.countries.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.asparagas.countries.databinding.FragmentCountryBinding
import com.asparagas.countries.viewmodel.CountryViewModel

class CountryFragment : Fragment() {

    private lateinit var binding: FragmentCountryBinding
    private lateinit var viewModel: CountryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel=ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textViewCountryName.text=it.countryName
                binding.textViewCountryRegion.text=it.countryRegion
                binding.textViewCountryCapital.text=it.countryCapital
                binding.textViewCountryCurrency.text=it.countryCurrency
                binding.textViewCountryLanguage.text=it.countryLanguage
            }
        })
    }
}