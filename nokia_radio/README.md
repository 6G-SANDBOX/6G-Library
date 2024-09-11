# NOKIA RADIO UMA

Component to integrate the Nokia Airscale physical equipment with 5G cores deployed inside Trial Networks.
It is done by enabling the necessary routing path through the use of the element called ‘Route Manager’ (already deployed in the site). 
The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- 'mcc': "214" 
- 'mnc': "702"
- 'apn': "internet"
- 'tac': 1
- 's_nssai_sst': 1
- 's_nssai_sd': "000009"
- 'amf_ip': "10.10.12.200"
- 'upf_ip': "10.10.12.201"
    - 
> [!NOTE]  
> Currently this component is only available in the site "uma".