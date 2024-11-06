# Changelog

## [unreleased]
### Changed
- Variable `one_bastion_wireguard_allowedips` promoted as public and user-configurable

## [v0.3.0]
### Removed
- Deleted ansible task overwriting `entity_name` with the component type. `entity_name` is now only defined during the Jenkins Pipeline

## [v0.2.0] - 2024-06-06
### Added
- New component to bootstrap the Trial Network instead of running first `tn_vxlan` and then `tn_bastion`.

<!-- Change latest version value at every release -->
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.3.0...unreleased
[v0.3.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.1...v0.3.0
[v0.2.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.1.0...v0.2.0



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