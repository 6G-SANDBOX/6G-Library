# Open5GS as a standalone Virtual Machine

The **open5gs_vm** 6G-Library component deploys a 5G SA Core inside an Ubuntu VM using scripts.
Current versions:
- Open5GS v2.7.6

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

## What is Open5GS

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

![loadcore_open5gs_vm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/open5gs_vm/loadcore_open5gs_vm.drawio.png)

## Maintainers

- Bjoern Riemer <bjoern.riemer@fokus.fraunhofer.de>
- Raúl Luzón González <raul.luzongonzalez@telefonica.com>
- Raúl Macián Castillo <raul.maciancastillo@telefonica.com>

## Short Description

Deploys a Open5GS as a standalone Virtual Machine

## Long Description

Deploys a Open5GS over a general-purpose Virtual Machine based on Ubuntu 22.04 LTS.  
The automation installs Open5GS version 2.7.6 and MongoDB on it.  
Through variable `one_open5gs_vm_size`, five different types of instances can be deployed:

- **extra_large**: 32 GiB of memory, 8 vCPU and 100GiB of storage  
- **large**: 16 GiB of memory, 4 vCPU and 50GiB of storage  
- **medium**: 8 GiB of memory, 4 vCPU and 25GiB of storage  
- **small**: 4 GiB of memory, 2 vCPU and 15GiB of storage  
- **very_small**: 2 GiB of memory, 1 vCPU and 10GiB of storage  

## Hypervisors

- one

## Depends on

- tn_bastion
- [vnet, tn_bastion]

## Tags

- 5G Core
- open5gs
- Standalone

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the Open5GS VM template to use in your OpenNebula environment |
| `image_id` | ID of the Open5GS VM image to use in your OpenNebula environment |
| `testbed_sim` | Optional: contents of a shell script to provision sim card secrets used in the testbed |
| `dns` | Optional: Default DNS server to give to the UE's if public variable one_open5gs_vm_dns is unset (default: 8.8.8.8) |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| `one_open5gs_vm_external_vnet` | Virtual Network name asigned to the VM for management purposes. | tn_vxlan or vnet | tn_vxlan | - | false |
| `one_open5gs_vm_internal_vnet` | Optional list of 1 or 2 vnets used for interfaces N2 (AMF) and N3 (UPF) respectivelly. | list[tn_vxlan or vnet] | null | - | false |
| `one_open5gs_vm_size` | Select one type of instance according to your hardware requirements. | str | - | extra_large, large, medium, small, very_small | true |
| `one_open5gs_vm_amf_n2_ip` | IPv4 address of the AMF in the 5G core network. | str | 10.21.12.200 | - | false |
| `one_open5gs_vm_upf_n3_ip` | IPv4 address of the UPF in the 5G core network. | str | 10.21.12.201 | - | false |
| `one_open5gs_vm_ue_count` | Number of UEs to be provisioned. | int | 20 | - | false |
| `one_open5gs_vm_tac` | Tracking Area Code (TAC) | int | 200 | - | false |
| `one_open5gs_vm_mcc` | Mobile Country Code (MCC) | str | "001" | - | false |
| `one_open5gs_vm_mnc` | Mobile Network Code (MNC) | str | "01" | - | false |
| `one_open5gs_vm_msin` | Mobile Subscriber Identification Number (MSIN) | str | "0000000001" | - | false |
| `one_open5gs_vm_key` | Permanent Subscription Key of each UE. | str | 465B5CE8B199B49FAA5F0A2EE238A6BC | - | false |
| `one_open5gs_vm_opc` | Operator Code (OP or OPC) of each UE. | str | E8ED289DEBA952E4283B54E88E6183CA | - | false |
| `one_open5gs_vm_apn` | APN of the initial PDU session. | str | internet | - | false |
| `one_open5gs_vm_s_nssai_sst` | SST of the S-NSSAI | int | 1 | - | false |
| `one_open5gs_vm_s_nssai_sd` | SD of the S-NSSAI | str | 000001 | - | false |
| `one_open5gs_vm_ue_subnet` | Subnet to be used by the UEs. | str | 10.45.0.0/16 | - | false |
| `one_open5gs_vm_use_nightly` | Install the Nightly version of Open5GS | bool | false | - | false |
| `one_open5gs_vm_install_webui` | Install the Open5GS Web UI | bool | false | - | false |
| `one_open5gs_vm_dns` | DNS server to give to the UE's. Overrides the site's default | str | false | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `open5gs_vm-id`: "Variable tied to a specific deployment of this component."
- `tn_ssh_public_key`: "Variable with a global name. Generated by a component that can only be deployed once per TN, such as tn_init"
