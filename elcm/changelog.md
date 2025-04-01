# Changelog

## unreleased
### Added
- Add `one_elcm_networks` input in `public.yaml`.

## v3.7.1
### Added
- Migrate influxdb from `v1.7.6` to `v2.7.11`.
- Migrate grafana from `v5.4` to `v11.5.1`.
- Component now registers a DNS record in `tn_bastion`'s DNS server

### Fixed
- Result template `ok_result.md.j2` with links to backend, frontend, grafana and influxdb.

## v3.6.3
### Added
- Initial release of `elcm` component to the 6G-Library.
