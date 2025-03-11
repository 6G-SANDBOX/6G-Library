# Changelog

## unreleased
### Added
- NFTables firewall now accepts exception files for NATting at `/etc/nftables/nat_exceptions/*.nft`.

## v0.4.0
Massive update. Component is no longer deprecated, and tn_init will just use files from it when necessary.
### Added
- New public input variables `one_bastion_vpn_clients` and `one_bastion_vpn_allowedips`.
### Removed
- Public input variable `one_bastion_wireguard_allowedips` has been deleted.


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