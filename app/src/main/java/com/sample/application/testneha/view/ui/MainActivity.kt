package com.sample.application.testneha.view.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.sample.application.testneha.R
import com.sample.application.testneha.service.model.CityFood


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar


        setSupportActionBar(toolbar);

        // add back arrow to toolbar


        if (savedInstanceState == null) {
            val fragment = cityFoodFragment() //一覧のFragment
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment, "List")
                    .commit()
        }
    }



    fun show(cityfood: CityFood) {

        Log.e("click_____>",cityfood.name+"<---image---->"+cityfood.image)

        if (getSupportActionBar() != null){
            getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
            getSupportActionBar()?.setDisplayShowHomeEnabled(true);
        }
        val projectFragment = DetailFragment.SingleDetail(cityfood) //詳細のFragment
        supportFragmentManager
            .beginTransaction()
            .addToBackStack("city")
            .replace(R.id.fragment_container, projectFragment, null)
            .commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // handle arrow click here
        if (item.getItemId() === android.R.id.home) {
            finish() // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item)
    }
}
