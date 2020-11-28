package com.example.playground.utils

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.playground.R
import com.google.android.material.card.MaterialCardView

/**
 * Destination 종류
 */
enum class Destinations{
    SOCKET, DRAG, DATABINDING
}

fun goNextDestination(view: View, type: Destinations){
    view.findNavController().navigate(findDestination(type))
}

fun findDestination(type: Destinations): Int{
    return when (type){
        Destinations.SOCKET -> { R.id.action_home_dest_to_socketActivity }
        Destinations.DRAG -> { R.id.action_home_dest_to_dragAndDropFragment }
        Destinations.DATABINDING -> { R.id.action_home_dest_to_dataBindingFragment }
    }
}