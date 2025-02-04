# Changelog

## unreleased
### Added
- New private variable allowing to disable legacy user ixia

### Changed
- `loadcore_agent` component now uses an appliance with the OpenNebula contextualization scripts, so both user jenkins and tnuser are now created
- Temprarily disabled deployment with Terraform due to the requirements of deploying VMs with multiple interfaces of same vnet
- Outputs are also temorarily disabled until the Terraform provider gets fixed

## v0.3.0
### Added
- Initial release of `loadcore_agent` component to the 6G-Library.
### Security
- Until the appliance gets the right contextualization, user jenkins is no longer created in this component due to increased complexity and conflicts with cloud-init
