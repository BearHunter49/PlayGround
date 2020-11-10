package com.example.playground.Utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("bind:imgRes")
fun imgLoad(imageView: AppCompatImageView, resId: Int){
    imageView.setImageResource(resId)
}
