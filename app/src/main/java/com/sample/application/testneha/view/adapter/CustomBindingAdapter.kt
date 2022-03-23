package com.sample.application.testneha.view.adapter

import androidx.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

object CustomBindingAdapter {
    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("profileImage")
    fun loadImage(view: ImageView, profileImage: String) {
        Glide.with(view.context)
            .load(profileImage)
            .into(view)
    }
}
