# Open5GS for Kubernetes

The **open5gs_k8s** 6G-Library component deploys a 5G SA Core inside a Kubernetes cluster using the [Helm Chart from Gradiant](https://artifacthub.io/packages/helm/open5gs/open5gs).

## 📦 Current versions:
- Open5GS v2.7.2
- Used Helm Chart v2.2.6

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

## 🔧 Deployment Details

- **Hypervisor**: `OpenNebula`
- **Orchestrator**: Kubernetes (via `oneKE`)
- **Helm-based deployment**: [Helm Chart by Gradiant](https://artifacthub.io/packages/helm/open5gs/open5gs)
- **Dependencies**: Requires a `oneKE` cluster already deployed.

---

## 📥 Input Variables

| Variable                      | Description                                                                                                    | Type    | Required | Default              |
|------------------------------|----------------------------------------------------------------------------------------------------------------|---------|----------|----------------------|
| `one_open5gs_k8s_target`     | Name of a previously deployed `oneKE` component used to deploy the Helm chart.                                | oneKE   | ✅       | -                    |
| `one_open5gs_k8s_amf_n2_ip`  | AMF IP (should be within the `metallb_range` of the `oneKE`).                                                  | str     | ❌       | `"10.21.12.200"`     |
| `one_open5gs_k8s_upf_n3_ip`  | UPF IP (should be within the `metallb_range` of the `oneKE`).                                                  | str     | ❌       | `"10.21.12.201"`     |
| `one_open5gs_k8s_ue_count`   | Number of UEs to simulate (SUPIs are generated sequentially).                                                 | int     | ❌       | `20`                 |
| `one_open5gs_k8s_tac`        | Tracking Area Code (TAC) for the PLMN.                                                                         | int     | ❌       | `200`                |
| `one_open5gs_k8s_mcc`        | Mobile Country Code. 3 digits inside quotes.                                                                   | str     | ❌       | `"001"`              |
| `one_open5gs_k8s_mnc`        | Mobile Network Code. 2–3 digits inside quotes.                                                                 | str     | ❌       | `"01"`               |
| `one_open5gs_k8s_msin`       | Mobile Subscriber Identifier (MSIN). 9–10 digits.                                                              | str     | ❌       | `"0000000001"`       |
| `one_open5gs_k8s_key`        | Permanent Subscription Key used by UEs.                                                                        | str     | ❌       | `"465B5CE8B199B49FAA5F0A2EE238A6BC"` |
| `one_open5gs_k8s_opc`        | Operator Code used by UEs.                                                                                     | str     | ❌       | `"E8ED289DEBA952E4283B54E88E6183CA"` |
| `one_open5gs_k8s_apn`        | Access Point Name for the default PDU session.                                                                 | str     | ❌       | `"internet"`         |
| `one_open5gs_k8s_s_nssai_sst`| Slice/Service Type (SST) of the network slice.                                                                 | int     | ❌       | `1`                  |
| `one_open5gs_k8s_s_nssai_sd` | Slice Differentiator (SD) of the network slice.                                                                | str     | ❌       | `"000001"`           |

---