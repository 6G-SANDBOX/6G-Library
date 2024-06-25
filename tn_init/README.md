# tn_init

First component of any Trial Network, composed of two sepparate entities: tn_vxlan and tn_bastion:
**tn_vxlan** is the main Virtual Network of the Trial Network and is accessible through the tn_bastion VPN
**tn_bastion** is a VM managing external access to the Trial Network and hosting various networking services such as:
- DHCP server
- NTP server
- Public network masquerading
- Port forwarding
- DNS server
- VPN Server

NOTE: Current tn_bastion only hosts the VPN Server