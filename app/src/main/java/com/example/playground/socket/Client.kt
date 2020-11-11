package com.example.playground.socket

interface Client {
    fun setServerInfo(ip: String, port: Int)
    fun sendMessage(data: String)
    fun receiveMessage(size: Int): String
    fun closeSocket()
}