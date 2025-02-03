# Open5GS for Kubernetes

The **open5gs_k8s** 6G-Library component deploys a 5G SA Core inside a Kubernetes cluster using the [Helm Chart from Gradiant](https://artifacthub.io/packages/helm/open5gs/open5gs).
Current versions:
- Open5GS v2.7.0
- Used Helm Chart v2.2.2

> [!NOTE]  
> The Kubernetes worker nodes need a CPU architecture with the vmx instructions set.
> In Opennebula, selecting a non-default architecture for the worker templates might be needed.


## What is Open5GS

<p align="center">
  <a href="https://open5gs.org/open5gs/docs/">
    <img src="https://open5gs.org/assets/img/open5gs-logo.png" width="500" title="UERANSIM">
  </a>
</p>

[**Open5GS**](https://github.com/open5gs/open5gs) is an advanced, open-source project designed for building and managing your own NR/LTE mobile network. Whether you’re setting up a private network for testing, research, or deployment, Open5GS offers a robust solution for configuring both 5G (NR) and LTE (evolved) networks (Release-17).
More info in the official documentation: https://open5gs.org/open5gs/docs/

![open5gs_k8s](https://github.com/6G-SANDBOX/6G-Library/blob/assets/images/open5gs_k8s.png)