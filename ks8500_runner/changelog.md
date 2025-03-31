# Changelog
## v1.13.0
### Added
- updated K8500 runner version 1.30.0
- updated OpenTap framework version 9.27.1
## v1.11.4.0
### Added
- Python and pip for plugins that use python venv
- Tshark for plugins that use it for data decoding

### Changed
- Updated to latest stable OpenTap
- Docker mount of opentap-images to give visibility on host and stability on restart
- Version number is now the 3 runner verion digits plus a fourth digit to denote one Component changes

## v0.3.0
### Added
- Initial release of `ks8500_runner` component to the 6G-Library. Basic deployment and registration with the KS8500 backend has been tested in uma. Verifying actual usage of the Runner is TBD
