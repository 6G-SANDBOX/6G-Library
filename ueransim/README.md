# UERANSIM

The [**UERANSIM**](https://github.com/aligungr/UERANSIM) 6G-Library component allows the experimenters to test and deploy their own Appliance of a 5G-SA UE and gNodeB.
It is based on an Ubuntu 22.04 LTS image with the latest UERANSIM binaries (v3.2.6): `nr-cli`, `nr-gnb`, `nr-ue` and `nroff`.

This appliance can be used as a 5G User Equipment (UE), a gNB, as both or as None, according to input variables `one_ueransim_run_gnb` and `one_ueransim_run_ue` or a combination of them. These variables set systemd services `ueransimb-gnb.service` and `ueransimb-ue.service` respectively as started and enabled at boottime.

The behaviour of the services depends on the config files at `/etc/ueransim/open5gs-gnb.yaml` and `/etc/ueransim/open5gs-ue.yaml` are modified during the first deployment according to prior components dependencies and/or the values specified in the input file.
For advanced configurations, you manually edit those files and restart the services. Examples can be found in [this repository](https://github.com/s5uishida/open5gs_5gc_ueransim_sample_config).

Depending on the chosen behaviour (with input variables `one_ueransim_run_gnb` and `one_ueransim_run_ue` respectivelly), the following dependencies are aplied:
- If `one_ueransim_run_gnb == 'YES'`, a previously deployed `open5gs` component can be referenced in the `one_ueransim_gnb_linked_open5gs` input variable to autocomplete gNB-related variables.
- If `one_ueransim_run_gnb == 'NO' and one_ueransim_run_ue == 'YES'`, a previously deployed `ueransim` component with `run_gnb == 'YES'` can be referenced in the `one_ueransim_ue_linked_gnb` input variable to autocomplete UE-related variables.
- If `one_ueransim_run_gnb == 'NO' and one_ueransim_run_ue == 'YES'`, the UE-related variables will be automatically autocompleted by default with the gnB-related variables.







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

### Ueransim both configuration

![ueransim_both](https://github.com/6G-SANDBOX/6G-Library/blob/assets/ueransim/ueransim_both.png)

### Ueransim split configuration

![ueransim_split](https://github.com/6G-SANDBOX/6G-Library/blob/assets/ueransim/ueransim_split.png)
