# OpenSAND Satellite Terminal (`opensand_st`)

![opensand](https://github.com/6G-SANDBOX/6G-Library/blob/assets/opensand/opensand.png)

OpenSAND is an open-source emulator for satellite communication systems (DVB-RCS2/DVB-S2).  
This component represents the **Satellite Terminal** entity in an emulated satellite network setup and is intended for use in the 6G-SANDBOX Trial Network environment.

## Maintainers

- Nikolaos Gkatzios <ngkatzios@infolysis.gr>  
- Konstantinos Fragkos <cfragos@infolysis.gr>

## Short Description

Emulated satellite terminal entity for the OpenSAND (NTN Emulator) component.

## Long Description

This component emulates a complete DVB-RCS2 – DVB-S2 system and acts as a Satellite Terminal within an emulated satellite network.  
It is intended to be deployed in the Trial Network and must be interconnected with the `opensand_sat` (Satellite entity) and the `opensand_gw` (Gateway entity), which should be deployed beforehand.  
This component uses a custom OpenSAND appliance that must be available in your site.  
Documentation is available at: [https://github.com/CNES/opensand](https://github.com/CNES/opensand)

## Hypervisors

- one

## Depends on

- tn_bastion  
- opensand_sat  
- opensand_gw

## Tags

- VM  
- appliance  
- NTN

## Site-specific variables

| Variable     | Description                                                                       |
|--------------|-----------------------------------------------------------------------------------|
| `template_id` | ID of the OpenSAND VM template to use in your OpenNebula environment             |
| `image_id`    | ID of the OpenSAND VM image to use in your OpenNebula environment                |

## Input variables

| Variable                  | Description                                                                                   | Type                   | Default     | Choices | Required When |
|---------------------------|-----------------------------------------------------------------------------------------------|------------------------|-------------|---------|----------------|
| `one_opensand_st_networks` | Ordered list of Virtual Network names the VM will be part of. First one should be tn_vxlan. | list[tn_vxlan or vnet] | ["tn_vxlan"] | -       | false          |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage and made available for future components.  
Each output variable has a short description of its purpose.

- `__entity_name__component_type`: "opensand_st"  
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"  
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
