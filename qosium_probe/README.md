# qosium_probe

A VM machine with Kaitotek's Qosium Probe measurement agent to deploy a measurement point into the trial network.

You can configure:

- Qosium Probe service ID number, a Probe identifier: *one\_qosium\_probe\_service_id*
- NTP server against which the VM's clock is synchronized: *one\_qosium\_probe\_ntp\_server\_address*

Once the component is deployed, you can use Qosium measurment controllers for conducting passive QoS measurements. Those are, for example, [Qosium Scope](https://www.kaitotek.com/resources/documentation/scope) and [Qosium Scopemon](https://www.kaitotek.com/resources/documentation/scopemon). Please note that for QoS results, you need to have two Qosium Probes deployed in your setup for a two-point measurement (either virtually or physically installed into network locations you want to carry out measurements)

Take a look at Qosium documentation for more information: [Qosium Docs](https://www.kaitotek.com/resources/documentation)

<p align="center">
<img src="https://www.kaitotek.com/media/pages/home/aea8d8eca3-1627636784/logo_qosium.svg" width="300">