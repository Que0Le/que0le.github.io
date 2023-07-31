---
title: "Wireguard How to setup and config"
date: "2023-07-30T00:00:00.000Z"
description: "Setup wireguard can be hard sometimes ..."
---

## Note
- `/32` and `/24` are very confusing and are often the main cause why the tunnel doesn't work (usually: can't ping).
- This post uses no stand alone key files. All pub and private key contents are included in the config files.
- Non-automatic steps.
- This post is based on my experience through different setups. 
The following steps are suitable for my setup. 
Other blogs might offer way more sophisticated guide and configuration, for example this post: [https://try.popho.be/wg.html](https://try.popho.be/wg.html).

## TODO:
- [ ] IPv6 support
- [ ] Multiple IPs, multiple subnets

## Setup Wireguard to create tunnels between server and clients:
After connected, each client treats this tunnel as if it is another network interface.

Clients can ping and talk to each other through server if needed.

Windows and Linux client work great. macOS clients however automatically routes all traffic through the tunnel by default which leads to no internet on the macOS client. Routing rules need to be changed!

## General Setup
This is for Ubuntu/Debian, as Windows has fantastic client with GUI:
```bash
sudo apt install wireguard
sudo nano /etc/wireguard/wg0.conf # copy config as suggested below
sudo chown -R root:root /etc/wireguard/
sudo chmod -R og-rwx /etc/wireguard/*

sudo systemctl enable wg-quick@wg0.service
## start or stop the service 
#sudo systemctl start wg-quick@wg0.service
#sudo systemctl stop wg-quick@wg0.service
```

After that, start the tunnel:
```bash
sudo wg-quick down wg0 && sudo wg-quick up wg0
```

__Server config__

```bash
[Interface]
Address = 10.100.100.1/24
ListenPort = 44444
PrivateKey = ___SERVER_PRIVATE_KEY_HERE________________
# public key note:
# ___SERVER_PUBLICKEY_HERE_SO_I_DONT_HAVE_TO_REGENERATE_IT____


### COMMENT OUT THESE 2 LINES
### IF YOU DONT WANT CLIENTS TO BE ABLE TO TALK TO EACH OTHER.
### THIS FORWARDING NEEDS sysctl -w net.ipv4.ip_forward=1
PostUp = iptables -I FORWARD -i wg0 -o wg0 -j ACCEPT
PostDown = iptables -D FORWARD -i wg0 -o wg0 -j ACCEPT

[Peer]
PublicKey = ___CLIENT_2_PUBLIC_KEY______________________
AllowedIPs = 10.100.100.2/32

[Peer]
PublicKey = ___CLIENT_3_PUBLIC_KEY______________________
AllowedIPs = 10.100.100.3/32
```


__Client config__

This config is for client with tunnel IP addresss `10.100.100.2`.
```bash
[Interface]
PrivateKey = ___CLIENT_2_PRIVATE_KEY_____________________
Address = 10.100.100.2/32


[Peer]
PublicKey = ___SERVER_PUBLIC_KEY_HERE_____________________
AllowedIPs = 10.100.100.1/24
Endpoint = server.real.public.ipv4:44444
PersistentKeepalive = 30
```


__TEST__

Now try to ping server or other clients from a client.
