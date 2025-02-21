# vnet

Private Virtual Network inside the Trial Network.
Allows the experimenter to interconnect different components using private networks instead of the main tn_vxlan.
The Virtual Network is of type VXLAN, and spans the subnet formed with `one_vnet_first_ip` and one_vnet_netmask.
A Virtual Network ranges `one_vnet_address_size` successive IPs starting from `one_vnet_first_ip`.
The maximum value for a full /24 subnet starting from X.X.X.1 should be 254.