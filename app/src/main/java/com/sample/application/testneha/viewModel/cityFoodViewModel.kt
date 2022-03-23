package com.sample.application.testneha.viewModel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.sample.application.testneha.database.AppDatabase
import com.sample.application.testneha.database.cityFoodRepo
import com.sample.application.testneha.service.model.CityFood
import com.sample.application.testneha.service.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class cityFoodViewModel (application: Application) : AndroidViewModel(application) {

     val repository = ProjectRepository.instance
    var cityListLiveData: MutableLiveData<List<CityFood>> = MutableLiveData()
    var foodListLiveData: MutableLiveData<List<CityFood>> = MutableLiveData()

    private val cityFoodRepository: cityFoodRepo
    val citylist: LiveData<List<CityFood>>
    val foodlist: LiveData<List<CityFood>>



    init {
        val userDao = AppDatabase.getDatabase(application).cityFoodDao()
        cityFoodRepository= cityFoodRepo(userDao)
        citylist=  cityFoodRepository.getAllFood("city")
        foodlist=  cityFoodRepository.getAllFood("food")

       loadcity()
    }

    private fun loadcity() = viewModelScope.launch { //onCleared()
        try {

            if (repository.checkForInternet(getApplication<Application>().applicationContext)) {
                val response = repository.getcityList()
                val responsefood = repository.getFoodList()

                if (response.isSuccessful && responsefood.isSuccessful) {

                    response.body()?.map { it.type="city" }
                    responsefood.body()?.map { it.type="food" }
                    response.body()?.forEach(){

                       /* DatabaseClient().getDatabase(getApplication<Application>().applicationContext)
                            .cityFoodDao().insertAll(it);
*/

                        it.type="city"
                        Log.e("insert>",it.name.toString())
                        cityFoodRepository.insertEvent(it)

                    }
                    responsefood.body()?.forEach(){
it.type="food"
                        cityFoodRepository.insertEvent(it)

                    }



                    cityListLiveData.postValue(response.body())
                    sortAlphabetically(response.body()!!)
                    foodListLiveData.postValue(responsefood.body())
                    sortAlphabetically(responsefood.body()!!)


                }
            }else{
                cityListLiveData.postValue(citylist.value)
                foodListLiveData.postValue(foodlist.value)
                Toast.makeText(getApplication<Application>().applicationContext,"Disconnected==>",Toast.LENGTH_LONG).show()

            }
        }
        catch (e: Exception) {
            e.stackTrace
        }
    }

    fun sortAlphabetically(arrayList: List< CityFood >): MutableList<CityFood >{
        var returnList: MutableList< CityFood > = arrayListOf()
        var list = arrayList as MutableList< CityFood >
        list.sortWith(Comparator { o1: CityFood, o2: CityFood ->
            o1.name!!.compareTo(o2.name!!)
        })
        returnList = list
        return returnList
    }




}