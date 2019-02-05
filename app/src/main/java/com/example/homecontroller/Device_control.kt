package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Device_control : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device_control)

        val fan_control = findViewById<ToggleButton>(R.id.fan_control)
        val light_control = findViewById<ToggleButton>(R.id.light_control)
        val ip = (this.application as MyApplication).getAddress()
        val exampleRequestQueue = Volley.newRequestQueue(this)


        val exampleStringRequest = JsonArrayRequest(
            Request.Method.GET, (ip + "manage/api/devices/"), null, Response.Listener { response ->
                    try {
                        // Loop through the array elements
                        for (i in 0 until response.length()) {
                            // Get current json object
                            val devices = response.getJSONObject(i)
                            val state = devices.getString("state")

                            if (i == 0) {
                                if (state == "On")
                                    fan_control.isChecked = true
                                else if (state == "Off")
                                    fan_control.isChecked = false
                            }else if (i == 1) {
                                if (state == "On")
                                    light_control.isChecked = true
                                else if (state == "Off")
                                    light_control.isChecked = false
                            }
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                {
                    /* This code is executed if there is an error. */
                })
            exampleRequestQueue.add(exampleStringRequest)


        fan_control.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val fanOnRequest = JsonArrayRequest(
                    Request.Method.GET, (ip + "manage/api/devices/Fan/setstate/On"), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOnRequest)

            } else {
                val fanOffRequest = JsonArrayRequest(
                    Request.Method.GET, (ip + "manage/api/devices/Fan/setstate/Off"), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOffRequest)
            }
        }

        light_control.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val fanOnRequest = JsonArrayRequest(
                    Request.Method.GET, (ip + "manage/api/devices/Light/setstate/On"), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOnRequest)

            } else {
                val fanOffRequest = JsonArrayRequest(
                    Request.Method.GET, (ip + "manage/api/devices/Light/setstate/Off"), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOffRequest)
            }
        }


    }

    override fun onBackPressed() {
        val startMain = Intent(this@Device_control, MainActivity::class.java)
        startActivity(startMain)
        this.finish()
    }
}

