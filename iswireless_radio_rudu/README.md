# ISWIRELESS RADIO RUDU

Component to integrate the ISWIRELESS ORAN physical equipment with 5G cores deployed inside Trial Networks.
It is done by enabling the necessary routing path through the use of the element called ‘Route Manager’ (already deployed in the site). 
The configuration exposed by this component should be synchronized with the 5G core and the UE to be used in conjunction:
- `mcc`: "001" 
- `mnc`: "01"
- `apn`: "internet"
- `tac`: 1
- `s_nssai_sst`: 1
- `s_nssai_sd`:
- `cu_ip`: "10.10.12.203"

> [!NOTE]  
> Currently this component is only available in the site "uma".