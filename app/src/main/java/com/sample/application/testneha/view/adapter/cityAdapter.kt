package com.sample.application.testneha.view.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.application.testneha.R
import com.sample.application.testneha.databinding.CityFoodItemBinding
import com.sample.application.testneha.service.model.CityFood
import com.sample.application.testneha.view.callback.CityFoodCallback


class cityAdapter (private val CityFoodCallback: CityFoodCallback?) :
    RecyclerView.Adapter<cityAdapter.cityViewHolder>() {

    private var cityFoodLists: List<CityFood>? = null

    fun setFoodlist(cityFoodLists: List<CityFood>) {

        if (this.cityFoodLists == null) {
            this.cityFoodLists = cityFoodLists
            notifyItemRangeInserted(0, cityFoodLists.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return requireNotNull(this@cityAdapter.cityFoodLists).size
                }

                override fun getNewListSize(): Int {
                    return cityFoodLists.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val oldList = this@cityAdapter.cityFoodLists
                    return oldList?.get(oldItemPosition)?.name == cityFoodLists[newItemPosition].name
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = cityFoodLists[newItemPosition]
                    val old = cityFoodLists[oldItemPosition]
                    return project.name == old.name
                }
            })
            this.cityFoodLists = cityFoodLists
            result.dispatchUpdatesTo(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int): cityViewHolder {
        val binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.city_food_item, parent,
            false) as CityFoodItemBinding
        binding.callback = CityFoodCallback
        return cityViewHolder(binding)
    }


    override fun onBindViewHolder(holder: cityViewHolder, position: Int) {
        holder.binding.city = cityFoodLists?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return cityFoodLists?.size ?: 0
    }

    open class cityViewHolder(val binding: CityFoodItemBinding) : RecyclerView.ViewHolder(binding.root)


}
