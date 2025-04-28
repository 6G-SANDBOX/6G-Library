# Hardware gNB

TODO
Component to integrate the Nokia Airscale physical equipment with a 5G core already deployed inside the Trial Network.
It is done by enabling the necessary routing path using a [6G-Sandbox route-manager-api](https://marketplace.mobilesandbox.cloud:9443/appliance/service_routemanager) VM expected to be already deployed in the site.
The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:


> [!NOTE]
> Aditional routing configuration that is not dependent on the Trial Network may also need to be done beforehand.

> [!NOTE]
> Component can use Route Manager (or not!)

> [!NOTE]  
> This component aims to replace the now deprecated `nokia_radio` and `berlin_ran` components, with similar usage.

![nokia_radio](https://github.com/6G-SANDBOX/6G-Library/blob/assets/nokia_radio/nokia_radio.png)
