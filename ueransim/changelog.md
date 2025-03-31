# Changelog

## unreleased
### Added
- New variable `one_ueransim_mode` allowing to switch the behabiour of the component between gNB, UE or both, and relegating `run_gnb` and `run_ue` variables into simply enabling the serfice or not.
- New variable `one_ueransim_ue_msin`, replacing `one_ueransim_ue_supi`.
### Changed
- Variables `one_ueransim_run_gnb` and `one_ueransim_run_ue` are now boolean and false by default.
- Variables `one_ueransim_gnb_linked_open5gs` and `one_ueransim_gnb_linked_gnb` are now optional.
- Variable `one_ueransim_ue_op` renamed to `one_ueransim_ue_opc`.
- Changed reference to 5GCore variable from `amf_ip` to `amf_n2_ip`.
### Deprecated
- Variable `one_ueransim_ue_supi` removed from input variables, but still used. Will be removed with the next release of the UERANSIM appliance.
- Variables `one_ueransim_ue_configured_nssai_sst`, `one_ueransim_ue_configured_nssai_sd`, `one_ueransim_ue_default_nssai_sst` and `one_ueransim_ue_default_nssai_sd` temorarily commented out, until they are useful.
### Removed
- Variable `one_ueransim_gnb_proxy` is no longer used, as the bastion will take care of all the Trial Networks routing.
### Fixed
- Variable `one_ueransim_gnbsearchlist` correctly sets is IP in BOTH mode, and can take the special value 'locahost' which will be internally transformed into the VMs first IP.

## v0.4.0
### Changed
- Input variable `one_ueransim_gnb_linked_open5gs` now supports `upf_p4_sw` and `open5gs_vm` and `open5gcore_vm` as component types.
### Fixed
- Variable `gnbSearchList` of the ueransim-UE now uses the correct ip address of the componnet when in BOTH mode (Temporary change until the appliance fixes it)

## v0.3.0
### Fixed
- Fixed image visualization in README.md and ok_result.md

## v0.2.0
### Changed
- Component follows the new architecture.
- Updated README.md

## v0.1.0
### Added
Initial release of the 6G Library. Changes will be kept on track for now on.
