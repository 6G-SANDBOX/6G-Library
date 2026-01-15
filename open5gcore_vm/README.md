
# Open5GCore as a standalone Virtual Machine

The Fraunhofer FOKUS Open5GCore toolkit is the first global practical implementation of the 3GPP 5G core network, supporting the functionalities of 3GPP Releases 17 and 18. Tailored for R&D activities, Open5GCore ensures compatibility with both 5G base stations and user equipment.

Designed to accelerate research and facilitate the transfer of expertise from Fraunhofer FOKUS to users, Open5GCore provides a robust foundation for 5G testbed deployments, trials, pilots, and the advancement of functionalities for future-beyond 5G and 6G standards. It comes with customization to the use case needs, enabling to run a testbed from day one.

Open5GCore implements the new 5G components as standalone, independent of the previous 4G EPC functionality. This approach allows for rapid, focused innovation in 5G technology, enabling practical, hands-on implementation and realistic evaluation and demonstration of novel concepts and applications.

for More information plaeas visit https://www.open5gcore.org/

![loadcore_open5gcore_vm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/open5gcore_vm/loadcore_open5gcore_vm.drawio.png)

## Maintainers

- Björn Riemer <bjoern.riemer@fokus.fraunhofer.de>

## Short Description

The **open5gcore_vm** 6G-Library component deploys a 5G Standalone (SA) Core inside an Ubuntu VM all in one with no external dependencies.



## Long Description

This Componnet represents a VM with the Open5gCore 5G Core network inside customized ready to be used in the 6G-SANDBOX trial network.

The integrated UPF can be configured to utilize Linux XDP/eBPF to accelerate the datapath. 

Version 10.5.0 of the Open5Gcore is used for this component. 

> [!NOTE] 
> This Component is only availabe in the Berlin Platform.

## Hypervisors

Currently only OpenNebula (one) is available

## Depends on

- `tn_bastion` 
- `tn_vxlan`
- `vnet` 

## Tags

- open5gcore
- 5G Core
- SA Core
- all-in-one
- XDP


## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the Open5GS VM template to use in your OpenNebula environment |
| `image_id` | ID of the Open5GS VM image to use in your OpenNebula environment |
| `license_crt` | contents of the open5gcore license certificate | 
| `testbed_sim_sql` | Optional: SQL dump with sim card credentials used in the `udm_db` |
| `gitlab_deploy_token` | secret token to access the `gitlab_base_url` |
| `gitlab_base_url` | Part of the URL to download an updated Open5Gcore |

## Input variables


| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| `one_open5gcore_vm_external_vnet` | Virtual Network name asigned to the VM for management purposes. | tn_vxlan or vnet | tn_vxlan | - | false |
| `one_open5gcore_vm_internal_vnet` | Optional list of 1 or 2 vnets used for interfaces N2 (AMF) and N3 (UPF) respectivelly. | list[tn_vxlan or vnet] | null | - | false |
| `one_open5gcore_vm_size` | Select one type of instance according to your hardware requirements. | str | - | extra_large, large, medium, small, very_small | true |
| `one_open5gcore_vm_amf_n2_ip` | IPv4 address of the AMF in the 5G core network. | str | 10.10.10.200 | - | false |
| `one_open5gcore_vm_upf_n3_ip` | IPv4 address of the UPF in the 5G core network. | str | 10.10.10.201 | - | false |
| `one_open5gcore_vm_ue_count` | Number of UEs to be provisioned staring from MSIN | int | 20 | - | false |
| `one_open5gcore_vm_tac` | Tracking Area Code (TAC) | int | 200 | - | false |
| `one_open5gcore_vm_mcc` | Mobile Country Code (MCC) | str | "001" | - | false |
| `one_open5gcore_vm_mnc` | Mobile Network Code (MNC) | str | "01" | - | false |
| `one_open5gcore_vm_msin` | Mobile Subscriber Identification Number (MSIN) 9 or 10 digits inside quotes. mcc + mnc + msin must add to exactly 15 | str | "0000000001" | - | false |
| `one_open5gcore_vm_key` | Permanent Subscription Key of each UE. | str | 465B5CE8B199B49FAA5F0A2EE238A6BC | - | false |
| `one_open5gcore_vm_opc` | Operator Code (OP or OPC) of each UE. | str | E8ED289DEBA952E4283B54E88E6183CA | - | false |
| `one_open5gcore_vm_apn` | APN/DNN used for PDU session. | str | internet | - | false |
| `one_open5gcore_vm_s_nssai_sst` | SST of the S-NSSAI | int | 1 | - | false |
| `one_open5gcore_vm_s_nssai_sd` | SD of the S-NSSAI | str | 000001 | - | false |
| `one_open5gcore_vm_ue_subnet` | Subnet to be used by the UEs. | str | 10.45.0.0/16 | - | false |
| `one_open5gcore_vm_upf_xdp` | Enable UDP XDP Datapath acceleration | bool | false | - | false |


## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.
Each variable has a short description of what it does for informational purposes.  

- `open5gcore_vm-core-id`: Variable tied to a specific deployment of this component.
- `open5gcore_vm-core-ips`: Dictionary of VM IP addresses.
- `open5gcore_vm-core-metadata`: Metadata including AMF and UPF IPs, UE provision info, PLMN IDs
