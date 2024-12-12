# Changelog

## [unreleased]
### Added
- New private variable allowing to disable legacy user ixia

### Changed
- Loadcore component now uses an appliance with the OpenNebula contextualization scripts, so both user jenkins and tnuser are now created
- Temprarily disabled deployment with Terraform due to the requirements of deploying VMs with multiple interfaces of same vnet
- Outputs are also temorarily disabled until the Terraform provider gets fixed

## [v0.3.0]
### Changed
- Until the appliance gets the right contextualization, user jenkins is no longer created in this component due to increased complexity and conflicts with cloud-init

### Fixed
- Jenkins now succesfully connects to the VM and executes the `agent-setup.sh` script as root user.
  

## [v0.2.2] - 2024-09-16
### Added
- New component loadcore_agent

<!-- Change latest version value at every release -->
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.3.0...unreleased
[v0.2.2]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.1.0...v0.2.2

<!-- FIELDS PER VERSION -->
<!--
### Added

- New features

### Changed

- Changes in existing functionality

### Deprecated

- Soon-to-be removed features

### Removed

- Removed features

### Fixed

- Bug fixes

### Security

- Vulnerability warnings
-->