# Changelog

## v0.5.0
### Changed
- Changed references to 5GCore variables from `upf_ip`, `amf_ip` to `upf_n3_ip`, `amf_n2_ip`.
- Variable `one_nokia_radio_open5gs` renamed to `any_nokia_radio_linked_5gcore`.
### Removed
- Component no longer requires a proxy IP address coming from `any_nokia_radio_linked_5gcore`. `tn_bastion` now takes care of all the Trial Networks routing.
### Fixed
- Hardware RAN is now correctly exposed to the Trial Network by adding a NATting exception into the `tn_bastion`.


## v0.4.0
Complete redisign of the component following the changes in tn_bastion and route-manager-api.
### Added
- New public input variables `one_nokia_radio_open5gs`, `one_nokia_radio_start_time` and `any_nokia_radio_duration`.
- Deployment now needs to define an exception on tn_bastion's firewall.
### Changed
- Site-specific variables reduced to only `cp_ip` and `up_ip`.
- The rest of the information is fetched from a linked previously-deployed 5G core with variable `one_nokia_radio_open5gs`.
- Jenkins configures routes via route-manager-api, instead of ssh to the component.
### Removed
- Removal of multiple hardcoded site-specific variables.

## v0.3.0
### Changed
- Component renamed from `nokia_radio_uma` to `nokia_radio`.
- Output variable `nokia_metadata_dict` renamed to `metadata_dict` like in the rest of the components.
### Fixed
- Fix typo in the TNLCM callback that used an `open5gs` variable.


## v0.2.1
### Added
- Initial release of `nokia_radio_uma` component to the 6G-Library.
