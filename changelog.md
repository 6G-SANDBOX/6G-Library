# Changelog

## [Unreleased]

### Added

- Standardized changelog format to fit [keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog/blob/main/CHANGELOG.md) rules.
- New parameters added to the Jenkinsfile: `ENTITY_NAME`, `TNLCM_CALLBACK`, `LIBRARY_URL` and `LIBRARY_BRANCH`
- New `.global/hypervisors/` directory replacing `<component>/private/<hypervisor>/prepare.yml` with the steps common to each hypervisor

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


### Removed
- Deleted `.global/pac/DEPLOY_COMPONENT_FROM_COMMIT.groovy`





Yet to be done:
- verify utility of some pipeline enviroment variables
- Change OpenNebula credentials so that `oneadmin` isn't the Jenkins user no more.
A less privileged user named `jenkins-master` will be the one deploying the components.
It is the admin of a newly created group called `6G-Sandbox`, where custom users may be added in the future.
- New `.global/cac/deploy_component.yaml` file with ansible tasks common to all components.
All ansible executions will start from here, to import the corresponding "component playbook"
- Delete `.global/cac/mix_variables.yaml` file, and `.global/cac/mix_variables_tasks.yaml` renamed to `load_variables.yaml`
- Rename `<component>/private/manifest.yaml` to `<component>/private/playbook.yaml`.
The term manifest is ambiguous an can refer both to the terraform files and to this yaml one.
The name `playbook.yml` is more clear and would lead to less confusion.
- Replace Ansible's `amazon.aws.aws_s3`module to a current one like `amazon.aws.s3_object`
- Add `SITE_URL` and `SITE_BRANCH` parameters to test forks of the **6G-Sandbox-Sites** repository. 
Remember to change from Jenkins the value of the `GITHUB_JENKINS` credential so that it can access all required repos.



To be discussed:
- remove public.yaml/name ??
- rename public.yaml/metadata/needs
- optional component dependencies. TNLCM needs aditional logic (not listed in metadata) to order components if for example a VM uses aditional vxlans



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