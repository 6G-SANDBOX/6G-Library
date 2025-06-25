# tn_vxlan

Bootstrap component of any Trial Network. Virtual Network reachable to the experimenter, and used by most other compoents to communicate to each other inside the Trial Network.
Default gateway of this network will be the tn_bastion, which is also the main VPN entrypoint to the Trial Network.

> ⚠️ **NOTE:** It is recommended to use the [`tn_init`](../tn_init) component instead, which deploys both `tn_bastion` and `tn_vxlan` together as a single setup.


## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>

## Short Description

Bootstraps the Trial Network with an initial Virtual Network (tn_vxlan). Deploying tn_init instead is the recommended approach.

## Long Description

Bootstrap component of any Trial Network. Virtual Network reachable to the experimenter, and used by most other components to communicate to each other inside the Trial Network.  
Default gateway of this network will be the tn_bastion, which is also the main VPN entrypoint to the Trial Network.  
Deploying `tn_init` instead is the recommended approach, as it serves as a wrapper of both `tn_vxlan` and `tn_bastion`.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- vxlan
- vnet
- virtual network
- tn_vxlan
- trial network

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

*(None defined directly — site variables from `tn_bastion` and other related components may still apply.)*

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.

| variable               | description                                      | type | default_value      | choices | required_when |
|------------------------|--------------------------------------------------|------|---------------------|---------|----------------|
| one_vxlan_netmask      | Network mask of the tn_vxlan subnet             | int  | 24                  | -       | false          |
| one_vxlan_first_ip     | First IP address of the VNet range              | str  | "192.168.199.1"     | -       | false          |
| one_vxlan_address_size | Amount of IP addresses that compose the VNet range | int  | 254                 | -       | false          |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  
`__entity_name__` refers to the name of the component inside the TN.  
Actual terraform output uses dashes which are a forbidden character for Ansible variables.  
e.g. if a component’s name is `vm_kvm-myubuntu`, its ID saved as a terraform output will be `vm_kvm_myubuntu-id`.

- `__tn_vxlan__id`: "VNet ID in Opennebula. Generated from Terraform Manifest"
- `__tn_vxlan__net`: "Subnet of the VNet in CIDR format. Generated from Terraform Manifest"
