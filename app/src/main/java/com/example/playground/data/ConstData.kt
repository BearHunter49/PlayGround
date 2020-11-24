package com.example.playground.data

import com.example.playground.R
import com.example.playground.utils.Destinations

object ConstData {
    val toyListDummyData = listOf<Toy>(
        Toy("Socket", R.drawable.ic_local_shipping_black_24dp, Destinations.SOCKET),
        Toy("DragAndDrop", R.drawable.ic_drag, Destinations.DRAG)
    )

}