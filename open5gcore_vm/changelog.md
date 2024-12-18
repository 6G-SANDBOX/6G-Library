# Changelog

## [v1.0.0]

### Added
- initial version of Open5gCore VM compoent
- Add Caddy to expose the WebCLI of Open5GCore
- Automatically configure a route on the `bastion` for the `ue_subnet` back to the core-VM

### Changed
- Change default `MTU` on DN network to 1406 to work with tn_vxlan `GUEST_MTU` of 1450