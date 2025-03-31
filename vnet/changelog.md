# Changelog

## v0.5.0
### Changed
- Variable `one_vnet_gw` has now no default variable, and defined no gateway if empty.


## v0.4.0
### Added
- New terraform output variable `_net`, allowing the following components to inferr the vnet subnet in CIDR form.
### Changed
- Variable `one_vnet_netmask` now takes numerical form, instead of CIDR form.
- Variable `one_vnet_dns` has now no default variable, and defines no DNS if empty.


## v0.2.0
### Changed
- Component follows the new architecture.
- Component renamed from `vxlan` to `vnet`.


## v0.1.0
### Added
Initial release of the 6G Library. Changes will be kept on track for now on.
