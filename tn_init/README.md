# tn_init

First component of any Trial Network, composed of two sepparate entities: tn_vxlan and tn_bastion:
**tn_vxlan** is the main Virtual Network of the Trial Network and is accessible through the tn_bastion VPN.
**tn_bastion** is a VM managing external access to the Trial Network and hosting various networking services such as:
- DHCP server (not yet)
- NTP server (not yet)
- Public network masquerading
- Port forwarding (not yet)
- DNS server
- VPN Server

> NOTE: Internally this component works as a wrapper of both components within the same ansible playook.

![tn_init](img/tn_init.png)