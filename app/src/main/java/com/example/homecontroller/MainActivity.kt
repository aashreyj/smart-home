package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.app.Application
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var device_show = findViewById<Button>(R.id.bDev_show)
        var device_control = findViewById<Button>(R.id.bDev_control)
        var device_log = findViewById<Button>(R.id.bDev_log)
        val sensorData = findViewById<Button>(R.id.bSensor)
        val sensorLog = findViewById<Button>(R.id.bSen_log)
        val ip_text = findViewById<EditText>(R.id.ip_server)

        ip_text.append((this.application as MyApplication).getAddress())

        device_show.setOnClickListener {
            (this.application as MyApplication).setAddress(ip_text.text.toString())
            val startShowDevice = Intent(this@MainActivity, show_devices::class.java)
            startActivity(startShowDevice)
            this.finish()
        }

        device_log.setOnClickListener {
            (this.application as MyApplication).setAddress(ip_text.text.toString())
            val startDeviceLog = Intent(this@MainActivity, Show_deviceLog::class.java)
            startActivity(startDeviceLog)
            this.finish()
        }

        device_control.setOnClickListener {
            (this.application as MyApplication).setAddress(ip_text.text.toString())
            val startDeviceControl = Intent(this@MainActivity, Device_control::class.java)
            startActivity(startDeviceControl)
            this.finish()
        }

        sensorLog.setOnClickListener {
            (this.application as MyApplication).setAddress(ip_text.text.toString())
            val startSensorLog = Intent(this@MainActivity, Sensor_Log::class.java)
            startActivity(startSensorLog)
            this.finish()
        }

        sensorData.setOnClickListener {
            (this.application as MyApplication).setAddress(ip_text.text.toString())
            val startSensorData = Intent(this@MainActivity, Sensor_Data::class.java)
            startActivity(startSensorData)
            this.finish()
        }
    }
}

class MyApplication : Application() {

    var serverAddress: String? = ""

    fun setAddress(address: String) {
        serverAddress = address
    }
    fun getAddress(): String {
        return serverAddress.toString()
    }
}