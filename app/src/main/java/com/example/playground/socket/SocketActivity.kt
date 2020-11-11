package com.example.playground.socket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import com.example.playground.databinding.ActivitySocketBinding
import kotlinx.coroutines.*

class SocketActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySocketBinding
    private lateinit var client: UDPClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySocketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        client = UDPClient()

        binding.sendBtn.setOnClickListener {
            val info = getServerInfo()
            client.setServerInfo(info.first, info.second)

            val scope = CoroutineScope(Dispatchers.IO)
            var output = ""
            val job = scope.launch {
                client.sendMessage(binding.inputText.text.toString())
                output = client.receiveMessage(1000)
            }

            scope.launch{
                job.join()
                CoroutineScope(Dispatchers.Main).launch{
                    setOutputText(output)

                }
            }

        }
    }

    private fun getServerInfo(): Pair<String, Int>{
        val ip = binding.inputTextIp.text.toString()
        val port = binding.inputTextPort.text.toString()
        if (!checkIpPort(ip, port)){
            return Pair("", 0)
        }

        return Pair(ip, port.toInt())
    }

    private fun checkIpPort(ip: String, port: String): Boolean {
        var check = true

        if (ip == "") {
            binding.inputTextIp.error = "Empty!"
            check = false
        }
        if (port == "") {
            binding.inputTextPort.error = "Empty!"
            check = false
        }
        return check
    }

    private fun setOutputText(msg: String){
        binding.textOutput.text = msg
    }

    override fun onDestroy() {
        client.closeSocket()
        super.onDestroy()
    }


    override fun finish() {
        super.finish()
        ActivityNavigator.applyPopAnimationsToPendingTransition(this)
    }
}