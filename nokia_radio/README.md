# NOKIA RADIO

Component to integrate the Nokia Airscale physical equipment with a 5G core already deployed inside the Trial Network.
It is done by enabling the necessary routing path using a [6G-Sandbox route-manager-api](https://marketplace.mobilesandbox.cloud:9443/appliance/service_routemanager) VM expected to be already deployed in the site.
The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- `mcc`: "214" 
- `mnc`: "702"
- `apn`: "internet"
- `tac`: 1
- `s_nssai_sst`: 1
- `s_nssai_sd`: "000009"
- `amf_n2_ip`: "10.10.12.200"
- `upf_n3_ip`: "10.10.12.201"

> [!NOTE]
> Aditional routing configuration that is not dependent on the Trial Network may also need to be done beforehand.

> [!NOTE]  
> Currently this component is only available in the site "uma".

![nokia_radio](https://github.com/6G-SANDBOX/6G-Library/blob/assets/nokia_radio/nokia_radio.png)

## 🔧 Deployment Details

- Hypervisor: `any`
- No appliance is deployed: this component configures routing paths via an existing [route-manager-api](https://marketplace.mobilesandbox.cloud:9443/appliance/service_routemanager) service.
- Requires the `tn_bastion` component to be deployed beforehand for access.
- Requires the `service_routemanager` component to be deployed and running in the site.

## 📥 Input Variables

| Variable                      | Description                                                                                                            | Type        | Required |
|------------------------------|------------------------------------------------------------------------------------------------------------------------|-------------|----------|
| `any_nokia_radio_linked_5gcore` | Name of the previously deployed 5G Core component inside the Trial Network. The gNB will try to connect to its AMF.   | str (component name) | Required |
| `any_nokia_radio_start_time`     | Time and date from which the routing is enabled, in RFC 3339 format (e.g., `2024-11-08T09:12:00+00:00`). Defaults to current time. | str         | Optional |
| `any_nokia_radio_duration`       | Duration of the Nokia Airscale reservation in seconds. Default: `3600` (1 hour).                                      | int         | Optional |
