# Open5GS for Kubernetes

The **open5gs_k8s** 6G-Library component deploys a 5G SA Core inside a Kubernetes cluster using the [Helm Chart from Gradiant](https://artifacthub.io/packages/helm/open5gs/open5gs).

## 📦 Current versions:
- Open5GS binaries: `v2.7.5`
- Helm Chart: `v2.3.3`

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

![open5gs_k8s](https://github.com/6G-SANDBOX/6G-Library/blob/assets/open5gs_k8s/open5gs_k8s.png)

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>

## Short Description

Deploys a 5G Core inside a Kubernetes cluster.

## Long Description

The **open5gs_k8s** 6G-Library component deploys a 5G SA Core inside a Kubernetes cluster using the Helm Chart provided by Gradiant.
It allows provisioning a set of UEs for testing and is designed to run inside a previously deployed `oneKE` Kubernetes cluster.
It requires the use of the `MetalLB` LoadBalancer to assign public IPs to AMF and UPF components.

More info is available in the Open5GS [official documentation](https://open5gs.org/open5gs/docs/) and the [Helm Chart on ArtifactHub](https://artifacthub.io/packages/helm/open5gs/open5gs).

## Hypervisors

- one

## Depends on

- oneKE

## Tags

- open5gs
- 5G Core
- kubernetes
- SA Core
- Helm Chart

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.
TNLCM checks that the variables are defined and errors if attempting to deploy the component without them.

*None defined for this component.*

## Input variables

| Variable | Description | Type | Default Value | Choices | Required When |
|----------|-------------|------|----------------|---------|----------------|
| `one_open5gs_k8s_target` | Name of the `oneKE` cluster where the chart will be deployed | oneKE | - | - | true |
| `one_open5gs_k8s_amf_n2_ip` | AMF N2 IP within the MetalLB range | str | `"10.21.12.200"` | - | false |
| `one_open5gs_k8s_upf_n3_ip` | UPF N3 IP within the MetalLB range | str | `"10.21.12.201"` | - | false |
| `one_open5gs_k8s_ue_count` | Number of UEs to provision | int | `20` | - | false |
| `one_open5gs_k8s_tac` | Tracking Area Code | int | `200` | - | false |
| `one_open5gs_k8s_mcc` | Mobile Country Code (3 digits) | str | `"001"` | - | false |
| `one_open5gs_k8s_mnc` | Mobile Network Code (2-3 digits) | str | `"01"` | - | false |
| `one_open5gs_k8s_msin` | Mobile Subscriber ID Number (9-10 digits) | str | `"0000000001"` | - | false |
| `one_open5gs_k8s_key` | Subscription Key | str | `"465B5CE8B199B49FAA5F0A2EE238A6BC"` | - | false |
| `one_open5gs_k8s_opc` | Operator Code | str | `"E8ED289DEBA952E4283B54E88E6183CA"` | - | false |
| `one_open5gs_k8s_apn` | Access Point Name | str | `"internet"` | - | false |
| `one_open5gs_k8s_s_nssai_sst` | S-NSSAI Slice/Service Type | int | `1` | - | false |
| `one_open5gs_k8s_s_nssai_sd` | S-NSSAI Slice Differentiator | str | `"000001"` | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.

__entity_name__ refers to the name of the component inside the TN.  
Actual terraform output uses dashes which are a forbidden character for ansible variables.  

- `open5gs_k8s-component_type`: "open5gs_k8s"
- `open5gs_k8s-5gcore_metadata`: Metadata including AMF and UPF IPs, UE provision info, PLMN IDs, and link to the oneKE cluster used
