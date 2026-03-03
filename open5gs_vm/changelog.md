# Changelog

## v1.1.0
### Added
- The DNS server which is send to the UE's can now be configured using a site variable or a public variable.
### Added
- New input variabe `one_open5gs_vm_loglevel` to set the log-level of the open5gs components
### Changed
- disable `open5gs-seppd` which was unused and filled the logfile
- The DNS server which is send to the UE's can now be configured using a site variable
### Fixed
- fix ansible syntax for installing the webUI

## v1.0.0
### Added
- New input variable `one_open5gs_vm_install_webui` to enable the installation of the Web UI.
### Changed
- Output dictionary `metadata` renamed to `5gcore_metadata`.
- Upgraded Open5GS from version `v2.7.2` to `v2.7.6`.
### Fixed
- disbale sending of icmp redirect messages for the UPF


## v0.5.1
### Added
- New input variable `one_open5gs_vm_install_webui` in `open5gs_vm`, enabling the installation of the webUI.

## v0.5.0
### Added
- Component is now responsible to expose its AMF and UPF interfaces, by adding rules and firewall exceptions to `tn_bastion`.
- New parameter `one_open5gs_vm_use_nightly` to install the nightly version of open5gs (Needed for MOCN).
### Changed
- Renamed variable `one_open5gs_vm_amf_ip` to `one_open5gs_vm_amf_n2_ip`.
- Renamed variable `one_open5gs_vm_upf_ip` to `one_open5gs_vm_upf_n3_ip`.
### Fixed
 - Remove hardcoded ip (10.45.0.1) from the `ogstun` interface.

## v0.4.0
### Added
- Initial release of `open5gs_vm` component to the 6G-Library. 
