# Erricson RAN ACCESS for Athens Platform

## Maintainers

- Vasilis Pitsilis <vpitsilis@dat.demokritos.gr>
- George Pappas <g.pappas@oteresearch.gr>
- Jason Diakoumakos <i.diakoumakos@oteresearch.gr>


## Short Description

Enables the capability of using physical RAN in a Trial Network (Athens RAN Erricson).


## Long Description

The Ericsson gNB RAN 6G-Library Component is implemented and tested as part of the Athens Platform based on the 6G-SANDBOX TN (Trial Network) component template. This component enables the integration of physical Ericsson RAN equipment (BBU6630, RH4408) installed at the COSMOTE Site with 5G cores deployed inside Trial Networks.

The component's primary purpose is to allow the use of the Ericsson gNB by any dynamically deployed trial network, as scheduled and managed by the Athens TNLCM. This is achieved through the on-demand scheduling of route changes in the RAN Router Manager, overcoming the limitation of legacy RAN configurations that cannot allow dynamic assignment to TNs in real-time.

This component requires the following prerequisites:
- A previously deployed "Route Manager" component must be referenced in the input variable "routeManagerRef"
- The Route Manager appliance must be installed in your site before deploying this component

The Route Manager is a custom appliance, developed as part of the 6G-SANDBOX library, that handles the routing configuration between the physical equipment and the 5G core. It manages network paths, traffic routing, and ensures proper connectivity between different network elements. The appliance must be properly configured with the correct network interfaces and routing tables to enable seamless communication between the RAN and core components.

The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:

- `mcc`: "001" 
- `mnc`: "01"
- `apn`: "internet"
- `tac`: 200
- `s_nssai_sst`: 1
- `s_nssai_sd`: "000001"
- `amf_ip`: "10.10.10.200"
- `upf_ip`: "10.10.10.201"


## Hypervisors

- any


## Depends on

- tn_bastion
- tn_init
- open5gs_vm
- Route Manager (pre-deployed in site)


## Tags

- radio
- gNB
- route manager
- Erricson RAN
- 5G Core
- Physical Equipment
- Athens Site


## Site-specific variables

Variables read from your site's encripted file in the 6G-Sandbox-Sites repository.
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them
Each variable has a short description of what it does for informational purposes

- `cp_ip`: 172.25.11.200 #IP address of the Control Plane
- `up_ip`: 172.25.13.200 #IP address of the User Plane

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.


- **one_ericsson_radio_open5gs**
  - **description**: Reference to a previously deployed 5G Core component. The gNB connects to its AMF.
  - **type**: `open5gs_vm-core`
  - **required_when**: `true`

- **any_athens_ran_duration**
  - **description**: Time in seconds for the reservation. Default is 3600.
  - **type**: `int`
  - **default_value**: `3600`
  - **required_when**: `false`


## Generated terraform outputs

Unknown before deployment; these outputs are stored in the S3 Object Storage and are available for use by future components.

Each output has a short description of what it does.  
`__entity_name__` refers to the internal component name inside the TN. In Terraform, underscores are used instead of dashes.

- **`__entity_name__component_type`**  
  `"iswireless_radio"` — identifies the type of component

- **`__entity_name__gnb_metadata`**  
  Metadata including:
  - `linked_5gcore`: The selected 5G Core component, if any


