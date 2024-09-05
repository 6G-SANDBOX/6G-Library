# Changelog

## [unreleased]


## [v0.2.2]
### Added
- New component ue_stf
- New common task `publish_fail_results.yaml`, abstracting tasks from `terraform_apply.yaml`
- New input variable `one_oneKE_nginx_passthough` in component `oneKE` to enable [SSL/TLS passthrough](https://kubernetes.github.io/ingress-nginx/user-guide/tls/#ssl-passthrough).
- New output variable `metadata_dict` from component `oneKE` to share its input variables to future components.
### Changed
- Open5GS upgraded to version `v2.7.1`.
- Component nokia_radio_uma renamed to nokia_radio.
- Comments modified in .dummy_component to make it less confusing. Also out variable naming recommendation is reflected on public.yaml
- Ansible hostname from created components no longer contain variables. It only added complexity without benefits.
- Renamed output variable from open5gs and nokia_radio components
- Fix compoment changelogs retroactively
### Removed
- Removed ansible task in unique components where `entity_name=component_type`. `entity_name` is now only defined during the Jenkins Pipeline
- Removed `pipeline-parameters.yaml` from playbook execution. Pipeline parameters are now sent as extraVars when executing the ansible playbook.
### Fixed
- Component nokia_radio JSON callback is now correctly functional
- Fixed image in ueransim README.md and ok_result.md

## [v0.2.1] - 2024-09-02
### Added
- - New component nokia_radio_uma
### Changed
- Updated description of pipeline parameters in TN_DEPLOY.groovy and TN_DESTROY.groovy
### Fixed
- Markdown file with stderr from failed executions of terraform apply are now correctly uploaded to the S3 Storage


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
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.2...HEAD
[v0.2.2]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.1...v0.2.2
[v0.2.1]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.0...v0.2.1
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
