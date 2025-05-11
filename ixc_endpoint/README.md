# ixc_endpoint

**IxChariot Performance Endpoints** act as controlled traffic sources and receivers, simulating real-world applications like web browsing, video streaming, or file transfers to evaluate how a network handles different traffic types.

Ref: https://support.ixiacom.com/support-links/ixchariot/endpoint-library/platform-endpoints

This component is part of the Trial Network Life Cycle Manager (TNLCM) ecosystem and is deployed as a virtual machine in OpenNebula.

## 🔧 Deployment Details

- Hypervisor: `OpenNebula`
- Appliance: [IxChariot Endpoint Appliance](https://marketplace.mobilesandbox.cloud:9443/appliance/ixc_endpoint)
- Requires the `tn_bastion` component to be deployed beforehand for access.
- Optionally connects to a Registration Server (`one_ixc_endpoint_rs` input parameter).

## 📥 Input Variables

| Variable                 | Description                                                      | Type               | Required |
|-------------------------|------------------------------------------------------------------|-------------------|----------|
| `one_ixc_endpoint_networks` | Ordered list of Virtual Network names the VM will join. Default is `["tn_vxlan"]`. | list[str]          | Optional |
| `one_ixc_endpoint_rs`        | Registration Server IP address to register the endpoint.        | str                | Required |

Example input file:

```yaml
# one_ixc_endpoint_networks: 
#   - tn_vxlan

one_ixc_endpoint_rs: "10.11.31.98"
