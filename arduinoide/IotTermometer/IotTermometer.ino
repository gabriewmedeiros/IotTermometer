#include <ESP8266WiFi.h>
#include <PubSubClient.h>
#include "DHT.h"

// Initialize DHT sensor
#define DHTPIN 4
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);
int humidity, temperature;

/* Explicity set your network and MQTT broker credentials. 
   5Ghz network connections don't work with ESP8266 module, 
   so you must connect to a 2.4Ghz Wifi network.
   ssid = The name of your Wifi network;
   password = Your Wifi network password;
   mqttServer =  */
const char* ssid = "SIGALIFE-2.4";
const char* password = "brilho1000";
const char* mqttServer = "test.mosquitto.org";
const int mqttPort = 1883;

// Initialize WifiClient
WiFiClient espClient;
PubSubClient client(espClient);

void setup() {
  Serial.begin(9600);
  dht.begin();
  connectToWifi();
  client.setServer(mqttServer, mqttPort);
}

void loop() {
  if (!client.connected()) {
    reconnectMqtt();
  }

  delay(2000);
  readDhtMeasurements();
  publish();
}

void connectToWifi() {
// We start by connecting to a WiFi network
  Serial.println();
  Serial.println();
  Serial.print("Connecting to: ");
  Serial.println(ssid);

  /* Explicitly set the ESP8266 to be a WiFi-client, otherwise, it by default,
     would try to act as both a client and an access-point and could cause
     network-issues with your other WiFi-devices on your WiFi-network. */
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(5000);
    Serial.println("Connecting to Wifi...");
  }

  Serial.println("");
  Serial.println("WiFi connected!");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void reconnectMqtt() {
   while (!client.connected()) {
    Serial.println("Connecting to MQTT broker...");
   
    if (client.connect("Project Iot Termometer")) {
      Serial.println("Connected to MQTT broker!");
    } else {
      Serial.println("Failed to connect");
      Serial.println(client.state());
      delay(2000);
    }
   }
}

void readDhtMeasurements() {
  // Reading temperature or humidity takes about 250 milliseconds!
  // Sensor readings may also be up to 2 seconds 'old' (its a very slow sensor)
  humidity = dht.readHumidity();
  temperature = dht.readTemperature();

  // Check if any reads failed and exit early (to try again).
  if (isnan(humidity) || isnan(temperature)) {
    Serial.println(F("Failed to read from DHT sensor!"));
    return;
  }

  Serial.println("---------DHT Measurements-----------");
  Serial.print(F("Humidity: "));
  Serial.print(humidity);
  Serial.print(F("%  Temperature: "));
  Serial.print(temperature);
  Serial.println(F("°C "));
}

void publish() {
  // Publish the temperature and humidity values received by the DHT to the MQTT broker
  client.publish("Temperature", String(temperature).c_str(), true);
  client.publish("Humidity", String(humidity).c_str(), true);
  Serial.println("Publishing on topic...");
}
