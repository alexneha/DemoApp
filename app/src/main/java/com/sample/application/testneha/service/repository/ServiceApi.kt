package com.sample.application.testneha.service.repository

import com.sample.application.testneha.service.model.CityFood

import retrofit2.Response
import retrofit2.http.GET

interface ServiceApi {


   @GET("e81570e822b273f0a366")
    suspend fun getCity(): Response<List<CityFood>>

    @GET("b4dd0d44343f7eb08f9c")
    suspend fun getFoodList(): Response<List<CityFood>>


  }
