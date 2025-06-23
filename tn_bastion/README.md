# tn_bastion

The **tn_bastion** 6G-Library component is the **bootstrap Virtual Machine** of any Trial Network (TN), enabling external access and providing key network services.
It manages external access to the TN and hosts various networking services as defined in Features.

> ⚠️ **NOTE:** It is recommended to use the [`tn_init`](../tn_init) component instead, which deploys both `tn_bastion` and `tn_vxlan` together as a single setup.

![tn_bastion](https://github.com/6G-SANDBOX/6G-Library/blob/assets/tn_bastion/tn_bastion.png)

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>

## Short Description

Main bridge VM to access the Trial Network. Deploying tn_init instead is the recommended approach.

## Long Description

Bootstrap VM of any Trial Network.  
It manages external access to the TN and hosts various networking services such as:

- DHCP server (not yet)  
- NTP server (not yet)  
- Public network masquerading  
- Port forwarding (not yet)  
- DNS server  
- VPN Server  

Deploying `tn_init` instead is the recommended approach, as it serves as a wrapper of both `tn_vxlan` and `tn_bastion`.

## Hypervisors

- one

## Depends on

*(none)*

## Tags

- bastion
- access
- ssh
- dns
- vpn
- trial network

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

| Variable     | Description                                                                 |
|--------------|-----------------------------------------------------------------------------|
| template_id  | ID of the BASTION VM template to use in your OpenNebula environment         |
| image_id     | ID of the BASTION VM image to use in your OpenNebula environment            |

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.

| Variable                     | Description                                                                                                  | Type | Default                        | Choices | Required When |
|-----------------------------|--------------------------------------------------------------------------------------------------------------|------|--------------------------------|---------|----------------|
| one_bastion_vpn_clients     | For the Wireguard VPN, the number of client configurations to generate.                                     | int  | 1                              | -       | false          |
| one_bastion_vpn_allowedips  | Comma-separated list of subnets that client configurations will route to the bastion (e.g., tn_vxlan subnet).| str  | "The subnet of tn_vxlan"         | -       | false          |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `__tn_bastion__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__tn_bastion__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
- `tn_dns_token`: "Token to authenticate to the tn_bastion DNS Server"
- `tn_routemanager_token`: "Bearer token to authenticate to the tn_bastion route-manager-api"
- `tn_ssh_public_key`: "SSH public key to include in TN Virtual Machines for login with user tnuser"
