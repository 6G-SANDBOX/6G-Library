# Qosium Probe

## Overview

This component is the key enabler of Qosium measurements into your trial network. Qosium is a real-time passive QoS/QoE measurement and monitoring solution, disclosing what is happening in your network with unprecedented accuracy. 

Qosium Probe, the measurement agent, executes most of the measurement functionalities. The locations of Probes determine the measurement topology. As Qosium is a passive measurement solution, you need to place Qosium Probe into such place where the network traffic you want to measure is visible in any of its network interfaces. You only need a single Probe per device, capable of handling multiple simultaneous independent measurements. 

To reveal all the available QoS statistics, you need to conduct measurements between two Qosium Probes deployed in your setup. You either can deploy two Qosium Probe components into your experimental network, or have Probe running on a physical device(s). You can also perform measurements at a single point with a limited results set. The measurements are operated by a controller software like Qosium Scope for manual measurements or Qosium Scopemon for automatic monitoring.

If you are heading for a monitoring setup, a central place for storing and visualizing the results can become useful. You can get the results into your own software, and even control measurements, by using Kaitotek's API. Another option is to deploy Kaitotek's Qosium Storage results solution, providing you with QoS situational awareness over the whole network. Qosium Storage can also be used to control measurements. Qosium Storage can be operated directly via its web UI or remotely over REST API.

## Integration

The Virtual Machine including Qosium Probe is, currently, deployed to OpenNebula manually. Please be in touch with [Kaitotek](https://www.kaitotek.com/forms/support-request) for more information and the VM image.

## Configuration

When you deploy the Qosium Probe component to a trial network, you can configure the following optional parameters:
 
- Qosium Probe identifier (Service ID): *one\_qosium\_probe\_service_id*
- NTP server against which the virtual machine's clock is synchronized: *one\_qosium\_probe\_ntp\_server\_address*

In addition, you can install Qosium Probes manually, e.g., to physical devices that reside out of the 6G-SANDBOX platform, but are connected to it. 

Once you have Probes in place, install the measurement controllers of your choice, and/or the results solution, if not already installed. 

NOTE: this component involves a private-licensed image. Thus, for support and Qosium installers, please contact [Kaitotek](https://www.kaitotek.com/forms/support-request).

## Additional information

You can find general information about Qosium [here](https://www.kaitotek.com/qosium).

Learn the details of Qosium and parameterization at:  [Qosium Docs](https://www.kaitotek.com/resources/documentation)

<p align="center">
<img src="https://www.kaitotek.com/media/pages/home/aea8d8eca3-1627636784/logo_qosium.svg" width="300">

Kaitotek Oy, 2025.
