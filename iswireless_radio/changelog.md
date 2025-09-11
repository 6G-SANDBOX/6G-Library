# Changelog

## v1.0.0
### Added
- New input variables `any_iswireless_radio_ru_du` allowing to switch between the previous *gNB* behavious, and **RU-DU** mode, which connects to a virtualized CU instead of a 5G Core
- New input variables `any_iswireless_radio_linked_5gcore` and `any_iswireless_radio_linked_cu`, required only in each mode, and ignored in the other,
- New site variables to be defined by the site administrator.
### Changed
- Output `linked_open5gs` moved to `gnb_metadata.linked_5gcore`.


## v0.5.0
### Changed
- Variable `one_iswireless_radio_open5gs` renamed to `any_iswireless_radio_linked_5gcore`.
- Changed references to 5GCore variables from `upf_ip`, `amf_ip` to `upf_n3_ip`, `amf_n2_ip`.
### Removed
- Component no longer requires a proxy IP address coming from `any_iswireless_radio_linked_5gcore`. `tn_bastion` now takes care of all the Trial Networks routing.
### Fixed
- Hardware RAN is now correctly exposed to the Trial Network by adding a NATting exception into the `tn_bastion`.

## v0.4.0
### Added
- Initial release of `iswireless_radio` component to the 6G-Library. 
