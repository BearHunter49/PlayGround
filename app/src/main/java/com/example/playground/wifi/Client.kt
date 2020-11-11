package com.example.playground.wifi

interface Client {
    fun setServerInfo(ip: String, port: Int)
    fun sendMessage(data: String)
    fun receiveMessage(size: Int): String
    fun closeSocket()
}