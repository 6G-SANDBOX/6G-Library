# RAN ACCESS for Berlin Platform

Component to integrate the RAN physical equipment with 5G cores deployed inside Trial Networks.
It is done by enabling the necessary routing path through the use of the element called ‘Route Manager’ (already deployed in the site). 
The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- `mcc`: "999" 
- `mnc`: "38"
- `apn`: "internet"
- `tac`: 122
- `s_nssai_sst`: 1
- `s_nssai_sd`: ""
- `amf_ip`: "10.10.11.200"
- `upf_ip`: "10.10.11.201"

> [!NOTE]  
> Currently this component is only available in the site "fokus".