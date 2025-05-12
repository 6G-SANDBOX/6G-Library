# tn_init

First component of any Trial Network, composed of two sepparate entities: tn_vxlan and tn_bastion:
- **tn_vxlan** is the main Virtual Network of the Trial Network and is accessible through the tn_bastion VPN.
- **tn_bastion** is a VM managing external access to the Trial Network and hosting various networking services such as:
  - DHCP server (not yet)
  - NTP server (not yet)
  - Public network masquerading
  - Port forwarding (not yet)
  - DNS server
  - VPN Server

> NOTE: Internally this component works as a wrapper of both components within the same ansible playook.

![tn_init](https://github.com/6G-SANDBOX/6G-Library/blob/assets/tn_init/tn_init.png)

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>

## Short Description

Bootstraps the Trial Network with an initial Virtual Network (tn_vxlan) and a bastion VM (tn_bastion)

## Long Description

First component of any Trial Network, composed of two separate entities: `tn_vxlan` and `tn_bastion`:  
**tn_vxlan** is the main Virtual Network of the Trial Network and is accessible through the `tn_bastion` VPN.  
**tn_bastion** is a VM managing external access to the Trial Network and hosting various networking services such as:

- DHCP server (not yet)
- NTP server (not yet)
- Public network masquerading
- Port forwarding (not yet)
- DNS server
- VPN Server

Internally this component works as a wrapper of both components within the same Ansible playbook.

## Hypervisors

- one

## Depends on

*(none)*

## Tags

- init
- bastion
- vxlan
- trial network

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

Currently, site variables from `tn_bastion` and `tn_vxlan` are used instead.

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.

| Variable                   | Description                                                                                                   | Type | Default               | Choices | Required When |
|---------------------------|---------------------------------------------------------------------------------------------------------------|------|-----------------------|---------|----------------|
| one_vxlan_netmask         | Network mask of the tn_vxlan subnet                                                                           | int  | 24                    | -       | false          |
| one_vxlan_first_ip        | First IP address of the VNet range                                                                            | str  | "192.168.199.1"       | -       | false          |
| one_vxlan_address_size    | Amount of IP addresses that compose the VNet range                                                            | int  | 254                   | -       | false          |
| one_bastion_vpn_clients   | Number of VPN client configurations to generate for simultaneous TN access                                   | int  | 1                     | -       | false          |
| one_bastion_vpn_allowedips| Comma-separated list of subnets that client configurations will route to the bastion (e.g., tn_vxlan subnet) | str  | "The subnet of tn_vxlan" | -    | false          |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `__tn_vxlan__id`: "VNet ID in Opennebula. Generated from Terraform Manifest"
- `__tn_vxlan__net`: "Subnet of the VNet in CIDR format. Generated from Terraform Manifest"
- `__tn_bastion__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__tn_bastion__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
- `tn_dns_token`: "Token to authenticate to the tn_bastion DNS Server"
- `tn_routemanager_token`: "Bearer token to authenticate to the tn_bastion route-manager-api."
- `tn_ssh_public_key`: "SSH public key to include in TN Virtual Machines for login with user tnuser"
