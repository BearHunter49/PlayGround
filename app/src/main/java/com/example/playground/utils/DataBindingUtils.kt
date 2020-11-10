package com.example.playground.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.example.playground.R

/**
 * Destination 종류
 */
enum class Destinations{
    WIFI
}


@BindingAdapter("bind:imgRes")
fun imgLoad(imageView: AppCompatImageView, resId: Int){
    imageView.setImageResource(resId)
}

fun findDestination(type: Destinations): Int{
    return when (type){
        Destinations.WIFI -> { R.id.action_home_dest_to_wifiActivity }
    }
}
