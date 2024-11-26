# Open5GS

The **Open5GS** 6G-Library component deploys a 5G SA Core inside a Kubernetes cluster using a Helm Chart.
Current versions:
- Open5GS v2.7.2


> [!NOTE]  
> The VM need a CPU architecture with the vmx instructions set.
> In Opennebula, selecting a non-default architecture for the worker templates might be needed.


This VM supports seperated networks for N2 and N3 but will fall back to use the n2 network for n3 if executed with only 2 networks.
If the VM is deployed with only one network then this mgmt network will be used for n2 and n3.

| network number | used for | notes |
| -------------- | -------- | ----- |
| 1 (eth0)       | management | used as fallback for n2 and n3 if the only interface |
| 2 (eth1)       | N2 (AMF) | used as fallback for N3 if no n3 network specified |
| 3 (eth2)       | N3 (UPF) | |

## What is Open5GS

<p align="center">
  <a href="https://open5gs.org/open5gs/docs/">
    <img src="https://open5gs.org/assets/img/open5gs-logo.png" width="500" title="UERANSIM">
  </a>
</p>

[**Open5GS**](https://github.com/open5gs/open5gs) is an advanced, open-source project designed for building and managing your own NR/LTE mobile network. Whether you’re setting up a private network for testing, research, or deployment, Open5GS offers a robust solution for configuring both 5G (NR) and LTE (evolved) networks (Release-17).
More info in the official documentation: https://open5gs.org/open5gs/docs/

A general-purpose Virtual Machine based on Ubuntu 22.04 LTS with Open5GS and MongoDB installed. 
Through variable "one_open5gs_vm_size", five different types of instances can be deployed
- **extra_large**: 32 GiB of memory, 8 vCPU and 100GiB of storage
- **large**: 16 GiB of memory, 4 vCPU and 50GiB of storage
- **medium**: 8 GiB of memory, 4 vCPU and 25GiB of storage
- **small**: 4 GiB of memory, 2 vCPU and 15GiB of storage
- **very_small**: 2 GiB of memory, 1 vCPU and 10GiB of storage

> [!NOTE] 
> The storage is currently fixed to the size defined in the VM template due to a bug in OpenNebulas Teraform provider. (6 GB in Berlin)