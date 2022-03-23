package com.sample.application.testneha.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sample.application.testneha.R
import com.sample.application.testneha.databinding.FragmentDetailCityFoodBinding
import com.sample.application.testneha.service.model.CityFood
import com.sample.application.testneha.viewModel.DetailViewModel


class DetailFragment  : Fragment() {

    companion object {
        private const val KEY_DETAIL = "singleCityFood"

        fun SingleDetail(cityFood: CityFood) = DetailFragment().apply {
            arguments = Bundle().apply { putSerializable(KEY_DETAIL,cityFood) }
        }
    }

    private val singleDetail by lazy {
        requireNotNull(
            arguments?.getSerializable(KEY_DETAIL) as CityFood
        ) {
            var food=CityFood(-1,"","","","")
            food
        }
    }

    private val viewModel by lazy {
        ViewModelProvider(this,DetailViewModel.Factory(requireActivity().application,singleDetail)).get(DetailViewModel::class.java)
    }

    private lateinit var binding: FragmentDetailCityFoodBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_city_food, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
        detail=viewModel


        }

        viewModel.singlecityFood.observe(viewLifecycleOwner, Observer { cityfood ->
            cityfood?.let {

                Log.e("it observe click_____>",it.name+"<---image---->"+it.image)
                binding.name.text=it.name
                binding.projectDesc.text=it.description
                Glide.with(view.context)
                    .load(it.image)
                    .into(binding.imageView)
            }
        })
    }

}