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

---

## 🔧 Deployment Details

- Hypervisor: `OpenNebula`
- No site-specific variables are required directly by `tn_init`, but the underlying components may require them (`tn_vxlan` and `tn_bastion`).

## 📥 Input Variables

| Variable                     | Description                                                                                           | Type   | Default       | Required |
|-----------------------------|-------------------------------------------------------------------------------------------------------|--------|----------------|----------|
| `one_vxlan_netmask`         | Network mask of the `tn_vxlan` subnet                                                                | int    | 24             | No       |
| `one_vxlan_first_ip`        | First IP address of the VNet range                                                                   | str    | 192.168.199.1  | No       |
| `one_vxlan_address_size`    | Total number of IPs in the VNet range                                                                | int    | 254            | No       |
| `one_bastion_vpn_clients`   | Number of VPN clients to generate configs for                                                        | int    | 1              | No       |
| `one_bastion_vpn_allowedips`| Comma-separated subnets accessible via the bastion VPN                                                | str    | tn_vxlan subnet| No       |

## 📤 Terraform Outputs

| Output                         | Description                                                                 |
|--------------------------------|-----------------------------------------------------------------------------|
| `__tn_vxlan__id`               | ID of the created VNet in OpenNebula                                       |
| `__tn_vxlan__net`              | Subnet CIDR of the VNet                                                    |
| `__tn_bastion__id`             | ID of the bastion VM in OpenNebula                                         |
| `__tn_bastion__ips`           | Dictionary with the bastion's IPs in each VNet                             |
| `tn_dns_token`                 | Token used to authenticate against the Bastion's DNS server                |
| `tn_routemanager_token`        | Token used for authenticating to the Bastion’s route-manager API           |
| `tn_ssh_public_key`            | Public key to inject into VMs for SSH access as `tnuser`                   |
