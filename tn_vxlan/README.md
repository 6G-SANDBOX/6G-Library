# tn_vxlan

Bootstrap component of any Trial Network. Virtual Network reachable to the experimenter, and used by most other compoents to communicate to each otehr inside the Trial Network.
Default gateway of this network will be the tn_bastion, which is also the main VPN entrypoint to the Trial Network.
Deploying `tn_init` instead is the recommended approach, as it serves as a wrapper of both tn_vxlan and tn_bastion.
