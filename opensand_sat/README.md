# OpenSAND Satellite (`opensand_sat`)

![opensand](https://github.com/6G-SANDBOX/6G-Library/blob/assets/opensand/opensand.png)

OpenSAND is an open-source emulator for satellite communication systems (DVB-RCS2/DVB-S2).  
This component represents the **Satellite** entity in an emulated satellite network setup and is intended for use in the 6G-SANDBOX Trial Network environment.

## Maintainers

- Nikolaos Gkatzios <ngkatzios@infolysis.gr>  
- Konstantinos Fragkos <cfragos@infolysis.gr>

## Short Description

Emulated satellite entity for the OpenSAND (NTN Emulator) component.

## Long Description

This component is able to emulate a complete DVB-RCS2 – DVB-S2 system and thus it provides an emulated satellite network system.  
It can be deployed in the Trial Network and create an emulated Satellite entity that can be interconnected with various other components, depending on the Trial Network’s topology.  
There is a mandatory interconnection with an emulated Gateway entity (`opensand_gw`) and an emulated Satellite Terminal entity.  
Documentation is available at: [https://github.com/CNES/opensand](https://github.com/CNES/opensand)

## Hypervisors

- one

## Depends on

- tn_bastion

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
| `one_opensand_sat_networks` | Ordered list of Virtual Network names the VM will be part of. First one should be tn_vxlan. | list[tn_vxlan or vnet] | ["tn_vxlan"] | -       | false          |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage and made available for future components.  
Each output variable has a short description of its purpose.

- `__entity_name__component_type`: "opensand_sat"  
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"  
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
