# oneKE

**oneKE** deploys a Kubernetes cluster in OpenNebula, based on the [oneKE service](https://github.com/OpenNebula/one-apps/wiki/oneke_intro) maintained by OpenNebula.

The cluster includes 6 virtual machines: `vnf_0`, `master_0`, `worker_0`, `storage_0`, `storage_1`, and `storage_2`.

> ⚠️ **NOTE:** Cluster customization will be progresivelly incorporated as the OpenNebula Terraform provider gets more functionalities

![oneKE](https://github.com/6G-SANDBOX/6G-Library/blob/assets/oneKE/oneKE.png)

---

## 🔧 Deployment Details

- **Hypervisor:** `OpenNebula`
- **Dependencies:**
  - Requires `tn_bastion` for SSH access.
  - Requires at least one `vnet` or `tn_vxlan` to provide cluster networking.

---

## 📥 Input Variables

| Variable                      | Description                                                                                                                                                         | Type                          | Required | Default      |
|------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------|----------|--------------|
| `one_oneKE_external_vnet`    | Name of a virtual network used to expose the Kubernetes cluster. Must reference a previously deployed `tn_vxlan` or `vnet` component.                              | `tn_vxlan` or `vnet`          | No       | `"tn_vxlan"` |
| `one_oneKE_internal_vnet`    | Name of a virtual network used to interconnect the cluster nodes internally. Must reference a previously deployed `vnet`.                                          | `vnet`                        | Yes      | -            |
| `one_oneKE_version`          | Version of the OneKE appliance to deploy.                                                                                                                           | `str` (`"129"`, `"131"`, `"131a"`) | No  | `"131"`      |
| `one_oneKE_multus`           | Enable Multus CNI for attaching multiple network interfaces to pods.                                                                                                | `bool`                        | No       | `true`       |
| `one_oneKE_cni_plugin`       | Primary CNI plugin: `cilium`, `calico`, or `canal`.                                                                                                                 | `str`                         | No       | `"canal"`    |
| `one_oneKE_cilium_range`     | CIDR range for Cilium BGP IP pool (only if `cni_plugin` = `cilium`). Must be inside `internal_vnet` subnet but outside its IP range.                               | `str`                         | No       | `null`       |
| `one_oneKE_metallb`          | Enable MetalLB (bare-metal load balancer).                                                                                                                          | `bool`                        | No       | `true`       |
| `one_oneKE_metallb_range`    | IP range for MetalLB to allocate services. Must be inside `internal_vnet` subnet but outside its IP range.                                                         | `str`                         | No       | `null`       |
| `one_oneKE_longhorn`         | Enable Longhorn for persistent storage.                                                                                                                             | `bool`                        | No       | `true`       |
| `one_oneKE_traefik`          | Enable Traefik ingress controller.                                                                                                                                  | `bool`                        | No       | `false`      |
| `one_oneKE_nginx_passthough`| Enable SSL/TLS passthrough in the NGINX ingress controller.                                                                                                          | `bool`                        | No       | `false`      |

---