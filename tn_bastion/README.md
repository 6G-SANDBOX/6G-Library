# tn_bastion

The **tn_bastion** 6G-Library component is the **bootstrap Virtual Machine** of any Trial Network (TN), enabling external access and providing key network services.
It manages external access to the TN and hosts various networking services as defined in Features.

> ⚠️ **NOTE:** It is recommended to use the [`tn_init`](../tn_init) component instead, which deploys both `tn_bastion` and `tn_vxlan` together as a single setup.

![tn_bastion](https://github.com/6G-SANDBOX/6G-Library/blob/assets/tn_bastion/tn_bastion.png)

## 🛠️ Features

This bastion VM includes several critical networking services:

- Public network masquerading (NAT)
- DNS server
- VPN server (Wireguard)
- SSH jump server for TN access
- [Planned] DHCP server
- [Planned] NTP server
- [Planned] Port forwarding

## 🧩 Deployment Details

- Hypervisor: `OpenNebula`
- Appliance: [tn_bastion appliance](https://marketplace.mobilesandbox.cloud:9443/appliance/service_bastion)
- Should be deployed before most other Trial Network components

## 📥 Input Variables

| Variable                      | Description                                                                 | Type | Default | Required |
|------------------------------|-----------------------------------------------------------------------------|------|---------|----------|
| `one_bastion_vpn_clients`    | Number of VPN client configurations to generate (Wireguard)                 | int  | 1       | No       |
| `one_bastion_vpn_allowedips` | Comma-separated subnets clients will route to through VPN                   | str  | TN subnet (e.g., `tn_vxlan`) | No       |

## 📤 Terraform Outputs

| Output Name                 | Description                                                |
|----------------------------|------------------------------------------------------------|
| `__tn_bastion__id`         | OpenNebula VM ID                                           |
| `__tn_bastion__ips`        | Dictionary of assigned IP addresses                        |
| `tn_dns_token`             | Token for authenticating to the internal DNS service       |
| `tn_routemanager_token`    | Token for accessing the Route Manager API                  |
| `tn_ssh_public_key`        | SSH public key to inject into TN VMs for access by `tnuser`|

