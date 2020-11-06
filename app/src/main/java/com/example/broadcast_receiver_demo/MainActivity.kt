package com.example.broadcast_receiver_demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.BatteryManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.ConnectivityManagerCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent = IntentFilter(Intent.ACTION_BATTERY_CHANGED)

        this.registerReceiver(myBroadcast,intent)
    }

    private var myBroadcast = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            var string = StringBuilder()
            val battery = intent?.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
            string.append("$battery %")

            textView.text = string
        }
    }

    override fun onDestroy() {
        unregisterReceiver(myBroadcast)
        super.onDestroy()
    }
}