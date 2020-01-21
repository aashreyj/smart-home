package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

class Show_deviceLog : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_device_log)

        var respond = findViewById<TextView>(R.id.response_text)
        var label = findViewById<TextView>(R.id.header)
        val ip = (this.application as MyApplication).getAddress()

        val exampleRequestQueue = Volley.newRequestQueue(this)

        val url = "http://" + ip + "/manage/api/devicelogs/" //creating the url to be accessed
            respond.text = ""

            val exampleStringRequest = JsonArrayRequest(
                Request.Method.GET, url, null, Response.Listener { response ->
                    try {
                        // Loop through the array elements
                        for (i in 0 until response.length()) {
                            // Get current json object
                            val devices = response.getJSONObject(i)

                            val device = devices.getString("device")
                            val datetime = devices.getString("datetime")
                            val state = devices.getString("state")

                            label.text = "The following Device Logs were found online:"
                            respond.append("Device Name: $device\nTime Log: $datetime\nState Change: $state\n\n\n")

                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener //Create an error listener to handle errors appropriately.
                {
                    /* This code is executed if there is an error. */
                    respond.text = ""
                    respond.append("There was an error.")
                })

            exampleRequestQueue.add(exampleStringRequest)

    }

    override fun onBackPressed() {
        var backToMain = Intent(this@Show_deviceLog, MainActivity::class.java)
        startActivity(backToMain)
        this.finish()
    }
}

