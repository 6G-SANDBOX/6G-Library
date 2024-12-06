# Changelog

## [unreleased]

### Added

- initial version of Open5gCore VM compoent
- Add Caddy to expose the WebCLI of Open5GCore
- Automatically configure a route on the `bastion` for the `ue_subnet` back to the core-VM

### Changed

- Change default `MTU` on DN network to 1406 to work with tn_vxlan `GUEST_MTU` of 1450


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