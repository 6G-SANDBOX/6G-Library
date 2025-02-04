# Changelog

## v0.4.0
### Added
- New public input variables `one_bastion_vpn_clients` and `one_bastion_vpn_allowedips`.
- New terraform output variable `_net`, allowing the following components to inferr the vnet subnet in CIDR form.
### Changed
- Variable `one_vxlan_netmask` now takes numerical form, instead of CIDR form.
### Removed
- Public input variable `one_bastion_wireguard_allowedips` has been deleted.
- Variables `one_vxlan_gw` and `one_vxlan_dns` have been deleted, as the new tn_bastion fills those roles now.

## v0.3.0
### Removed
- Deleted ansible task overwriting `entity_name` with the component type. `entity_name` is now only defined during the Jenkins Pipeline.

## v0.2.0
### Added
- Initial release of `tn_init` component to the 6G-Library. 
