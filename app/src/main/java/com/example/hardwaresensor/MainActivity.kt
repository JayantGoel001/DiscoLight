package com.example.hardwaresensor

import android.content.Context
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sm=getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor=sm.getSensorList(Sensor.TYPE_ALL)
        sensor.forEach{
           Log.i("Sensors","${it.name} ++ ${it.vendor}")
        }
        val accSensor=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sensorListener=object :SensorEventListener
        {
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }

            override fun onSensorChanged(event: SensorEvent) {
                Log.i("Sensors","x=${event.values[0]},y=${event.values[1]},z=${event.values[2]}")
                val ax=event.values[0].toInt()
                val ay=event.values[1].toInt()
                val az=event.values[2].toInt()
                lay.setBackgroundColor(Color.rgb(
                    (((ax+10)/24.0)*255).toInt(),
                    (((ay+10)/24.0)*255).toInt(),
                    (((az+10)/24.0)*255).toInt()
                ))
            }
        }
        sm.registerListener(sensorListener,accSensor,1000*60)
    }
}
