# loadcore_agent

Deploy a VM with Keysight's LoadCore Agent ready to play.
After booting, the agent is automatically registered to the LoadCore Middleware server.

This component requires a working Loadcore Middleware exposed to the Trial Networks in the site. The Middleware IP address can be provided in the 6G-Sandbox-Sites repository branch of the site.
To set this applinace in your site, please first download either the [base appliance](https://marketplace.mobilesandbox.cloud:9443/appliance/loadcore_agent) or the one with [hugepages](https://marketplace.mobilesandbox.cloud:9443/appliance/loadcore_agent_hugepages). The hugepages appliance is heavier but provides better support for DPDK.


### Sample Trial Network using loadcore_agent with an open5gs_k8s 5G Core

![loadcore_open5gs_k8s](https://github.com/6G-SANDBOX/6G-Library/blob/assets/loadcore/loadcore_open5gs_k8s.png)

### Sample Trial Network using loadcore_agent with an open5gs_vm 5G Core

![loadcore_open5gs_vm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/loadcore/loadcore_open5gs_vm.png)

## Maintainers

- Jesus Macias Portela <jesus.maciasportela@telefonica.com>
- Ana Isabel Lara García <anaisabel.laragarcia@telefonica.com>
- Raúl Luzón González <raul.luzongonzalez@telefonica.com>
- Raúl Macián Castillo <raul.maciancastillo@telefonica.com>
- Filip Ivanovich <filip.ivanovic@keysight.com>
- Abhishek Bakshi <abhishek.bakshi@keysight.com>

## Short Description

Deploys a VM with Keysight's LoadCore Agent to register with the LoadCore Middleware server.

## Long Description

The **LoadCore Agent** component deploys a Virtual Machine preconfigured with Keysight’s LoadCore Agent software.  
Once booted, the VM automatically registers itself with a central LoadCore Middleware server configured in the site's variables.

This tool is used for performance and network testing, especially in scenarios requiring simulation of realistic user traffic.  
Users can deploy variants of the appliance, including one with support for hugepages.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- Keysight  
- LoadCore  
- agent  
- traffic generator  
- performance testing  
- network testing

---

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.  
Each variable has a short description of what it does for informational purposes.

| Variable                   | Description                                                                 |
|----------------------------|-----------------------------------------------------------------------------|
| `loadcore_middleware_ip`   | IP address of the LoadCore Middleware in your site                          |
| `template_id`              | (DEPRECATED) ID of the legacy VM template for LoadCore Agent                |
| `image_id`                 | (DEPRECATED) ID of the legacy VM image for LoadCore Agent                   |
| `base_appliance.template_id` | ID of the base appliance VM template (no hugepages)                     |
| `base_appliance.image_id`    | ID of the base appliance VM image (no hugepages)                        |
| `hugepages_appliance.template_id` | ID of the hugepages appliance VM template                       |
| `hugepages_appliance.image_id`    | ID of the hugepages appliance VM image                          |

---

## Input variables

Describes the variables shown in the TNLCM to be filled by the experimenter and included in a TN descriptor.  
Most variables just serve to overwrite a private/default value, but others (mainly mandatory ones) serve to define dependencies between previously deployed components.

| Variable                     | Description                                                                                             | Type                    | Default Value | Choices | Required When |
|------------------------------|---------------------------------------------------------------------------------------------------------|-------------------------|---------------|---------|----------------|
| `one_loadcore_agent_networks` | Ordered list of VNet names to attach the VM to. First is used by Jenkins to configure the VM.         | list[tn_vxlan or vnet]  | ["tn_vxlan"]  | -       | false          |
| `one_loadcore_agent_hugepages` | Whether to enable hugepages (16x1G) for the VM. If true, hugepages-enabled appliance will be used.   | bool                    | false         | -       | false          |

---

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `loadcore_agent-<custom_name>-component_type`: `"loadcore_agent"`
- `loadcore_agent-<custom_name>-id`: VM ID in OpenNebula. Generated from Terraform Manifest
- `loadcore_agent-<custom_name>-ips`: Dictionary of VM IP addresses: `{<VNet ID in OpenNebula>: <IP address>}`. Generated from Terraform Manifest
