# Descrição
Esse projeto faz a integração de um ESP8266 + protocolo MQTT com uma aplicativo de termômetro Android simples, usando os ambientes de desenvolvimento Android Studio e Arduino IDE.

O módulo ESP8266, conectado ao sensor DHT11, recebe os tópicos de temperatura e umidade coletados no ambiente e os publica no server/broker MQTT test.mosquitto.org, que são consultado pelo aplicativo Android IoT Termometer e exibido no tela para o usuário.

Para a utilização do projeto, é necessário clonar o repositório e conectar o protótipo ESP8266 + DHT11 ao computador através de conexão USB. Abra o arquivo IotTermometer.ino no ArduinoIDE, substitua o valor das váriaveis ssid e password para os da rede em que o computador está conectado e rode o projeto. Após isso, abra o projeto Android no Android Studio e rode usando o emulador ou device pessoal de sua preferência. 

O usuário verá os dados de temperatura e umidade coletados sendo exibidos em uma interface amigável.

### Componentes Usados

- Módulo Wi-fi ESP8266 NodeMCU
- Sensor DHT11
- 3x cabos jumper
- Smartphone Android

### Bibliotecas Usadas

No Arduino Ide:
- DHT sensor library - Adafruit
- Adafruit Unified Sensor - Adafruit
- PubSubclient - Nick O'Leary

No Android Studio:
- Eclipse Paho Android Service (Usamos um fork feito pelo usuário mayzs no github)

### Descrição do Hardware

**Módulo Wi-Fi (MQTT):** 
O ESP8266 é um microcontrolador com capacidade de conexão Wi-Fi integrado. Ele é amplamente utilizado em projetos de IoT (Internet das Coisas) devido à sua versatilidade e baixo custo.

**Sensor de Umidade e Temperatura DHT11**:
O sensor DHT11 foi utilizado para medir a umidade relativa e a temperatura do ambiente. Este sensor digital é amplamente empregado em projetos de baixo custo devido à sua simplicidade e precisão moderada.

**Cabos Jumper Macho-Fêmea**:
Os cabos jumper foram empregados para a interconexão dos componentes eletrônicos na prototipagem.

**Cabo micro USB**:
Cabo usado para ligar e conectador o ESP8266 + DHT ao computador

**Smartphone Android**: Age como atuador do sistema, é onde é buildado o aplicativo.

**ArduinoIDE**: Ambiente de desenvolvimento usado para escrever código em C++ para programação de dispositivos IoT.

**AndroidStudio**: Ambiente de desenvolvimento usado para construir aplicativos para dispositivos com o sistema operacional Android.

### Links de documentação

ESP8266
- https://www.espressif.com/sites/default/files/documentation/0a-esp8266ex_datasheet_en.pdf
- https://www.espressif.com/sites/default/files/documentation/esp8266-technical_reference_en.pdf

DHT
- https://www.mouser.com/datasheet/2/758/DHT11-Technical-Data-Sheet-Translated-Version-1143054.pdf
- https://www.maritex.com.pl/product/attachment/147097/e3ba4b862ce252dac75137c2b42b983f
- https://www.arduino.cc/reference/en/libraries/dht11/

MQTT
- https://mqtt.org/

Android
- https://eclipse.dev/paho/index.php?page=users.php
- https://www.hivemq.com/blog/mqtt-client-library-enyclopedia-paho-android-service/






