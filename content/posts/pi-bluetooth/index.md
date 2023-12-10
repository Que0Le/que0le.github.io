---
title: "Pi Bluetooth"
date: 2023-12-10T13:00:54+01:00
draft: true
---


## Prepare

Fix error: `Failed to start discovery: org.bluez.Error.NotReady`
```bash
rfkill list
# 0: hci0: Bluetooth
#         Soft blocked: yes
#         Hard blocked: no
1: phy0: Wireless LAN
        # Soft blocked: no
        # Hard blocked: no

# If soft blocked, then:
rfkill unblock all
```

## Use bluetooth from command line

Scan for ESP32 device:
```bash
pi@raspberrypi:~ $ sudo hcitool dev
Devices:
        hci0    B8:27:EB:0C:A9:AB
pi@raspberrypi:~ $ sudo hcitool scan
Scanning ...
        78:21:84:77:D8:FA       ESP32
```

Scan intensively:
```bash
sudo bluetoothctl
# Agent registered
[bluetooth]# scan on
# Discovery started
# [CHG] Controller B8:27:EB:0C:A9:AB Discovering: yes
# [NEW] Device A4:C1:38:96:85:E0 ihoment_H6179_85E0
# [NEW] Device 5E:1E:86:61:3D:38 5E-1E-86-61-3D-38
# [NEW] Device 6D:B2:AA:7B:59:22 6D-B2-AA-7B-59-22
# [NEW] Device E6:33:2D:13:BD:EA E6-33-2D-13-BD-EA
# [NEW] Device 57:D7:4F:F1:F1:31 57-D7-4F-F1-F1-31
pair 78:21:84:77:D8:FA

[bluetooth]# paired-devices
Device 78:21:84:77:D8:FA ESP32

remove 78:21:84:77:D8:FA
```

```bash
sudo apt-get install bluetooth bluez libbluetooth-dev
sudo python3 -m pip install pybluez
```


## ESP32 listens, Pi pairs simple 
```c
#include "BluetoothSerial.h"

/* Check if Bluetooth configurations are enabled in the SDK */
/* If not, then you have to recompile the SDK */
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

BluetoothSerial SerialBT;

void setup() {
  Serial.begin(115200);
  /* If no name is given, default 'ESP32' is applied */
  /* If you want to give your own name to ESP32 Bluetooth device, then */
  /* specify the name as an argument SerialBT.begin("myESP32Bluetooth"); */
  SerialBT.begin();
  Serial.println("Bluetooth Started! Ready to pair...");
}

void loop() {
  if (Serial.available()) {
    SerialBT.write(Serial.read());
  }
  if (SerialBT.available()) {
    Serial.write(SerialBT.read());
  }
  delay(20);
}
```

```python
import bluetooth
bd_addr = "78:21:84:77:D8:FA"
port = 1
sock=bluetooth.BluetoothSocket( bluetooth.RFCOMM )
sock.connect((bd_addr, port))
sock.send("hello!!")
sock.close()
```

## Pi connects and sends data periodically 

```cpp
#include "BluetoothSerial.h"

/* Check if Bluetooth configurations are enabled in the SDK */
/* If not, then you have to recompile the SDK */
#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error Bluetooth is not enabled! Please run `make menuconfig` to and enable it
#endif

BluetoothSerial SerialBT;
const int BUFFER_SIZE = 184;
char buffer[BUFFER_SIZE];

struct payload {
  int count;
  char message[100];
  int temps[10];
  int humids[10];
} ;

struct payload pl;

void setup() {
  Serial.begin(115200);
  /* If no name is given, default 'ESP32' is applied */
  /* If you want to give your own name to ESP32 Bluetooth device, then */
  /* specify the name as an argument SerialBT.begin("myESP32Bluetooth"); */
  SerialBT.begin();
  Serial.println("Bluetooth Started! Ready to pair...");
}

void loop() {
  if (SerialBT.available()) {
    // Read data into the buffer
    int bytesRead = SerialBT.readBytes((char *) &pl, BUFFER_SIZE);

    // Process the received data (replace this with your actual processing logic)
    if (bytesRead > 0) {
      Serial.printf("Count: %d. Received message: ", pl.count);
      Serial.write(pl.message, 100);
      Serial.println();
      for (int i = 0; i < 10; i++) {
        Serial.printf("%d ", pl.temps[i]);
      }
      Serial.println();
      for (int i = 0; i < 10; i++) {
        Serial.printf("%d ", pl.humids[i]);
      }
      Serial.println();
      
      Serial.println();
    }
  }
  delay(20);
}
```

```python
import bluetooth

import time, random, string, struct, array

## Send data in struct. 
format_string = 'I100s10i10i'
print(f"struct payload size: ${str(struct.calcsize(format_string))}")
integer_value = 42
char_array = b'Hello, World!'
int_array_1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
int_array_2 = [11, 12, 13, 14, 15, 16, 17, 18, 19, 20]

bd_addr = "78:21:84:77:D8:FA"
port = 1

current_count = 0
while True:
    try:
        print("Connecting ...")
        sock=bluetooth.BluetoothSocket( bluetooth.RFCOMM )
        ret = sock.connect((bd_addr, port))
        temps = [current_count] * 10
        humids = [current_count] * 10
        packed_data = struct.pack(
            format_string, current_count, 
            char_array, *temps, *humids
            )
        print("Connected. Sending ...")
        sock.send(packed_data)
        sock.close()
        print("Done. Sleep.")
        current_count += 1
    except Exception as e:
        print("Error: ", e)
    time.sleep(5)

```