# Changelog

## [Unreleased]

### Added

- Standardized changelog format to fit [keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog/blob/main/CHANGELOG.md) rules.

### Changed

- Radical repository reorganization:
    - moved `doc/sample_input_file.yaml` to `sample_input_file.yaml`
    - moved `doc/index.md` to `README.md`
    - moved `public/changelog.yaml` to `changelog.md` with **changed format**
    - moved `public/description.yaml` to `variables/public.yaml`
    - moved `private/values.yaml` to `variables/private.yaml`
    - moved `private/one/` to `code/hypervisors/one/`. Also moved charts from **k8s_mediium** and **open5gs** to `code/hypervisors/all/`
    - moved `results/` directory to `code/results/`
    - moved `private/manifest.yaml` to `code/manifest.yaml`
    - deleted `public/` directory
    - deleted `private/` directory
- Restructuration of public variables file.










To be discussed:
- remove public.yaml/name ??
- remove tn_bastion and tn_vxlan from public.yaml/metadata/depends->needs
- also remove intrinsec dependencies (dependency of a dependency)



## [v0.1.0] - 29-04-2024

Initial release of the 6G Library. Changes will be kept on track for now on.


<!-- Change latest version value at every release -->
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.1.0...HEAD
<!--
Format for next release
[v0.1.1]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.1.0...v0.1.1
-->
[v0.1.0]: https://github.com/6G-SANDBOX/6G-Library/releases/tag/v0.1.0


<!--
FIELDS PER VERSION:

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