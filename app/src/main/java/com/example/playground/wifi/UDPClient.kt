package com.example.playground.wifi

import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress

class UDPClient(view: WifiActivity) : Client {

    private lateinit var server: UDPServer

    override fun setServerInfo(address: String, port: Int) {
        val ipAddr = InetAddress.getByName(address)
        server = UDPServer(ipAddr, port, DatagramSocket())
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
            result = String(receivedMsg, 0, dp.length)
        }catch (e: IOException){
            Log.e(UDP_TAG, "receiveMessage Error!")
        }

        return result
    }

    override fun closeSocket(){
        server.socket.close()
    }

    companion object{
        const val UDP_TAG = "UDP"
    }
}