# Athens RAN RADIO (Ericsson RAN)

Component to integrate the Ericsson RAN physical equipment with a 5G core already deployed inside the Trial Network.
It is done by enabling the necessary routing path using a [6G-Sandbox route-manager-api](https://marketplace.mobilesandbox.cloud:9443/appliance/service_routemanager) VM expected to be already deployed in the site.

The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- `mcc`: "001" 
- `mnc`: "01"
- `apn`: "default"
- `imsi`: "001010000000001"
- `tac`: 1
- `s_nssai_sst`: 1
- `s_nssai_sd`: "000001"
- `key`: "465B5CE8B199B49FAA5F0A2EE238A6BC"
- `opc`: "E8ED289DEBA952E4283B54E88E6183CA"
- `amf_n2_ip`: "10.10.10.200"
- `upf_n3_ip`: "10.10.10.201"


> [!NOTE]
> Aditional routing configuration that is not dependent on the Trial Network may also need to be done beforehand.

> [!NOTE]  
> Currently this component is only available in the site "athens".

![athens_ran](https://github.com/6G-SANDBOX/6G-Library/blob/assets/athens_ran/athens_radio.png)

## Maintainers

- Jason Diakoumakos <i.diakoumakos@oteresearch.gr>
- Vasilis Pitsilis <vpitsilis@dat.demokritos.gr>
- George Pappas <g.pappas@oteresearch.gr>

## Short Description

Enables routing temporarily between Ericsson RAN physical equipment and a previously deployed 5G Core via the Route Manager.

## Long Description

The Ericsson gNB RAN 6G-Library Component is implemented and tested as part of the Athens Platform based on the 6G-SANDBOX TN (Trial Network) component template. This component enables the integration of physical Ericsson RAN equipment (BBU6630, RH4408) installed at the COSMOTE Site with 5G cores deployed inside Trial Networks.

The component's primary purpose is to allow the use of the Ericsson gNB by any dynamically deployed trial network, as scheduled and managed by the Athens TNLCM. This is achieved through the on-demand scheduling of route changes in the RAN Router Manager, overcoming the limitation of legacy RAN configurations that cannot allow dynamic assignment to TNs in real-time.

This component requires the following prerequisites:
- A previously deployed "Route Manager" component must be referenced in the input variable "routeManagerRef"
- The Route Manager appliance must be installed in your site before deploying this component

Functionality is done by enabling the necessary routing path for a limited time duration using a **6G-Sandbox Route Manager API** VM, which must be pre-deployed in the site.

The Route Manager is a custom appliance, developed as part of the 6G-SANDBOX library, that handles the routing configuration between the physical equipment and the 5G core. It manages network paths, traffic routing, and ensures proper connectivity between different network elements. The appliance must be properly configured with the correct network interfaces and routing tables to enable seamless communication between the RAN and core components.

 
> ⚠️ This component may only work if the configurations for the related 5G Core and UE are set as follows:
> - `mcc`: `"001"`  
> - `mnc`: `"01"`  
> - `apn`: `"default"`  
> - `imsi`: `"001010000000001"`  
> - `tac`: `1`  
> - `s_nssai_sst`: `1`  
> - `s_nssai_sd`: `"000001"`  
> - `amf_n2_ip`: `"10.10.10.200"`  
> - `upf_n3_ip`: `"10.10.10.201"`  
> - `key`: `"465B5CE8B199B49FAA5F0A2EE238A6BC"`  
> - `opc`: `"E8ED289DEBA952E4283B54E88E6183CA"`  

> Additional routing configurations not dependent on the Trial Network may also be required.  
> **Currently only available at site `athens`.**

## Hypervisors

- any

## Depends on

- tn_bastion  
- tn_init
- [open5gs_vm]

## Tags

- Ericsson
- Ericsson RAN
- gNB  
- radio  
- route manager
- 5G Core
- Physical Equipment
- Athens Site

---

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

| Variable | Description |
|----------|-------------|
| `n2_ip`  | IP address of Ericsson RAN in the Control Plane N2 interface. It connects to the AMF of the 5G Core. |
| `n3_ip`  | IP address of Ericsson RAN in the User Plane N3 interface. It connects to the UPF of the 5G Core.    |

---

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.

| Variable                        | Description                                                                                                             | Type                                               | Default Value         | Choices | Required When |
|----------------------------------|-------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------|------------------------|---------|----------------|
| `any_athens_ran_linked_5gcore`  | Name of a previously deployed 5G Core component. The gNB will connect to its AMF.                                      | open5gs_vm | -                      | -       | true           |
| `any_athens_ran_start_time`     | Time and date from which the route is enabled (RFC 3339 format). Defaults to current time if unspecified.             | str                                                | Current time and date  | -       | false          |
| `any_athens_ran_duration`       | Duration in seconds for the Ericsson RAN route reservation                                                           | int                                                | 3600                   | -       | false          |

---

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `athens_ran-component_type`: `"athens_ran"`
- `athens_ran-gnb_metadata`: Metadata object containing:
  - `linked_5gcore`: Value provided in `any_athens_ran_linked_5gcore`

