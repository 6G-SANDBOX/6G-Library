# tn_bastion (LEGACY)

Legacy bootrap VM in any Trial Network. tn_init is the new recommended component for starting Trial Networks
It manages external access to the TN and hosting various networking services such as:
- DHCP server
- NTP server
- Public network masquerading
- Port forwarding
- DNS server
- VPN Server

NOTE: Current tn_bastion only hosts the VPN Server


KNOWN LIMITATIONS: You can only be connected to one TN at the same time