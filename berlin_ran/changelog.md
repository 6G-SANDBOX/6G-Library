# Changelog

## v0.5.0
### Added
 - Support for NAT exceptions when connecting the RAN subnet to the TN.
### Changed
- Variable `any_berlin_ran_linked_open5g` renamed to `any_berlin_ran_linked_5gcore`.
 - Updated 5G core medatada output to new format.
 - Fixed default TAC used in Berlin Huawei RAN.
 - Changed interface towards the new bastion v0.4.0.
 - Changed references to 5GCore variables from `upf_ip`, `amf_ip` to `upf_n3_ip`, `amf_n2_ip`.


## v0.4.0
### Added
- Initial release of `berlin_ran` to the 6G-Library, based on component `nokia_radio`.