package com.example.homecontroller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import org.json.JSONObject
import com.android.volley.toolbox.Volley
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest

class Sensor_Data : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor__data)

        val temp_val : EditText = findViewById(R.id.therm_input)
        val temp_go : Button = findViewById(R.id.post_therm)

        val light_val : EditText = findViewById(R.id.ldr_input)
        val light_go : Button = findViewById(R.id.post_ldr)

        val RequestQueue = Volley.newRequestQueue(this)

        val ip = (this.application as MyApplication).getAddress()

        val thermUrl = "http://" + ip + "/manage/api/sensors/thermistor/addlog/" //creating the url to be accessed
        val lightUrl = "http://" + ip + "/manage/api/sensors/light_sensor/addlog/" //creating the url to be accessed
        temp_go.setOnClickListener {
            var tempPost = JSONObject()
            tempPost.put("value",temp_val.text.toString())
            val tempRequest = JsonObjectRequest(
                Request.Method.POST, (thermUrl), tempPost, Response.Listener { response -> }, Response.ErrorListener {})

            RequestQueue.add(tempRequest)
            Toast.makeText(this@Sensor_Data, "Thermistor data was Posted.", Toast.LENGTH_SHORT).show()

        }

        light_go.setOnClickListener {
            var lightPost = JSONObject()
            lightPost.put("value",light_val.text.toString())
            val lightRequest = JsonObjectRequest(
                Request.Method.POST, (lightUrl), lightPost, Response.Listener { response -> }, Response.ErrorListener {})

            RequestQueue.add(lightRequest)
            Toast.makeText(this@Sensor_Data, "Light data was Posted.",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        val startMain = Intent(this@Sensor_Data, MainActivity::class.java)
        startActivity(startMain)
        this.finish()
    }
}

