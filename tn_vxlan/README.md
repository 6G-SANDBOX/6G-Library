# tn_vxlan

Bootstrap component of any Trial Network. Virtual Network reachable to the experimenter, and used by most other compoents to communicate to each other inside the Trial Network.
Default gateway of this network will be the tn_bastion, which is also the main VPN entrypoint to the Trial Network.

> ⚠️ **NOTE:** It is recommended to use the [`tn_init`](../tn_init) component instead, which deploys both `tn_bastion` and `tn_vxlan` together as a single setup.


## 📥 Input Variables

| Variable                  | Description                                                  | Type | Default         | Required |
|--------------------------|--------------------------------------------------------------|------|------------------|----------|
| `one_vxlan_netmask`      | Network mask of the `tn_vxlan` subnet                        | int  | 24               | No       |
| `one_vxlan_first_ip`     | First IP address of the VNet range                           | str  | 192.168.199.1    | No       |
| `one_vxlan_address_size` | Total number of IPs available in the VNet range              | int  | 254              | No       |

---

## 📤 Terraform Outputs

| Output                  | Description                                             |
|-------------------------|---------------------------------------------------------|
| `__tn_vxlan__id`        | VNet ID in OpenNebula. Generated from Terraform Manifest |
| `__tn_vxlan__net`       | Subnet of the VNet in CIDR format                        |

---
