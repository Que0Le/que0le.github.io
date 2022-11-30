+++
title = "The journey to building a dream device - or a story of a failed project"
date = 2022-11-29T03:06:55+01:00
draft = false
toc = true
+++



## Introduction 

Back in the end of 2021 ...
As many CS guys, I started to play with various technologies and toys. Some were really "for fun", others were for scratching my own itch. 
Some ideas were developed further during a walk or under the shower and lead to the burning of many useless hours on side projects and ideas, with the E-ink-ESP32 project is one of them.

I can remember the initiate idea was: I want to display something (an image, a graph, ...) on a monitor to help me learn vocabulary, idioms and proverbs. 
Well, living abroad kind of destroys your mother-tongue language knowledge, so the wasted time on building such tool could be justified. 

I wanted the device to be portable, simple, and cheap. I just want to setup once, and it should just work without me caring at all - everything that can make a product famous!
This rules out LCD monitors (obvious since that would require a power outlet). Buying ready-to-use products like the digital picture frame is also out of the questions not only because that would be nearly impossible for future configuration and customization, but also there existed no such cheap, battery-powered device. 

So, I am an informatiker. I can build thing! If nothing works for me, I will build one myself. 
From that moment, I started my own journey into the (high level) embedded system, because I know this is the only way to satisfy my requirements: low power, cheap.
This device should also be wireless controlled , that means WIFI/Bluetooth/ZigBee/... The selected hardware is therefor:

- Controller: ESP32: a powerful and versatile controller with excellent support and online documentation. Built-in WIFI and Bluetooth support. I use the !(ESP32 Devkit C)[https://www.az-delivery.de/en/products/esp-32-dev-kit-c-v4], or short _esp32dev_
- Display: E-ink display from Waveshare. Cheap enough and very easy to find.
- Wireless: LoRa. The decision to switch to LoRa (due to power consumption) comes later, which leads to throwing most of the code written for Wifi-based data transmitting.


## Next
Test