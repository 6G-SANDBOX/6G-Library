# Changelog

## v0.5.1
### Added
- The image of the deployed VMs can now be resized. Default's size is 10GiB.
- VM is now also included in tnuser's ssh config file.
- VM gets registered in the `tn_bastion`'s DNS server with an A record.
### Changed
- Augmented default memory size to 18 GiB in order to support the hugepages appliance.
### Fixed
- Component `loadcore_agent` now correctly works with both the hugepages appliance, and the "light" one.
- Component is now completelly deployable by only using Terraform, removing the previous ansible workaround.


## v0.5.0
### Fixed
- Add firewall exception to be able to connect to the middleware through the `tn_bastion`.


## v0.4.0
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
