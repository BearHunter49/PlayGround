package com.example.playground.wifi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import com.example.playground.R
import com.example.playground.databinding.ActivityWifiBinding
import kotlinx.coroutines.*

class WifiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWifiBinding
    private lateinit var client: UDPClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWifiBinding.inflate(layoutInflater)

        setContentView(binding.root)


        client = UDPClient(this)
        client.setServerInfo("192.168.0.24", 5000)

        binding.sendBtn.setOnClickListener {
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