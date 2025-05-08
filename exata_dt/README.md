# loadcore_agent

Deploy a VM with Keysight's LoadCore Agent ready to play.
After booting, the agent is automatically registered to the LoadCore Middleware server.

This component requires a working Loadcore Middleware exposed to the Trial Networks in the site. The Middleware IP address can be provided in the 6G-Sandbox-Sites repository branch of the site.
To set this applinace in your site, please first download either the [base appliance](https://marketplace.mobilesandbox.cloud:9443/appliance/loadcore_agent) or the one with [hugepages](https://marketplace.mobilesandbox.cloud:9443/appliance/loadcore_agent_hugepages). The hugepages appliance is heavier but provides better support for DPDK.


### Sample Trial Network using loadcore_agent with an open5gs_k8s 5G Core

![loadcore_open5gs_k8s](https://github.com/6G-SANDBOX/6G-Library/blob/assets/loadcore/loadcore_open5gs_k8s.png)

### Sample Trial Network using loadcore_agent with an open5gs_vm 5G Core

![loadcore_open5gs_vm](https://github.com/6G-SANDBOX/6G-Library/blob/assets/loadcore/loadcore_open5gs_vm.png)
