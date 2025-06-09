# xext

The **xrext** 6G-Library component deploys a Virtual Machine inside your Trial Network based on Ubuntu 22.04 LTS.

![vm_kvm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/xrext/xrext_concept.png)

## Maintainers

- Joaquín Pérez <joaquin.perez_otero.ext@nokia.com>

## Short Description

Deploys an OpenNebula virtual machine with Python modules installed to consume XR extensions of NaC REST API.

## Long Description

The XRext 6G-Library component allows the experimenters to deploy their own virtual machine for 
testing XR sessions, using Nokia's Network as Code (NaC) API, on top of the 5G network deployed 
in the UMA site.

The virtual machine deployed by the appliance should only interact with the NaC server and the 
CAPIF server that are already serving the UMA site, through the corresponding Python scripts. 
The locations and credentials are obtained from the config.ini file, which must be located in 
the same directory as these scripts. The appliance already provides a config.ini file with the 
information needed to access the services deployed on the UMA site.

## Hypervisors

- one

## Depends on

- tn_bastion
- [tn_vxlan, vnet]

## Tags

- xrext
- ubuntu 22.04
- VM
- Python
- NaC
- XR

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

- `template_id`: ID of the Ubuntu 22.04 LTS VM template to use in your OpenNebula environment  
- `image_id`: ID of the Ubuntu 22.04 LTS VM image to use in your OpenNebula environment  

## Input variables

| variable           | description                                                                                                                                                    | type                    | default_value | choices                                  | required_when |
|--------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|----------------|------------------------------------------|----------------|
| one_vm_kvm_networks | Ordered list of Virtual Network names the VM will be part of. The first Virtual Network is used by Jenkins to reach and configure the VM.                     | list[tn_vxlan or vnet]  | ["tn_vxlan"]   | -                                        | false          |

## Generated terraform outputs
Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.

- xrext-id: "OpenNebula VM ID."
- xrext-ips: "OpenNebula VM Network Assignments."
- tn_ssh_private_key: "Trial Network VM's private key for user *tnuser*."
- tn_ssh_public_key: "Trial Network VM's public key for user *tnuser*."
