# Changelog

## v1.0.0
### Changed
- Adapted to accept external influxdb and grafana.
- Component is now added to the sshconfig of tnuser

## v0.5.0
### Added
- Component now registers a DNS record in `tn_bastion`'s DNS server
### Changed
- Appliance version upgraded to version `v3.7.1`, involving changes such as:
    - Influxdb upgraded from `v1.7.6` to `v2.7.11`.
    - Grafana upgraded from `v5.4` to `v11.5.1`.
### Fixed
- Result template `ok_result.md.j2` with links to backend, frontend, grafana and influxdb.


- UERANSIM appliance version upgraded its software to version `v3.2.7`.

## v0.3.0
### Added
- Initial release of `elcm` component to the 6G-Library with appliance 
- ELCM appliance at version `v3.6.3`.
