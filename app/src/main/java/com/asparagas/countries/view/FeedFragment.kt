package com.asparagas.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.asparagas.countries.adapter.CountryAdapter
import com.asparagas.countries.databinding.FragmentFeedBinding
import com.asparagas.countries.service.CountryAPIService
import com.asparagas.countries.viewmodel.FeedViewModel

class FeedFragment : Fragment() {

    private lateinit var binding: FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel=ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.countryList.layoutManager=LinearLayoutManager(context)
        binding.countryList.adapter=countryAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing=false
        }

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.countryList.visibility=View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.countryError.visibility=View.VISIBLE
                    binding.countryList.visibility=View.INVISIBLE
                    binding.progressBarFeedFragment.visibility=View.INVISIBLE
                }else{
                    binding.countryError.visibility=View.INVISIBLE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.progressBarFeedFragment.visibility=View.VISIBLE
                    binding.countryError.visibility=View.INVISIBLE
                    binding.countryList.visibility=View.INVISIBLE
                }else{
                    binding.progressBarFeedFragment.visibility=View.INVISIBLE
                }
            }
        })
    }

}