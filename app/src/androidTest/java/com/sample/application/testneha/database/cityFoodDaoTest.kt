package com.sample.application.testneha.database

import android.content.Context
import androidx.room.Room
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sample.application.testneha.service.model.CityFood
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.test.runBlockingTest

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class cityFoodDaoTest {
    private  lateinit var  database: AppDatabase
    private lateinit var  dao: cityFoodDao

    @Before
    fun setup(){

        val context = ApplicationProvider.getApplicationContext<Context>()
        // initialize the db and dao variable
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = database.cityFoodDao()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = CityFood(45,"name", "cfcfcf", "ffvfv", "city")
        dao.insertAll(shoppingItem)

        val allShoppingItems = dao.getAll()

        assertThat(allShoppingItems)
    }


}