package com.example.playground.socket

import android.util.Log
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UDPClient : Client {

    private lateinit var server: UDPServer
    private var ip: String = ""
    private var port: Int = 0

    override fun setServerInfo(ip: String, port: Int) {
        // check duplicate
        if (this.ip != ip) this.ip = ip
        if (this.port != port) this.port = port

        val ipAddress = InetAddress.getByName(this.ip)
        server = UDPServer(ipAddress, this.port, DatagramSocket())
    }

    override fun sendMessage(data: String) {
        val dp = DatagramPacket(data.toByteArray(), data.length, server.address, server.port)
        val ds = server.socket
        try {
            ds.send(dp)
        }catch (e: IOException){
            Log.e(UDP_TAG, "sendMessage Error!")
        }
    }

    override fun receiveMessage(size: Int): String{
        val receivedMsg = ByteArray(size)
        val dp = DatagramPacket(receivedMsg, receivedMsg.size)
        val ds = server.socket
        var result = ""

        try {
            ds.receive(dp)
            result = String(receivedMsg, 0, dp.length) + ", ${dp.address}, ${dp.port}"
        }catch (e: IOException){
            Log.e(UDP_TAG, "receiveMessage Error!")
        }

        return result
    }

    override fun closeSocket(){
        if (this::server.isInitialized){
            server.socket.close()
        }
    }

    companion object{
        const val UDP_TAG = "UDP"
    }
}