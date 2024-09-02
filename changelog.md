# Changelog

## [v0.2.1]

### Changed

- Failed terraform applies correctly post a markdown to the MinIO informing about the error.
- Corrected texts in the pipeline parameter descriptions
- New nokia_radio_uma component

## [v0.2.0] - 2024-06-06

### Added

- Standardized changelog format to fit [keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog/blob/main/CHANGELOG.md) rules.
- New parameters added to the Jenkinsfile.
- Pipeline executins can point to a specific commit, release or branch
- New vm_kvm component replacing all previous VM components
- New tn_init component, combining tn_vxlan and tn_bastion together
- New tsn component, enabling further networking capabilities.
- New common task files for ansible playbooks.

### Changed

- Radical repository reorganization:
    - moved `doc/sample_input_file.yaml` to `sample_input_file.yaml`
    - moved `doc/index.md` to `README.md`
    - moved `public/changelog.yaml` to `changelog.md` with **changed format**
    - moved `public/description.yaml` to `.tnlcm/public.yaml`
    - moved `private/values.yaml` to `variables/{{ site_hypervisor }}/private.yaml`
    - moved `results/` directory to `code/results/`. Renamed markdown templates
    - moved `private/manifest.yaml` to `code/component_playbook.yaml`
    - deleted `public/` directory
    - deleted `private/` directory
    - and many more
- Component "vxlan" renamed to "vnet"
- Component "k8s_medium" renamed to oneKE
- Modified Jenkins Pipeline
- TNLCM now with base64-encoded values
- New unprivileged user used in OpenNebula: `jenkins-master` from a newly created group called `6G-Sandbox`
- Access to deployed components can be done with a 'tnuser' user.
- Replaced Ansible's `amazon.aws.aws_s3` module to a current one: `amazon.aws.s3_object`


### Removed
- Deleted `.global/pac/DEPLOY_COMPONENT_FROM_COMMIT.groovy`
- Deleted all redundant VM components



## [v0.1.0] - 2024-04-29

Initial release of the 6G Library. Changes will be kept on track for now on.


<!-- Change latest version value at every release -->
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.0...HEAD
[v0.2.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.1.0...v0.2.0
[v0.1.0]: https://github.com/6G-SANDBOX/6G-Library/releases/tag/v0.1.0



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
