# Changelog

## [Unreleased]

### Added

- Standardized changelog format to fit [keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog/blob/main/CHANGELOG.md) rules.
- New parameters added to the Jenkinsfile: `ENTITY_NAME`, `TNLCM_CALLBACK`, `LIBRARY_URL` and `LIBRARY_BRANCH`
- New `.global/hypervisors/` directory replacing `<component>/private/<hypervisor>/prepare.yml` with the steps common to each hypervisor
- New `.global/cac/deploy_component.yaml` file with ansible tasks common to all components.
All ansible executions will start from here, to later import its corresponding "component_playbook.yaml" file
- Minor ansible tasks moved to a `tasks/` directory inside `.global/cac`

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
- Jenkins Pipeline modified and renamed
- Renamed `.global/iac/providers.tf` to `.global/iac/opennebula_provider_v1.4.tf`
- Output to the TNLCM now with base64-encoded values
- New unprivileged user used in OpenNebula: `jenkins-master` from a newly created group called `6G-Sandbox`
- Replaced Ansible's `amazon.aws.aws_s3` module to a current one: `amazon.aws.s3_object`


### Removed
- Deleted `.global/pac/DEPLOY_COMPONENT_FROM_COMMIT.groovy`




Yet to be done:
- Use Ansible ENVIROMENTAL VALUES to specify the ansible user into the VMs (rootless priviledged user). 3 default ssh keys: site-admin, jenkins-master and tn-user
- UE gets GNB IP directly



## [v0.1.0] - 2024-04-29

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