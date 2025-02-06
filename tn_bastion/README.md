# tn_bastion

Bootstrap VM of any Trial Network.
It manages external access to the TN and hosts various networking services such as:
- DHCP server (not yet)
- NTP server (not yet)
- Public network masquerading
- Port forwarding (not yet)
- DNS server
- VPN Server
Deploying `tn_init` instead is the recommended approach, as it serves as a wrapper of both tn_vxlan and tn_bastion.

![tn_bastion](https://github.com/6G-SANDBOX/6G-Library/blob/assets/tn_bastion/tn_bastion.png)
