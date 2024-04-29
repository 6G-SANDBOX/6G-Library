# UERANSIM

The **UERANSIM** 6G-Library component allows the experimenters to test and deploy their own Appliance of a 5G-SA UE and gNodeB.

This component is based on an Ubuntu 22.04 minimal image with the latest UERANSIM binaries (v3.2.6): `nr-cli`, `nr-gnb`, `nr-ue` and `nroff`.

The mutable variables are specified in the sample input file at `doc/sample_input_file.yaml`


## What is UERANSIM
(Taken from the [UERANSIM repository](https://github.com/aligungr/UERANSIM))

<p align="center">
  <a href="https://raw.githubusercontent.com/aligungr/UERANSIM/master/.github/logo.png"><img src="." width="75" title="UERANSIM"></a>
</p>
<p align="center">
<img src="https://img.shields.io/badge/UERANSIM-v3.2.6-blue" />
<img src="https://img.shields.io/badge/3GPP-R15-orange" />
<img src="https://img.shields.io/badge/License-GPL--3.0-green"/>
</p>

**UERANSIM** <small>(pronounced "ju-i ræn sɪm")</small>, is the open source state-of-the-art 5G UE and RAN (gNodeB)
simulator. UE and RAN can be considered as a 5G mobile phone and a base station in basic terms. The project can be used for
testing 5G Core Network and studying 5G System.

UERANSIM introduces the world's first and only open source 5G-SA UE and gNodeB implementation.

## Current Status

Basic functionalities of UE and gNodeB are fully functional and ready to use. However some of the features are not complete.
More details can be found at [Feature Set](https://github.com/aligungr/UERANSIM/wiki/Feature-Set).

On the other hand, UERANSIM does not fully provide physical layer. 5G-NR radio interface is partially implemented, and simply simulated over UDP protocol.

<p align="center">
<img src="https://img.shields.io/badge/Radio%20Interface-simulated-orange" alt="OS Linux"/>
<img src="https://img.shields.io/badge/Control%20Plane-functional-green" alt="OS Linux"/>  
<img src="https://img.shields.io/badge/User%20Plane-functional-green" alt="OS Linux"/>
</p>

