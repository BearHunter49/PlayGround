package com.example.playground.socket

import java.net.DatagramSocket
import java.net.InetAddress

data class UDPServer(val address: InetAddress, val port: Int, val socket: DatagramSocket)