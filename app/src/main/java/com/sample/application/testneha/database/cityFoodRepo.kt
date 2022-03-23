package com.sample.application.testneha.database

import androidx.lifecycle.LiveData
import com.sample.application.testneha.service.model.CityFood

class cityFoodRepo(private val cityfooddao: cityFoodDao) {

    // get all the events
    val readAllData: LiveData<List<CityFood>> = cityfooddao.getAll()



    fun getAllFood(string: String): LiveData<List<CityFood>> = cityfooddao.getAllCity(string)


    fun getAll(): LiveData<List<CityFood>> = cityfooddao.getAll()

    // adds an event to our database.
    suspend fun insertEvent(cityfood: CityFood) {
        cityfooddao.insertAll(cityfood)
    }

    // deletes an event from database.
    suspend fun deleteEvent(event: CityFood) {
        cityfooddao.delete(event)
    }

    // updates an event from database.
    suspend fun updateEvent(event: CityFood) {
        cityfooddao.update(event)
    }


}