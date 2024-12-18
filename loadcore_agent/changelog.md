# Changelog

## [v0.3.1]

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

## [v0.2.2]

### Added
- New component loadcore_agent