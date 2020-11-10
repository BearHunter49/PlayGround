package com.example.playground.data

import androidx.annotation.DrawableRes
import com.example.playground.utils.Destinations

data class Toy(val name: String, @DrawableRes val image: Int, val type: Destinations)