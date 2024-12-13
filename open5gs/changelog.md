# Changelog

## [v0.3.1]

### Changed
- Updated helm chart reference URL to point the 6G-Sandbox dockerhub
- Fixed bug where the one_open5gs_upf_ip wasn't correctly set as an output variable
- added input `one_open5gs_ue_count` 

## [v0.3.0]

### Changed
- Ansible hostname of the OneKE master no longer uses variables. It only added complexity without benefits.
- Output variable `open5gs_metadata_dict` renamed to `metadata_dict` like the rest of the components

## [v0.2.0]

### Changed
- Component follows the new architecture.

## [v0.1.0]

### Added
Initial release of the 6G Library. Changes will be kept on track for now on.