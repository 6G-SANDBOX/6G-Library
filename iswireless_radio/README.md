# ISWIRELESS RADIO

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>  
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>  
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>  

## Short Description

Enables the capability of using ISWIRELESS Radio in a Trial Network.

## Long Description

This component enables the integration of ISWIRELESS physical equipment located at UMA with a 5G Core deployed in the Trial Network.  
The integration is achieved by enabling the necessary routing path using the **Route Manager**, which is already deployed at the site.

The configuration exposed by this component must be **synchronized** with both the 5G Core and the UE used in conjunction. These values must match:

- `mcc`: "999"  
- `mnc`: "71"  
- `apn`: "internet"  
- `tac`: 1  
- `s_nssai_sst`: 1  
- `s_nssai_sd`: "000009"  
- `amf_n2_ip`: "10.10.12.200"  
- `upf_n3_ip`: "10.10.12.201"  

> **NOTE**: This component is only available at the site `"uma"`.

## Hypervisors

- any

## Depends on

- tn_bastion  
- [open5gs_vm, open5gs_k8s, open5gcore_vm, upf_p4_sw] if `any_iswireless_radio_ru_du == false`

## Tags

- radio  
- gNB  
- IS-WIRELESS  
- route manager  

## Site-specific variables

These values must be present in the site's encrypted variables file:

- `cu_ip`: IP address of the CU when not running in RU-DU mode  
- `du_ip`: IP address of the DU when running in RU-DU mode  
- `portainer_url`: URL of the Portainer instance managing the CU & DU containers (e.g. `http://10.20.30.40:9000`)  
- `portainer_token`: Valid API token for authentication  
- `portainer_environment`: Portainer environment ID (e.g. `1`)  
- `cu_container_name`: Name of the CU container in Portainer  
- `du_cu_container_name`: Name of the DU container for non RU-DU mode  
- `du_container_name`: Name of the DU container for RU-DU mode  

## Input Variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.

#### Variables

- **any_iswireless_radio_ru_du**
  - **description**: Enables RU-DU mode, where the DU runs standalone and a virtualized CU component must be separately deployed.
  - **type**: `bool`
  - **default_value**: `false`
  - **required_when**: `false`

- **any_iswireless_radio_linked_5gcore**
  - **description**: Reference to a previously deployed 5G Core component. The gNB connects to its AMF. Ignored if RU-DU mode is enabled.
  - **type**: `open5gs_vm or open5gs_k8s or open5gcore_vm or upf_p4_sw`
  - **required_when**: `any_iswireless_radio_ru_du == false`

- **any_iswireless_radio_linked_cu**
  - **description**: Reference to a deployed CU component (`cu_k8s`). Only required when RU-DU mode is enabled.
  - **type**: `cu_k8s`
  - **required_when**: `any_iswireless_radio_ru_du == true`

- **any_iswireless_radio_start_time**
  - **description**: Time and date from which the route is enabled, in RFC 3339 format (e.g., `2024-11-08T09:12:00+00:00`). Defaults to current time.
  - **type**: `str`
  - **default_value**: Current time and date
  - **required_when**: `false`

- **any_iswireless_radio_duration**
  - **description**: Time in seconds for the ISWIRELESS reservation. Default is 10800 (3 hours).
  - **type**: `int`
  - **default_value**: `10800`
  - **required_when**: `false`

---

## Generated Terraform Outputs

Unknown before deployment; these outputs are stored in the S3 Object Storage and are available for use by future components.

Each output has a short description of what it does.  
`__entity_name__` refers to the internal component name inside the TN. In Terraform, underscores are used instead of dashes.

- **`__entity_name__component_type`**  
  `"iswireless_radio"` — identifies the type of component

- **`__entity_name__gnb_metadata`**  
  Metadata including:
  - `linked_5gcore`: The selected 5G Core component, if any
