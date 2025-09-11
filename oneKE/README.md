# oneKE

**oneKE** deploys a Kubernetes cluster in OpenNebula, based on the [oneKE service](https://github.com/OpenNebula/one-apps/wiki/oneke_intro) maintained by OpenNebula.

The cluster includes 6 virtual machines: `vnf_0`, `master_0`, `worker_0`, `storage_0`, `storage_1`, and `storage_2`.

> ⚠️ **NOTE:** Cluster customization will be progresivelly incorporated as the OpenNebula Terraform provider gets more functionalities

![oneKE](https://github.com/6G-SANDBOX/6G-Library/blob/assets/oneKE/oneKE.png)

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>

## Short Description

Deploys a Kubernetes cluster in OpenNebula using the official oneKE service.

## Long Description

Kubernetes cluster based on the oneKE service created and maintained by OpenNebula.  
It creates a Rancher-based Kubernetes cluster with 6 different VMs: `vnf_0`, `master_0`, `worker_0`, `storage_0`, `storage_1`, and `storage_2`.

> NOTE: Cluster customization will be progressively incorporated as the OpenNebula Terraform provider gets more functionalities.

More info in the official documentation: [oneKE Intro](https://github.com/OpenNebula/one-apps/wiki/oneke_intro)

## Hypervisors

- one

## Depends on

- tn_bastion
- vnet
- [tn_vxlan, vnet]

## Tags

- oneKE
- kubernetes
- cluster
- OpenNebula

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined and errors if attempting to deploy the component without them.

| Variable | Description |
|----------|-------------|
| `131.template_id` | ID of the OneKE v1.31 service template to use in your OpenNebula environment |
| `131a.template_id` | ID of the OneKE v1.31 Airgapped service template to use in your OpenNebula environment |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| `one_oneKE_external_vnet` | Name of the VNet used to expose the OneKE cluster | tn_vxlan or vnet | "tn_vxlan" | - | false |
| `one_oneKE_internal_vnet` | Name of the VNet used to interconnect the OneKE nodes | vnet | - | - | true |
| `one_oneKE_version` | Version of the OneKE Appliance to deploy | str | "131" | ["129","131","131a"] | false |
| `one_oneKE_multus` | Enable Multus, a CNI that enables attaching multiple network interfaces to pods | bool | true | - | false |
| `one_oneKE_cni_plugin` | Primary CNI to enable for pod connectivity | str | "canal" | ["cilium", "calico", "canal"] | false |
| `one_oneKE_cilium_range` | Pool of IPs for Cilium’s BGP control plane | str | null | - | false |
| `one_oneKE_metallb` | Enable MetalLB for bare-metal LB services | bool | true | - | false |
| `one_oneKE_metallb_range` | Range of IPs for MetalLB to allocate Kubernetes services | str | null | - | false |
| `one_oneKE_longhorn` | Enable Longhorn for persistent storage | bool | true | - | false |
| `one_oneKE_traefik` | Enable Traefik ingress controller | bool | false | - | false |
| `one_oneKE_nginx_passthough` | Enable/disable SSL passthrough in ingress-nginx | bool | false | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `oneKE-id`: Service ID in OpenNebula. Generated from Terraform Manifest
- `oneKE-roles`: Array with service roles information. Generated from Terraform Manifest
- `oneKE-node_ids`: Dictionary of VM IDs in OpenNebula: key='VM name', value='the VM ID'
- `oneKE-node_ips`: Dictionary of VM addresses: key='VM name', value='the VM main address'
- `oneKE-oneKE_metadata`: Object with internal metadata such as network references and enabled plugins.
