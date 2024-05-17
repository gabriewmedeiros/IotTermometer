package com.example.iottermometer

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttActionListener
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.IMqttToken
import org.eclipse.paho.client.mqttv3.MqttCallback
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage

class MainActivity : AppCompatActivity() {

    private val mqttHost = "tcp://test.mosquitto.org:1883"
    private var client: MqttAndroidClient? = null
    private var tvTemperature: TextView? = null
    private var tvHumidity: TextView? = null
    private val temperatureTopic = "Temperature"
    private val humidityTopic = "Humidity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clientId = MqttClient.generateClientId()
        client = MqttAndroidClient(this.applicationContext, mqttHost, clientId)
        val options = MqttConnectOptions()

        tvTemperature = findViewById(R.id.tvTemperature)
        tvHumidity = findViewById(R.id.tvHumidity)

        try {
            val token: IMqttToken = client!!.connect(options)
            token.actionCallback = object : IMqttActionListener {
                override fun onSuccess(asyncActionToken: IMqttToken) {
                    Toast.makeText(this@MainActivity, "Connected to MQTT broker", Toast.LENGTH_LONG).show()
                    setSubscription()
                }

                override fun onFailure(asyncActionToken: IMqttToken, exception: Throwable) {
                    Toast.makeText(this@MainActivity, "Disconnected", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e: MqttException) {
            e.printStackTrace()
        }

        client!!.setCallback(object : MqttCallback {
            override fun connectionLost(cause: Throwable) {}

            @Throws(Exception::class)
            override fun messageArrived(topic: String, message: MqttMessage) {
                when (topic) {
                    temperatureTopic -> {
                        tvTemperature?.text = getString(R.string.temperature_value, String(message.payload))
                    }
                    humidityTopic -> {
                        tvHumidity?.text = getString(R.string.humidity_value, String(message.payload))
                    }
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken) {}
        })
    }

    private fun setSubscription() {
        try {
            client!!.subscribe(temperatureTopic, 0)
            client!!.subscribe(humidityTopic, 0)
        } catch (e: MqttException) {
            e.printStackTrace()
        }
    }
}