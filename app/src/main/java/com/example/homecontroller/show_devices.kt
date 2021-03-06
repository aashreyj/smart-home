package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class show_devices : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_devices)


        val respond = findViewById<TextView>(R.id.response_text)
        val label = findViewById<TextView>(R.id.header)
        val ip = (this.application as MyApplication).getAddress()

        val exampleRequestQueue = Volley.newRequestQueue(this)


        val url = "http://" + ip + "/manage/api/devices/" //creating the url to be accessed
        Toast.makeText(this@show_devices, url, Toast.LENGTH_SHORT).show()
            respond.text = ""

            val exampleStringRequest = JsonArrayRequest(
                Request.Method.GET, url, null, Response.Listener { response ->
                    try {
                        // Loop through the array elements
                        for (i in 0 until response.length()) {
                            // Get current json object
                            val devices = response.getJSONObject(i)

                            val name = devices.getString("name")
                            val state = devices.getString("state")
                            val type = devices.getString("type")
                            val location = devices.getString("location")

                            label.text = "The following devices were found online: "
                            respond.append("Name: $name\nState: $state\nType: $type\nLocation: $location\n\n\n")
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
        val backToMain = Intent(this@show_devices, MainActivity::class.java)
        startActivity(backToMain)
        this.finish()
    }
}
