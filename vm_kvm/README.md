# vm_kvm

The **vm_kvm** 6G-Library component deploys a general-purpose Virtual Machine inside your Trial Network based on Ubuntu 22.04 LTS.

![vm_kvm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/vm_kvm/vm_kvm.png)

## Instance Sizes
Through variable "one_vm_kvm_size", five different types of instances can be deployed

| Size         | Memory | vCPUs | Storage |
|--------------|--------|-------|---------|
| extra_large  | 32 GiB | 8     | 100 GiB |
| large        | 16 GiB | 4     | 50 GiB  |
| medium       | 8 GiB  | 4     | 25 GiB  |
| small        | 4 GiB  | 2     | 15 GiB  |
| very_small   | 2 GiB  | 1     | 10 GiB  |

---

## Input Variables

| Name | Type | Default | Description |
|------|------|---------|-------------|
| `one_vm_kvm_networks` | `list[tn_vxlan or vnet]` | `["tn_vxlan"]` | Ordered list of Virtual Network names the VM will be part of. The first one is used by Jenkins to reach and configure the VM. Usually, `tn_vxlan` is sufficient. |
| `one_vm_kvm_size` | `str` | `"samll"` | Instance type to deploy according to the resource requirements. See table above for sizes. Options: `extra_large`, `large`, `medium`, `small`, `very_small`. |

---

## Terraform Outputs

| Output | Description |
|--------|-------------|
| `__<component>__component_type` | `"vm_kvm"` |
| `__<component>__id` | VM ID in OpenNebula |
| `__<component>__ips` | Dictionary of VM IP addresses `{<vnet_id>: <ip>}` |

---