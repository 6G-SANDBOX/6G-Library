# Changelog

## [v0.3.1]

### Changed
- Variable `one_bastion_wireguard_allowedips` promoted as public and user-configurable

## [v0.3.0]

### Removed
- Deleted ansible task overwriting `entity_name` with the component type. `entity_name` is now only defined during the Jenkins Pipeline

## [v0.2.0]

### Added
- New component to bootstrap the Trial Network instead of running first `tn_vxlan` and then `tn_bastion`.