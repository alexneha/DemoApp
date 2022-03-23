package com.sample.application.testneha.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sample.application.testneha.R
import com.sample.application.testneha.databinding.FragmentCityLayoutBinding
import com.sample.application.testneha.service.model.CityFood
import com.sample.application.testneha.view.adapter.cityAdapter

import com.sample.application.testneha.view.callback.CityFoodCallback
import com.sample.application.testneha.viewModel.cityFoodViewModel

class cityFoodFragment : Fragment() {


   private val viewModel by lazy {
       ViewModelProvider(this).get(cityFoodViewModel::class.java)
    }
    private lateinit var binding: FragmentCityLayoutBinding

    private val cityAdapter: cityAdapter = cityAdapter(object : CityFoodCallback {


        override fun onClickcity(project: CityFood) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(project)
            }
        }
    })


    private val foodadapter: cityAdapter = cityAdapter(object : CityFoodCallback {


        override fun onClickcity(project: CityFood) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                (activity as MainActivity).show(project)
            }
        }
    })




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_city_layout,
            container,
            false
        ) //dataBinding
        binding.apply {
            projectList.adapter = cityAdapter
            isLoading = true
        }

        binding.apply {
            foodList.adapter = foodadapter
        }




        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(viewModel.repository.checkForInternet(context!!)){
    viewModel.cityListLiveData.observe(viewLifecycleOwner, Observer { projects ->
        projects?.let {
            binding.isLoading = false
            cityAdapter.setFoodlist(it)
        }
    })
    viewModel.foodListLiveData.observe(viewLifecycleOwner, Observer { foods ->
        foods?.let {
            binding.isLoading = false
            foodadapter.setFoodlist(it)
        }
    })

}else{
            viewModel.citylist.observe(viewLifecycleOwner, Observer { user ->
                Log.e("___iii___",user.size.toString())
                binding.isLoading = false
                cityAdapter.setFoodlist(user)
            })

            viewModel.foodlist.observe(viewLifecycleOwner, Observer { user ->
                Log.e("___iii___",user.size.toString())
                binding.isLoading = false
                foodadapter.setFoodlist(user)
            })

        }





    }
}