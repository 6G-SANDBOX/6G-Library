# ISWIRELESS RADIO

Component to integrate the ISWIRELESS ORAN physical equipment with 5G cores deployed inside Trial Networks.
It is done by enabling the necessary routing path through the use of the element called ‘Route Manager’ (already deployed in the site). 
The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- `mcc`: "001" 
- `mnc`: "01"
- `apn`: "internet"
- `tac`: 1
- `s_nssai_sst`: 1
- `s_nssai_sd`:
- `amf_n2_ip`: "10.10.12.200"
- `upf_n3_ip`: "10.10.12.201"

> [!NOTE]  
> Currently this component is only available in the site "uma".

## Input Variables

| Variable | Description | Type | Default | Required |
|---------|-------------|------|---------|----------|
| `any_iswireless_radio_ru_du` | Enables RU-DU mode, where the DU is remote and a CU must be linked | `bool` | `false` | No |
| `any_iswireless_radio_linked_5gcore` | Reference to a previously deployed 5G Core component (ignored if `ru_du` is `true`) | `open5gs_vm` or `open5gs_k8s` or `open5gcore_vm` or `upf_p4_sw` | `null` | If `ru_du == false` |
| `any_iswireless_radio_linked_cu` | Reference to a deployed `cu_k8s` component (only required if `ru_du` is `true`) | `cu_k8s` | `null` | If `ru_du == true` |
| `any_iswireless_radio_start_time` | Start time of the route (RFC 3339 format). Defaults to current time if not set | `str` | Current time and date | No |
| `any_iswireless_radio_duration` | Duration of the ISWIRELESS route reservation in seconds | `int` | `10800` (3 hours) | No |

---

## Terraform Outputs

| Output | Description |
|--------|-------------|
| `__<component>__component_type` | Always `iswireless_radio` |
| `__<component>__gnb_metadata` | Metadata including the linked 5G core configuration |

---
