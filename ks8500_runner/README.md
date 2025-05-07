# ks8500_runner
The KS8500 Runner can be deployed to allow accessing local ressources such as instrument control interfaces during test executions scheduled from the cloud based KS8500 SaaS

KS8500 Runner does not require any additional license. The users have to be a member of one of the reals to deploy a runner in a TN, 
6GSB-Malaga, 6GSB-Berlin, 6GSB-Athen or 6GSB-Finland. Currently, the runner provides access to Loadcore MW and IxChariot MW deployed in a site.

## Maintainers:
- Torsten Iversen <torsten.iversen@keysight.com>
- The Dao <the.dao@keysight.com>
- Álvaro Curto Merino <alvaro.curtomerino@telefonica.com>
## Short_description: 
Deploys a VM with KS8500 Runner
## Long_description:
The KS8500 Runner allows accessing local on premises resources while running a test campaign being scheduled from the KS8500 backend. 
To deploy a runner in a TN, it is required that the users have already got a registration token, which can achieved from the Web UI interface, 
(https://test-automation.pw.keysight.com). In order to access KS8500 Web UI interface, the users need to be registered in one of the realms below
6GSB-Malaga, 6GSB-Berlin, 6GSB-Athen and 6GSB-Finland. To register, the users can simply send an email to the.dao@keysight.com or filip.ivanovic@keysight.com
and indicate which reals the users want to become a member of. Once a runner is deployed, the users can access the Web UI interface to manage test plans, test campaign 

## Hypervisors: 
OpenNeBula (one)
appliances: ["https://marketplace.opennebula.io/appliance/d74a5f80-20bd-013d-0e49-7875a4a4f528"]
## Depends_on:
- tn_init
## Tags:
- KS8500 Runner
- 
## Site-specific variables
site_variables:
loadcore_middleware_ip: IP address of the loadcore middleware in your site
template_id: ID of the Alpine Linux 3.20 VM template to use in your OpenNebula environment
image_id: ID of the Alpine Linux 3.20 VM image to use in your OpenNebula environment

## Input variables
- one_ks8500runner_networks:
  - description: Ordered list of Virtual Network names the VM will be part of. The first Virtual Network is used by Jenkins to reach and configure the VM, so most cases will be fine 
    using always the tn_vxlan
  - type: "list[tn_vxlan or vnet]"
  - default_value: ["tn_vxlan"]
  - required_when: false

- ks8500runner_registration_token: 
  - description: KS8500 users runner registration token for creating the runner
  - type: str
  - required_when: true

- ks8500runner_name: 
  - description: User assigned name to identify the runner in the KS8500 backend
  - type: str
  - default_value: "6gsb_runner"
  - required_when: false

- ks8500runner_backend_url:
  - description: |URL for accessing the KS8500 backend. The runner needs this information to connect
  - type: str
  - default_value: "https://test-automation.pw.keysight.com"
  - required_when: false

- ks8500runner_special_action:
  - description: Select sepcial action
    A valid input would be [none (No special action taken), delete_runner_data (Deletes the runner data to trigger a new registration attempt.
    this may be useful if the runner has been accidentally deleted in the KS8500 backend)]
  - type: str
  - choices: ["none", "delete_runner_data"]
  default_value: "none"
  required_when: false

## Generate terraform output
__entity_name__component_type: "ks8500_runner"
__entity_name__id: "VM ID in OpenNebula. Generated from Terraform Manifest"
__entity_name__ips: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
__entity_name__registration_info: A string with information about the runner registration

