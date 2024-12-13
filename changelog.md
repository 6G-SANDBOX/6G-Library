# Changelog

## [unreleased]
### Added
- New component `upf_p4_sw`
- New component `open5gs_vm`
- New component `open5gcore_vm`
- New component `berlin_ran`

### Changed
- Updated `ks8500_runner` Added python dependencies. Updated to latest stable OpenTap
- Variable `one_bastion_wireguard_allowedips` promoted as public and user-configurable in components `tn_bastion` and `tn_init`
- Updated `open5gs` helm chart reference URL to point the 6G-Sandbox dockerhub
- Updated `loadcore` to use new appliance with OpenNebula contextualization scripts. However, IaC is currently broken. VM is deployed by Ansible itself
- Updated `ueransim` the field `one_ueransim_gnb_linked_open5gs` to support `upf_p4_sw` component.
- Metadata appliances url.
- Updated `ueransim-ue` config to correctly reach the gnb in the same VM when in BOTH mode

## Fixed
- Component TSN: markdown report file `ok_result.md.j2`.
- Fixed bug where the one_open5gs_upf_ip wasn't correctly set as an output variable


## [v0.3.0] - 2024-10-16
### Added
- New component ks8500_runner
- New component stf_ue
- New component ocf
- New component xrext
- New component elcm
- New component loadcore_agent
- New components opensand_gw, opensand_sat and opensand_st
- New common task `publish_fail_results.yaml`, abstracting tasks from `terraform_apply.yaml`
- New input variable `one_oneKE_nginx_passthough` in component `oneKE` to enable [SSL/TLS passthrough](https://kubernetes.github.io/ingress-nginx/user-guide/tls/#ssl-passthrough).
- New output variable `metadata_dict` from component `oneKE` to share its input variables to future components.
### Changed
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
- Picture in ueransim README.md and ok_result.md not rendering properly
- tnuser login not working in OneKE vnf_0, as account was created locked

## [v0.2.1] - 2024-09-02
### Added
- New component nokia_radio_uma
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
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.3.0...HEAD
[v0.3.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.1...v0.3.0
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
