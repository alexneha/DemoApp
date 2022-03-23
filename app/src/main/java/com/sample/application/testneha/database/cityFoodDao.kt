package com.sample.application.testneha.database

import androidx.lifecycle.LiveData

import androidx.room.*
import com.sample.application.testneha.service.model.CityFood


@Dao
interface cityFoodDao {

    @Query("SELECT * FROM city_food WHERE type LIKE :type ORDER BY name")
    fun getAllCity(type:String): LiveData<List<CityFood>>



    @Query("SELECT * FROM city_food")
    fun getAll(): LiveData<List<CityFood>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( cityFood: CityFood)


    @Delete
    suspend fun delete(cityFood: CityFood)

    @Update
    suspend fun update( cityFood: CityFood)
}