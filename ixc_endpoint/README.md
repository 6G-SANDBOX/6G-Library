# IxChariot Endpoint

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>
- Filip Ivanovich <filip.ivanovic@keysight.com>
- Abhishek Bakshi <abhishek.bakshi@keysight.com>

## Short Description

Keysight IxChariot Endpoint for network performance evaluation.

## Long Description

IxChariot Performance Endpoints act as controlled traffic sources and receivers, simulating real-world applications like web browsing, video streaming, or file transfers to evaluate how a network handles different traffic types.  
This component deploys a VM configured as an IxChariot endpoint and connects it to a specified Registration Server, allowing centralized orchestration of performance tests.  
It requires connection to a virtual network (usually `tn_vxlan`) and can be integrated in complex test setups involving multiple traffic endpoints.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- IxChariot  
- traffic generator  
- performance testing  
- network testing

---

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

| Variable     | Description                                                                       |
|--------------|-----------------------------------------------------------------------------------|
| `template_id` | ID of the IxChariot endpoint VM template to use in your OpenNebula environment   |
| `image_id`    | ID of the IxChariot endpoint VM image to use in your OpenNebula environment      |

---

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.

| Variable                | Description                                                                                     | Type                         | Default Value  | Choices | Required When |
|-------------------------|-------------------------------------------------------------------------------------------------|------------------------------|----------------|---------|----------------|
| `one_ixc_endpoint_networks` | Ordered list of Virtual Network names the VM will be part of. Typically starts with `tn_vxlan`. | list[tn_vxlan or vnet]       | ["tn_vxlan"]   | -       | false          |
| `one_ixc_endpoint_rs`       | Registration Server IP Address to register the endpoint.                                     | str                          | -              | -       | true           |

---

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `ixc_endpoint-<custom_name>-component_type`: `"ixc_endpoint"`
- `ixc_endpoint-<custom_name>-id`: VM ID in OpenNebula. Generated from Terraform Manifest
- `ixc_endpoint-<custom_name>-ips`: Dictionary of VM IP addresses: `{<VNet ID in OpenNebula>: <IP address>}`. Generated from Terraform Manifest
