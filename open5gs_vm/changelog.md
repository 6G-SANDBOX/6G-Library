# Changelog
## unreleased
### Added
- New parameter `one_open5gs_vm_install_webui` to install the WEB ui

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
