# UERANSIM

The [**UERANSIM**](https://github.com/aligungr/UERANSIM) 6G-Library component allows the experimenters to test and deploy their own Appliance of a 5G-SA UE and gNodeB.
It is based on an Ubuntu 22.04 LTS image with the latest UERANSIM binaries (v3.2.6): `nr-cli`, `nr-gnb`, `nr-ue` and `nroff`.

This appliance can be used as a 5G User Equipment (UE), a gNB, as both or as None, according to input variables `one_ueransim_run_gnb` and `one_ueransim_run_ue` or a combination of them. These variables set systemd services `ueransimb-gnb.service` and `ueransimb-ue.service` respectively as started and enabled at boot time.

The behaviour of the services depends on the config files at `/etc/ueransim/open5gs-gnb.yaml` and `/etc/ueransim/open5gs-ue.yaml` are modified during the first deployment according to prior components dependencies and/or the values specified in the input file.
For advanced configurations, you manually edit those files and restart the services. Examples can be found in [this repository](https://github.com/s5uishida/open5gs_5gc_ueransim_sample_config).

Depending on the chosen behaviour (with input variables `one_ueransim_run_gnb` and `one_ueransim_run_ue` respectivelly), the following dependencies are aplied:
- If `one_ueransim_run_gnb == true`, a previously deployed `open5gs` component can be referenced in the `one_ueransim_gnb_linked_5gcore` input variable to autocomplete gNB-related variables.
- If `one_ueransim_run_gnb == false and one_ueransim_run_ue == true`, a previously deployed `ueransim` component with `one_ueransim_run_gnb == true` can be referenced in the `one_ueransim_ue_linked_gnb` input variable to autocomplete UE-related variables.
- If `one_ueransim_run_gnb == true` and `one_ueransim_run_ue == true`, the UE-related variables will be automatically autocompleted by default with the gnB-related variables.



## What is UERANSIM
<p align="center">
  <a href="https://github.com/aligungr/UERANSIM">
    <img src="https://raw.githubusercontent.com/aligungr/UERANSIM/master/.github/logo.png" width="125" title="UERANSIM">
  </a>
</p>
<p align="center">
<img src="https://img.shields.io/badge/UERANSIM-v3.2.6-blue" />
<img src="https://img.shields.io/badge/3GPP-R15-orange" />
<img src="https://img.shields.io/badge/License-GPL--3.0-green"/>
</p>

[**UERANSIM**](https://github.com/aligungr/UERANSIM) <small>(pronounced "ju-i ræn sɪm")</small>, is the open source state-of-the-art 5G UE and RAN (gNodeB)
simulator. UE and RAN can be considered as a 5G mobile phone and a base station in basic terms. The project can be used for
testing 5G Core Network and studying 5G System.

UERANSIM introduces the world's first and only open source 5G-SA UE and gNodeB implementation.

### Current Status

Basic functionalities of UE and gNodeB are fully functional and ready to use. However some of the features are not complete.
More details can be found at [Feature Set](https://github.com/aligungr/UERANSIM/wiki/Feature-Set).

On the other hand, UERANSIM does not fully provide physical layer. 5G-NR radio interface is partially implemented, and simply simulated over UDP protocol.

<p align="center">
<img src="https://img.shields.io/badge/Radio%20Interface-simulated-orange" alt="OS Linux"/>
<img src="https://img.shields.io/badge/Control%20Plane-functional-green" alt="OS Linux"/>  
<img src="https://img.shields.io/badge/User%20Plane-functional-green" alt="OS Linux"/>
</p>

### Ueransim both `open5gs_k8s` configuration

![ueransim_both](https://github.com/6G-SANDBOX/6G-Library/blob/assets/ueransim/ueransim_both_open5gs_k8s.png)

### Ueransim split `open5gs_k8s` configuration

![ueransim_split](https://github.com/6G-SANDBOX/6G-Library/blob/assets/ueransim/ueransim_split_open5gs_k8s.png)

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>

## Short Description

Deploys a VM with UERANSIM services, simulating a state-of-art 5G gNB, UE or both.

## Long Description

The UERANSIM component can be used as a 5G gNB, a User Equipment (UE) or as both at the same time, according to input variable `one_ueransim_mode`.

- In **gnb** mode, all gNB-related variables are used to modify the YAML config file used by the `ueransim-gnb.service`. This mode (and **both**) requires to specify a previously deployed "5G Core" component in the `one_ueransim_gnb_linked_5gcore` input variable.

- In **ue** mode, all UE-related variables are used to modify the YAML config file used by the `ueransim-ue.service`. If the optional input variable `one_ueransim_ue_linked_gnb` is provided with a previously deployed "gNB" component (including another UERANSIM in modes *gnb* or *both*), its variables will be used to autocomplete the UE-related variables not provided by the experimenter.

- **both** mode will act as *gnb* and *ue* modes at the same time. Moreover, if `one_ueransim_ue_linked_gnb` is left blank, UE-related variables not provided by the experimenter can be autocompleted using their respective gNB equivalent variables.

## Hypervisors

- one

## Depends on

- tn_bastion
- [tn_vxlan, vnet]
- [open5gs_vm, open5gs_k8s, open5gcore_vm, upf_p4_sw] if any_iswireless_radio_ru_du == false

## Tags

- ueransim
- gNB
- UE
- 5G

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.

Each variable has a short description of what it does for informational purposes.

- `template_id`: ID of the UERANSIM VM template to use in your OpenNebula environment  
- `image_id`: ID of the UERANSIM VM image to use in your OpenNebula environment

## Input variables

| variable                         | description                                                                                      | type                               | default_value         | choices               | required_when |
|----------------------------------|--------------------------------------------------------------------------------------------------|------------------------------------|------------------------|------------------------|----------------|
| one_ueransim_networks            | Ordered list of Virtual Network names the VM will be part of.                                   | list[tn_vxlan or vnet]             | ["tn_vxlan"]          | -                      | false          |
| one_ueransim_mode                | Set the component as a gNB, an UE or both.                                                      | str                                | "both"                   | ["gnb", "ue", "both"] | false          |
| one_ueransim_gnb_linked_5gcore   | Name of a previously deployed 5G Core component.                                                | open5gs_vm or open5gs_k8s or upf_p4_sw or open5gcore_vm                  | null                   | -                      | false          |
| one_ueransim_gnb_amf_n2_ip       | AMF IPv4 address.                                                                               | str                                | "10.21.12.200"           | -                      | false          |
| one_ueransim_gnb_tac             | Tracking Area Code (TAC).                                                                       | int                                | 200                    | -                      | false          |
| one_ueransim_gnb_mcc             | Mobile Country Code (MCC).                                                                      | str                                | "001"                  | -                      | false          |
| one_ueransim_gnb_mnc             | Mobile Network Code (MNC).                                                                      | str                                | "01"                   | -                      | false          |
| one_ueransim_gnb_slices_sst      | Slice/Service Type (SST).                                                                       | int                                | 1                      | -                      | false          |
| one_ueransim_gnb_slices_sd       | Slice Differentiator (SD).                                                                      | str                                | "000001"               | -                      | false          |
| one_ueransim_ue_linked_gnb       | Linked external gNB component for UE autocompletion.                                           | ueransim                           | null                   | -                      | false          |
| one_ueransim_ue_gnbsearchlist    | List of IPs where UE will search for gNBs.                                                     | str                                | "localhost"              | -                      | false          |
| one_ueransim_ue_mcc              | UE Mobile Country Code.                                                                         | str                                | "001"                  | -                      | false          |
| one_ueransim_ue_mnc              | UE Mobile Network Code.                                                                         | str                                | "01"                   | -                      | false          |
| one_ueransim_ue_msin             | UE MSIN                                                                                         | str                                | "0000000001"           | -                      | false          |
| one_ueransim_ue_key              | Subscription key of the UE.                                                                     | str                                | "465B5CE8B199B49FAA5F0A2EE238A6BC"     | -                      | false          |
| one_ueransim_ue_opc              | Operator code of the UE.                                                                        | str                                | "E8ED289DEBA952E4283B54E88E6183CA"      | -                      | false          |
| one_ueransim_ue_session_apn      | APN of initial PDU session.                                                                     | str                                | "internet"               | -                      | false          |
| one_ueransim_ue_session_sst      | SST of initial PDU session.                                                                     | int                                | 1                      | -                      | false          |
| one_ueransim_ue_session_sd       | SD of initial PDU session.                                                                      | str                                | "000001"               | -                      | false          |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.
Each variable has a short description of what it does for informational purposes.  

- `ueransim-id`: Variable tied to a specific deployment of this component.
- `ueransim-ips`: Dictionary of VM IP addresses.
- `ueransim-mode`: Mode set for this UERANSIM deployment.
- `ueransim-gnb_metadata`: Metadata about the gNB role (linked 5G core, etc).
- `ueransim-ue_metadata`: Metadata about the UE role (linked gNB, etc).
- `tn_ssh_public_key`: Variable with a global name. Generated by a component that can only be deployed once per TN, such as `tn_init`.
