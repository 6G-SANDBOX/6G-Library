# RAN ACCESS for Berlin Platform

This component enables the integration of RAN physical equipment located at Berlin FOKUS with a 5G core already deployed in the Trial Network.
It is done by enabling the necessary routing path through the use of the element called [6G-Sandbox route-manager-api](https://marketplace.mobilesandbox.cloud:9443/appliance/service_routemanager) expected to be already deployed in the site.
WARNING: The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- `mcc`: "999" 
- `mnc`: "38"
- `apn`: "internet"
- `tac`: 122
- `s_nssai_sst`: 1
- `s_nssai_sd`: null
- `amf_n2_ip`: "10.10.11.200"
- `upf_n3_ip`: "10.10.11.201"

> [!NOTE]  
> Currently this component is only available in the site "fokus".

> [!NOTE]
> Aditional routing configuration that is not dependent on the Trial Network may also need to be done beforehand.

![berlin-ran_5Gcore_vm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/berlin_ran/berlin-ran_5Gcore_vm.png)

## Maintainers

- Bjoern Riemer <bjoern.riemer@fokus.fraunhofer.de>

## Short Description

Enables routing temporarily between physical RAN available in Berlin and a previously deployed 5G Core.

## Long Description

Component to integrate the physical RAN available in the Berlin site equipment with a 5G core already deployed inside the Trial Network.  
It is done by enabling the necessary routing path for a limited time duration using a **6G-Sandbox Route Manager API** VM, which must be pre-deployed in the site.

> ⚠️ This component may only work if the configurations for the related 5G Core and UE are set as follows:
- `mcc`: "999" 
- `mnc`: "38"
- `apn`: "internet"
- `tac`: 122
- `s_nssai_sst`: 1
- `s_nssai_sd`: null
- `amf_n2_ip`: "10.10.11.200"
- `upf_n3_ip`: "10.10.11.201"

> Additional routing configurations not dependent on the Trial Network may also be required.  
> **Currently only available at site `fokus`.**

## Hypervisors

- any

## Depends on

- tn_bastion  
- [open5gs_vm, open5gs_k8s, open5gcore_vm, upf_p4_sw]

## Tags

- RAN
- Huawei
- Nokia  
- gNB  
- radio  
- route manager

---

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

| Variable | Description |
|----------|-------------|
| `subnet`  | Subnet used for the RAN N2 and N3 interfaces. (example 10.55.0.0/16) |

---

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.

| Variable                        | Description                                                                                                    | Type                                               | Default Value         | Choices | Required When |
|---------------------------------|----------------------------------------------------------------------------------------------------------------|----------------------------------------------------|------------------------|---------|----------------|
| `any_berlin_ran_linked_5gcore`  | Name of a previously deployed 5G Core component. The gNB will connect to its AMF.                              | open5gs_vm or open5gs_k8s or open5gcore_vm or upf_p4_sw | -                 | -       | true           |
| `any_berlin_ran_start_time`     | Time and date from which the route is enabled (RFC 3339 format). Defaults to current time if unspecified.      | str                                                | Current time and date  | -       | false          |
| `any_berlin_ran_duration`       | Duration in seconds for the RAN equipment reservation                                                          | int                                                | 3600                   | -       | false          |

---

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `berlin_ran-component_type`: `"berlin_ran"`
- `berlin_ran-gnb_metadata`: Metadata object containing:
  - `linked_5gcore`: Value provided in `any_berlin_ran_linked_5gcore`