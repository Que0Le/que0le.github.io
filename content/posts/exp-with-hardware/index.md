---
title: "Exp With Hardware"
date: 2023-12-11T21:58:34+01:00
draft: false
---



## ESP32
- Sometimes, using different adapter (i.e, USB C-A adapter) will report different name for the plugged device. This is easy to observe by plugging a ESP32 into a normal adapter vs Apple USB C to HDMI and USB A adapter.
- Opening multiple PlatformIO windows for the same hardware might be tricky if 2 windows want to access the same device.

## LoRa
- There are problems caused by interference and sharing of spectrum:

<div style="align: left; text-align:center;">
    <img src="lora_data_err.PNG" alt="" width="90%" />
    <span style="display:block;"></span>
</div>