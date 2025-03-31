# Changelog

## v1.0.0
### Added
- Initial release of `open5gcore_vm` component to the 6G-Library.
- Using Caddy to expose the WebCLI of Open5GCore.
- Automatically configure a route on the `bastion` for the `ue_subnet` back to the core-VM.
- Default `MTU` on DN network set to 1406 to work with tn_vxlan `GUEST_MTU` of 1450.
