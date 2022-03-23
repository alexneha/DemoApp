package com.sample.application.testneha.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.application.testneha.service.model.CityFood

class DetailViewModel (private val myApplication: Application,
private val cityfood: CityFood
) : AndroidViewModel(myApplication)
{
    var singlecityFood: MutableLiveData<CityFood> = MutableLiveData()

    init {
        singlecityFood.postValue(cityfood)
    }






    class Factory(private val application: Application,
                  private val cityFood: CityFood) :
        ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(application, cityFood) as T
        }
    }
}