package com.example.playground.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("imgRes")
fun imgLoad(imageView: AppCompatImageView, resId: Int){
    imageView.setImageResource(resId)
}

