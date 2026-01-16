# Changelog

## unreleased
### Removed
- Removal of multiple public variables of little interest to the experimenter:
  - `ocf_any_vault_namespace`
  - `ocf_any_vault_storage_class`
  - `ocf_any_vault_storage_size`
  - `ocf_any_monitoring_namespace`
  - `ocf_any_capif_release`
  - `ocf_any_capif_name_version_chart`
  - `ocf_any_capif_namespace`
  - `ocf_any_capif_storage_class`
  - `ocf_any_capif_storage_access_mode`
  - `ocf_any_capif_grafana_storage_size`
  - `ocf_any_capif_loki_storage_size`
  - `ocf_any_capif_mongo_storage_size`
  - `ocf_any_capif_mongo_register_storage_size`
  - `ocf_any_capif_tempo_storage_size`

## v1.0.0
### Changed
- Output dictionary `metadata` renamed to `ocf_metadata`.

## v0.4.0
### Added
- Initial release of `ofc` component to the 6G-Library.