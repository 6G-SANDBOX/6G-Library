# Changelog

## v0.4.0
Massive update. Component is no longer deprecated, and tn_init will just use files from it when necessary.
### Added
- New terraform output variable `_net`, allowing the following components to inferr the vnet subnet in CIDR form.
### Changed
- Variable `one_vxlan_netmask` now takes numerical form, instead of CIDR form.
### Removed
- Variables `one_vxlan_gw` and `one_vxlan_dns` have been deleted, as the new tn_bastion fills those roles now.


## v0.3.0
### Deprecated
- Component is still deprecated, move to `tn_init` instead.
### Removed
- Deleted ansible task overwriting `entity_name` with the component type. `entity_name` is now only defined during the Jenkins Pipeline.

## v0.2.0
### Changed
- Component follows the new architecture.
### Deprecated
- Component is now deprecated, try to use `tn_init` instead.

## v0.1.0
### Added
Initial release of the 6G Library. Changes will be kept on track for now on.
