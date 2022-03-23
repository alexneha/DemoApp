package com.sample.application.testneha.service.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "city_food" ,indices = [Index(value = ["name"], unique = true)])

data class CityFood (@PrimaryKey(autoGenerate = true)
                      var id: Int,
                    var name:String?="",
                     var image:String?="",
                     var description:String?="",
                     var type:String?=""):Serializable