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

## Input Variables
| Variable                          | Description                                                                                                                                                                                                                                                                                                                   | Type                            | Default             | Required |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------|---------------------|----------|
| `one_ueransim_networks`          | Ordered list of Virtual Network names the VM will be part of. The first Virtual Network is used by Jenkins to reach and configure the VM, so most cases will be fine using always the tn_vxlan.                                                                                                                            | list[tn_vxlan or vnet]          | ['tn_vxlan']        | No       |
| `one_ueransim_mode`              | Set the component as a gNB, an UE or as both inside the Trial Network.                                                                                                                                                                                                | str                             | "both"                | No       |
| `one_ueransim_gnb_linked_5gcore` | Name of a previously deployed "5G Core" component. Enables autocompletion of gNB config.                                                                                                                                                          | "open5gs_vm or open5gs_k8s or upf_p4_sw or open5gcore_vm"            | null                | No       |
| `one_ueransim_gnb_amf_n2_ip`     | AMF IP address (hardcoded AMF port 38412). Overwritten if `linked_5gcore` is defined.                                                                                                                                                                                                                                         | str                             | 10.21.12.200        | No       |
| `one_ueransim_gnb_tac`           | Tracking Area Code (TAC). Overwritten if `linked_5gcore` is defined.                                                                                                                                                                                                                                                          | int                             | 200                 | No       |
| `one_ueransim_gnb_mcc`           | Mobile Country Code (3 digits). Overwritten if `linked_5gcore` is defined.                                                                                                                                                                                                                                                   | str                             | "001"               | No       |
| `one_ueransim_gnb_mnc`           | Mobile Network Code (2–3 digits). Overwritten if `linked_5gcore` is defined.                                                                                                                                                                                                                                                 | str                             | "01"                | No       |
| `one_ueransim_gnb_slices_sst`    | Slice/Service Type (SST) of the S-NSSAI. Overwritten if `linked_5gcore` is defined.                                                                                                                                                                                                                                           | int                             | 1                   | No       |
| `one_ueransim_gnb_slices_sd`     | Slice Differentiator (SD) of the S-NSSAI (min. 6 digits). Overwritten if `linked_5gcore` is defined.                                                                                                                                                                                                                          | str                             | "000001"            | No       |
| `one_ueransim_ue_linked_gnb`     | Previously deployed external gNB (only `ueransim` in mode `gnb` or `both`). Enables autocompletion of UE parameters. Not used in `gnb` mode.                                                                                                                                                                                 | Component (`ueransim`)          | null                | No       |
| `one_ueransim_ue_gnbsearchlist`  | List of IPs where the UE searches for gNBs. 'localhost' will be replaced with the first vnet IP. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                      | str                             | "localhost"         | No       |
| `one_ueransim_ue_mcc`            | MCC (3 digits). Used in IMSI. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                         | str                             | "001"               | No       |
| `one_ueransim_ue_mnc`            | MNC (2–3 digits). Used in IMSI. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                       | str                             | "01"                | No       |
| `one_ueransim_ue_msin`           | MSIN (9–10 digits). Used in IMSI. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                     | str                             | "0000000001"        | No       |
| `one_ueransim_ue_key`            | Permanent key of the UE. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                              | str                             | "465B5CE8B199B49F..." | No     |
| `one_ueransim_ue_opc`            | Operator code of the UE. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                              | str                             | "E8ED289DEBA952..." | No       |
| `one_ueransim_ue_session_apn`    | APN of the initial PDU session. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                       | str                             | "internet"          | No       |
| `one_ueransim_ue_session_sst`    | SST of the initial PDU session. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                       | int                             | 1                   | No       |
| `one_ueransim_ue_session_sd`     | SD of the initial PDU session. Overwritten if `linked_gnb` is defined.                                                                                                                                                                                                                                                        | str                             | "000001"            | No       |
