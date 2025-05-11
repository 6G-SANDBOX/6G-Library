# vnet

The **vnet** 6G-Library component creates a private Virtual Network inside the Trial Network, allowing experimenters to interconnect multiple components using isolated VXLAN subnets instead of the default `tn_vxlan`.

The Virtual Network is of type VXLAN, and spans the subnet formed with `one_vnet_first_ip` and `one_vnet_netmask`.
A Virtual Network ranges `one_vnet_address_size` successive IPs starting from `one_vnet_first_ip`.

> [!NOTE]
> For a typical `/24` network (e.g., starting at `X.X.X.1`), the maximum valid address count is 254.

![vnet](https://github.com/6G-SANDBOX/6G-Library/blob/assets/vnet/vnet.png)

---

## Input Variables

| Variable              | Description                                                                | Type   | Default         | Required |
|-----------------------|----------------------------------------------------------------------------|--------|----------------|----------|
| `one_vnet_first_ip`   | First IP address in the VNet range                                         | `str`  | `"10.21.12.1"` | No       |
| `one_vnet_netmask`    | Network mask of the subnet                                                 | `int`  | `24`           | No       |
| `one_vnet_address_size` | Number of usable IPs starting from `first_ip`                            | `int`  | `100`          | No       |
| `one_vnet_gw`         | Default gateway IP address                                                 | `str`  | `null`         | No       |
| `one_vnet_dns`        | Space-separated list of DNS servers                                        | `str`  | `null`         | No       |


---

## Terraform Outputs

| Output | Description |
|--------|-------------|
| `__<component>__component_type` | `vnet` |
| `__<component>__id` | VNet ID in OpenNebula |
| `__<component>__net` | Subnet of the VNet in CIDR format (e.g., `10.21.12.1/24`) |
