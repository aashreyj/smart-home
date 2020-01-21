package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import java.sql.Timestamp

class Sensor_Log : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor__log)

        var respond = findViewById<TextView>(R.id.response)
        var label = findViewById<TextView>(R.id.header)
        val ip = (this.application as MyApplication).getAddress()

        val exampleRequestQueue = Volley.newRequestQueue(this)


        val url = "http://" + ip + "/manage/api/sensorlogs/" //creating the url to be accessed
        respond.text = ""

        val exampleStringRequest = JsonArrayRequest(
            Request.Method.GET, url, null, Listener { response ->
                try {
                    // Loop through the array elements
                    for (i in 0 until response.length()) {
                        // Get current json object
                        val sensor = response.getJSONObject(i)

                        val name = sensor.getString("sensor")
                        val datetime = sensor.getString("datetime")
                        val value = sensor.getString("value")

                        label.text = "The following Sensor Logs were found online:"
                        respond.append("Sensor Name: $name\nTime Log: $datetime}\nValue: $value\n\n\n")

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
        var backToMain = Intent(this@Sensor_Log, MainActivity::class.java)
        startActivity(backToMain)
        this.finish()
    }
}

