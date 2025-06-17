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
- `du_cu_container_name`: Name of the DU container for non RU-DU mode  
- `du_container_name`: Name of the DU container for RU-DU mode  

## Input Variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.

| Variable                              | Description                                                                                                                                                  | Type                                                             | Default Value           | Choices | Required When                            |
|---------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|-------------------------|---------|-------------------------------------------|
| `any_iswireless_radio_ru_du`         | Enables RU-DU mode, where the DU runs standalone and a virtualized CU must be deployed separately                                                           | `bool`                                                           | `false`                 | -       | `false`                                   |
| `any_iswireless_radio_linked_5gcore` | Reference to a previously deployed 5G Core component. Ignored in RU-DU mode                                                                                  | `open5gs_vm or open5gs_k8s or open5gcore_vm or upf_p4_sw`       | -                       | -       | `any_iswireless_radio_ru_du == false`     |
| `any_iswireless_radio_linked_cu`     | Reference to a deployed `cu_k8s` component. Required in RU-DU mode                                                                                           | `cu_k8s`                                                         | -                       | -       | `any_iswireless_radio_ru_du == true`      |
| `any_iswireless_radio_start_time`    | Time and date from which the route is enabled, in RFC 3339 format. Defaults to current time if unspecified                                                  | `str`                                                            | Current time and date   | -       | `false`                                   |
| `any_iswireless_radio_duration`      | Time in seconds for the ISWIRELESS reservation                                                                                                               | `int`                                                            | `10800`                 | -       | `false`                                   |

---

## Generated Terraform Outputs

Unknown before deployment; these outputs are stored in the S3 Object Storage and are available for use by future components.

Each output has a short description of what it does.  

- **`iswireless_radio-<custom_name>-component_type`**  
  `"iswireless_radio"` — identifies the type of component

- **`iswireless_radio-<custom_name>-gnb_metadata`**  
  Metadata including:
  - `linked_5gcore`: The selected 5G Core component, if any
