# Changelog
## v1.16.2.0
### changed
- upgraded runner version 2.0.0
- upgraded to KS8500 cloud V2
## v1.16.1.2
### Fixed
- Removed duplicated tn_bastion  in .tnclm/public.yaml
- Fixed disk resize method.
## v1.16.1
- upgraded runner version 1.16.1
## v1.15.1
### Changed
- upgraded runner version 1.15.1
- upgraded opentap version 9.28.3
## v1.14.0
### Changed
- upgraded runner version 1.14.1
## v1.13.0.1
### Changed
- updated sample_tnlcm_descriptor.yaml
- update component_playbook.yaml with task to increase VM disc size
- update component_playbook.yaml with task to install additional packages
- update component_playbook.yaml with tasks to add firewall exceptions
- update runner version to 1.14.0
### Fixed
- START_SCRIPT_BASE64 defined in ks8500_runner.tfj2 
## v1.13.0
### Changed
- Software updated to version `1.30.0`.
- OpenTap framework updated to version `9.27.1`.


## v1.11.4.0
### Added
- Python and pip for plugins that use python venv
- Tshark for plugins that use it for data decoding
### Changed
- Software updated to version `v1.11.4.0`.
- OpenTap framework updated to latest stable version.
- Docker mount of opentap-images to give visibility on host and stability on restart.
- Version number is now the 3 runner verion digits plus a fourth digit to denote one Component changes.

## v0.3.0
### Added
- Initial release of `ks8500_runner` component to the 6G-Library. Basic deployment and registration with the KS8500 backend has been tested in uma. Verifying actual usage of the Runner is TBD
