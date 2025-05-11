# Open5GS as a standalone Virtual Machine

The **open5gs_vm** 6G-Library component deploys a 5G SA Core inside an Ubuntu VM using scripts.
Current versions:
- Open5GS v2.7.2

> [!NOTE]  
> The VM needs a CPU architecture with the vmx instructions set.
> In Opennebula, site admin should select a non-default architecture for the VM template, or use host-passthrough.

This VM supports separated networks for N2 and N3 but will fallback to use the n2 network for n3 if executed with only 2 networks.
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


## 🚀 Deployment Details

- **Hypervisor**: `OpenNebula`
- **OS**: Ubuntu 22.04 LTS
- **Pre-installed**:
  - Open5GS v2.7.2
  - MongoDB
- **Appliance**: [Open5GS Service Appliance](https://marketplace.mobilesandbox.cloud:9443/appliance/service_openFgs)

---

## 📥 Input Variables

| Variable                       | Description                                                                                         | Type                    | Required |
|--------------------------------|-----------------------------------------------------------------------------------------------------|-------------------------|----------|
| `one_open5gs_vm_external_vnet` | Virtual Network for VM management. Default: `tn_vxlan`                                              | `tn_vxlan or vnet`      | No       |
| `one_open5gs_vm_internal_vnet` | List of up to two VNets for N2 and N3 traffic.                                                     | `list[tn_vxlan or vnet]`| No       |
| `one_open5gs_vm_size`          | VM hardware profile. See instance types below.                                                     | `str`                   | Yes      |
| `one_open5gs_vm_amf_n2_ip`     | IP address of AMF (N2 interface).                                                                  | `str`                   | No       |
| `one_open5gs_vm_upf_n3_ip`     | IP address of UPF (N3 interface).                                                                  | `str`                   | No       |
| `one_open5gs_vm_ue_count`      | Number of UEs to be provisioned.                                                                   | `int`                   | No       |
| `one_open5gs_vm_tac`           | Tracking Area Code (TAC).                                                                          | `int`                   | No       |
| `one_open5gs_vm_mcc`           | Mobile Country Code. Must be 3 digits in quotes.                                                    | `str`                   | No       |
| `one_open5gs_vm_mnc`           | Mobile Network Code. Must be 2–3 digits in quotes.                                                  | `str`                   | No       |
| `one_open5gs_vm_msin`          | Mobile Subscriber ID Number. Fills out the IMSI.                                                    | `str`                   | No       |
| `one_open5gs_vm_key`           | Subscription Key (UE authentication).                                                              | `str`                   | No       |
| `one_open5gs_vm_opc`           | Operator Code (UE authentication).                                                                 | `str`                   | No       |
| `one_open5gs_vm_apn`           | APN to be assigned to the UE.                                                                      | `str`                   | No       |
| `one_open5gs_vm_s_nssai_sst`   | S-NSSAI Slice/Service Type.                                                                        | `int`                   | No       |
| `one_open5gs_vm_s_nssai_sd`    | S-NSSAI Slice Differentiator. Minimum length: 6.                                                    | `str`                   | No       |
| `one_open5gs_vm_ue_subnet`     | Subnet used by UEs. Independent of Trial Network VNets.                                            | `str`                   | No       |
| `one_open5gs_vm_use_nightly`   | Use nightly Open5GS build (unstable, for testing).                                                  | `bool`                  | No       |
| `one_open5gs_vm_install_webui` | Whether to install the Open5GS WebUI (increases deployment time).                                  | `bool`                  | No       |

---
