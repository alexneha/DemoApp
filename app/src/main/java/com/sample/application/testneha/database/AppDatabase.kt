package com.sample.application.testneha.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.application.testneha.service.model.CityFood


@Database(
    entities = [CityFood::class],
    version = 1

)
 abstract class AppDatabase : RoomDatabase(){
    abstract fun cityFoodDao(): cityFoodDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "city_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
