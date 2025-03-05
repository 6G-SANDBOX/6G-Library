# Changelog

## unreleased
### Changed
- Open5GS upgraded from `v2.7.0` to `v2.7.2`. Chart version upgraded from `v2.2.2` to `v2.2.6`.
  

## v0.4.0
### Changed
- Componet renamed from `open5gs` to `open5gs_k8s`. All component references have been updated following this change.
- Updated helm chart reference URL to directly point to the 6G-Sandbox dockerhub.
- Input variable `one_open5gs_k8s_oneKE` renamed to `one_open5gs_k8s_target`.
### Added
- New input variable `one_open5gs_k8s_ue_count`, allowing to create multiple network slices for different UEs.
- open5gs web interface is now registered in the new tn_bastion DNS server
### Removed
- Removed variable `one_open5gs_k8s_webui_subdomain`. Subdomain is now deduced from the one from the `tn_bastion` and the `entity_name`.
### Fixed
- Variable `one_open5gs_upf_ip` is now correctly set as an output variable.


## v0.3.0
### Changed
- Ansible hostname of the OneKE master no longer uses variables. It only added complexity without benefits.
- Output variable `open5gs_metadata_dict` renamed to `metadata_dict` like in the rest of the components.


## v0.2.0
### Changed
- Component follows the new architecture.


## v0.1.0
### Added
Initial release of the 6G Library. Changes will be kept on track for now on.
