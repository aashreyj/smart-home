package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        val lightOffUrl = "http://" + ip + "/manage/api/devices/Light/setstate/0" //creating the url to be accessed
        val lightOnUrl = "http://" + ip + "/manage/api/devices/Light/setstate/1" //creating the url to be accessed
        val fanOnUrl = "http://" + ip + "/manage/api/devices/Fan/setstate/1" //creating the url to be accessed
        val fanOffUrl = "http://" + ip + "/manage/api/devices/Fan/setstate/0" //creating the url to be accessed
        val url = "http://" + ip + "/manage/api/devices/" //creating the url to be accessed

        val exampleStringRequest = JsonArrayRequest(
            Request.Method.GET, (url), null, Response.Listener { response ->
                    try {
                        // Loop through the array elements
                        for (i in 0 until response.length()) {
                            // Get current json object
                            val devices = response.getJSONObject(i)
                            val state = devices.getString("state")

                            if (i == 0) {
                                if (state == "1")
                                    fan_control.isChecked = true
                                else if (state == "0")
                                    fan_control.isChecked = false
                            }else if (i == 1) {
                                if (state == "1")
                                    light_control.isChecked = true
                                else if (state == "0")
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
                    Request.Method.GET, (fanOnUrl), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOnRequest)

                Toast.makeText(this@Device_control, "Fan was switched On.",Toast.LENGTH_SHORT).show()

            } else if (!isChecked){
                val fanOffRequest = JsonArrayRequest(
                    Request.Method.GET, (fanOffUrl), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOffRequest)
                Toast.makeText(this@Device_control, "Fan was switched Off.",Toast.LENGTH_SHORT).show()
            }
        }

        light_control.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val fanOnRequest = JsonArrayRequest(
                    Request.Method.GET, (lightOnUrl), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOnRequest)
                Toast.makeText(this@Device_control, "Light was switched On.",Toast.LENGTH_SHORT).show()

            } else if(!isChecked){
                val fanOffRequest = JsonArrayRequest(
                    Request.Method.GET, (lightOffUrl), null, Response.Listener { response -> }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                    {
                        /* This code is executed if there is an error. */
                    })
                exampleRequestQueue.add(fanOffRequest)
                Toast.makeText(this@Device_control, "Light was switched Off.",Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onBackPressed() {
        val startMain = Intent(this@Device_control, MainActivity::class.java)
        startActivity(startMain)
        this.finish()
    }
}

