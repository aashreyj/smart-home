package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var device_show = findViewById<Button>(R.id.bDev_show)
        var device_control = findViewById<Button>(R.id.bDev_control)
        var device_log = findViewById<Button>(R.id.bDev_log)
        var sensor_log = findViewById<Button>(R.id.bSen_log)
        var sensor_data = findViewById<Button>(R.id.bSensor)


        device_show.setOnClickListener {
            val startShowDevice = Intent(this@MainActivity, show_devices::class.java)
            startActivity(startShowDevice)
            this.finish()
        }
    }
}