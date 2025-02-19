# Changelog

## [unreleased]
### Added
- Component `oneKE` upgraded to version `unreleased`, now supporting OneKE 1.31.
### Changed
- public.yaml files normalized between components
### Fixed
- The open5gs proxy in components `nokia_radio` and `iswireless_radio` is now properly set. Components upgraded to version `unreleased`.


## [v0.4.0]
### Added
- New component `berlin_ran` at version `v0.5.0`.
- New component `int_p4_sw` at version `v1.0.0`.
- New component `iswireless_radio` at version `v0.4.0`.
- New component `ixc_endpoint` at version `v0.4.0`.
- New component `open5gs_vm` at version `v1.0.0`.
- New component `open5gcore_vm` at version `v0.4.0`.
- New component `upf_p4_sw` at version `v0.4.0`.
- 3 new ansible task files in `.global/cac`:
  - `nftables_add`: Tasks to create an nft file with exceptions to the firewall. Allowed IPs are sent via a list variable named `exceptions`.
  - `routemanager_add`: Utility task to send requests to a `route-manager-api`. It works both for the `route-manager-api` service inside the `tn_bastion`, or for standalone `route-manager-api` VMs deployed from the marketplace appliance.
  - `tnuser_ssh_config.yaml`: Used to create/update the ssh_config file the experimenter can use to ssh their VMs once the TN has been deployed. This decouples the code used to create the ssh_config the Jenkins uses (file `jenkins_ssh_config.yaml`, previously named `ssh_config.yaml`) as ssh access as Jenkins user should be reserved to site administrators.
- Tasks in `publish_fail_results.yaml` and `publish_ok_results.yaml` now only write the generated files locally if flag `debug` is enabled. Code is also optimized.
### Changed
- Field `metadata` in `.tnlcm/public.yaml` includes three new optional variables: `destroy_script`, `resource_manager` and `appliance`. More information about them in the dummy component.
- Field `output` in `.tnlcm/public.yaml`renamed to `terraform_outputs` and repurposed to document the values each component saves as terraform outputs.
- Components that upload an utility file to the minIO, now prefix them with with `misc-`, to diferenciate them from other files such as terraform manifests (`tf-`) or success markdowns (`ok_result-`).
- Component `.dummy_component` upgraded to version `v0.4.0`. Updated description of what "default_value" in public.yaml is really for.
- Component `ks8500_runner` upgraded to version `v1.11.4.0`. Added python dependencies. Updated to latest stable OpenTap.
- Component `loadcore_agent` upgraded to version `v0.4.0`. Now using new appliance with OpenNebula contextualization scripts. However, *IaC* is currently broken. VM is deployed by Ansible itself.
- Component `nokia_radio` upgraded to version `v0.4.0`. Complete redisign of the component following the changes in tn_bastion and route-manager-api.
- Component `ocf` upgraded to version `v1.0.0`.
- Component `oneKE` upgraded to version `v0.4.0`.
- Component `open5gs` renamed to `open5gs_k8s`, and upgraded to version `v0.4.0`. Helm chart reference URL now points to the 6G-Sandbox dockerhub. New input variable and bugfix.
- Components `opensand_gw`, `opensand_sat` and `opensand_st` upgraded to version `v0.4.0` with new input variable.
- Component `oneKE` upgraded to version `v0.4.0`. Complete redisign of the component following the changes in tn_bastion and route-manager-api.
- Components `tn_init`, `tn_bastion` and `tn_vxlan` upgraded to version `v0.4.0`, with a massive revision of all previous code. Please read more on `tn_bastion` and `tn_vxlan` changelogs.
- Component `tsn` upgraded to version `v0.4.0`. Fixed markdown report.
- Component `loadcore` upgraded to version `v0.4.0`. Now uses appliance with OpenNebula contextualization scripts.
- Component `ueransim` upgraded to version `v0.4.0`, allowing variable autocompletion from new components.
- Component `vm_kvm` upgraded to version `v0.4.0`, registering itself in the DNS server.
- Component `vnet` upgraded to version `v0.4.0`, with new terraform output variable.
### Removed
- TNLCM callbacks don't include an output field anymore
### Fixed
- Selecting vnets for VM components from either `tn_vxlan` or `vnet` now works correctly.


## [v0.3.0] - 2024-10-16
### Added
- New component `elcm` at version `v3.6.3`.
- New component `ks8500_runner` at version `v0.3.0`.
- New component `loadcore_agent` at version `v0.3.0`.
- New component `ocf` at version `v1.0.0`.
- New components `opensand_gw`, `opensand_sat` and `opensand_st` at version `v0.3.0`.
- New component `stf_ue` at version `v0.3.0`.
- New component `xrext` at version `v0.3.0`
- New common task `publish_fail_results.yaml`, abstracting tasks from `terraform_apply.yaml`.
- New input variable `one_oneKE_nginx_passthough` in component `oneKE` to enable [SSL/TLS passthrough](https://kubernetes.github.io/ingress-nginx/user-guide/tls/#ssl-passthrough).
- New output variable `metadata_dict` in component `oneKE` to share its input variables to future components.
### Changed
- Ansible's `hostname` variable from created VM components no longer contains nested variables. It only added complexity without benefits.
- `.dummy_component` upgraded to version `v0.3.0`. Added comments to make its following less confusing. Also output variables naming recommendation is reflected on `.tnlcm/public.yaml`.
- Component `nokia_radio_uma` renamed to `nokia_radio` and upgraded to version `v0.3.0` with JSON callback bugfix
- `oneKE` upgraded to version `v0.3.0`.
- `open5gs` upgraded to version `v0.3.0`.
- `ueransim` upgraded to version `v0.3.0` fixing markdown rendering issues.
### Removed
- Removed ansible task in unique components where `entity_name=component_type`. `entity_name` is now only defined during the Jenkins Pipeline.
- Removed `pipeline-parameters.yaml` from playbook execution. Pipeline parameters are now sent as extraVars when executing the ansible playbook.


## [v0.2.1] - 2024-09-02
### Added
- New component `nokia_radio_uma` at version `v0.2.1`.
### Changed
- Updated description of pipeline parameters in TN_DEPLOY.groovy and TN_DESTROY.groovy.
### Fixed
- Markdown file with stderr from failed executions of terraform apply are now correctly uploaded to the object storage.


## [v0.2.0] - 2024-06-06
### Added
- Standardized changelog format to fit [keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog/blob/main/CHANGELOG.md) rules.
- New parameters added to the Jenkinsfile.
- Pipeline executins can point to a specific commit, release or branch.
- New component `tn_init` at version `v0.2.0`, combining `tn_vxlan` and `tn_bastion` together, and ment to be the go-to way to deploy Trial Networks
- New component `tsn` at version `v0.2.0`, enabling further networking capabilities.
- New component `vm_kvm` at version `v0.2.0`, replacing all previous VM components.
- New common task files for ansible playbooks.
### Changed
- Radical repository reorganization:
    - moved `doc/sample_input_file.yaml` to `sample_input_file.yaml`.
    - moved `doc/index.md` to `README.md`.
    - moved `public/changelog.yaml` to `changelog.md` with **changed format**.
    - moved `public/description.yaml` to `.tnlcm/public.yaml`.
    - moved `private/values.yaml` to `variables/{{ site_hypervisor }}/private.yaml`.
    - moved `results/` directory to `code/results/`. Renamed markdown templates.
    - moved `private/manifest.yaml` to `code/component_playbook.yaml`.
    - deleted `public/` directory.
    - deleted `private/` directory.
    - and many more
- Initial set of components version upgraded to version `v0.2.0`.
- Component `k8s_medium` renamed to `oneKE`.
- Component `vxlan` renamed to `vnet`.
- Modified Jenkins Pipeline.
- TNLCM now with base64-encoded values.
- New unprivileged user used in OpenNebula: `jenkins-master` from a newly created group called `6G-Sandbox`.
- Access to deployed components can be done with a 'tnuser' user.
- Replaced Ansible's `amazon.aws.aws_s3` module to a current one: `amazon.aws.s3_object`.
### Deprecated
- Components `tn_bastion` and `tn_vxlan` are now partially deprecated. Their support will be reduced, as the recommended aproach is to use `tn_init` to deploy Trial Networks
### Removed
- Deleted `.global/pac/DEPLOY_COMPONENT_FROM_COMMIT.groovy`.
- Deleted all redundant VM components:
  * `vm_kvm_exra_large`
  * `vm_kvm_large`
  * `vm_kvm_medium`
  * `vm_kvm_small`
  * `vm_kvm_very_small`


## [v0.1.0] - 2024-04-29
Initial release of the 6G Library. Changes will be kept on track for now on.
Initial set of components is:
- `.dummy_component`.
- `k8s_medium`.
- `open5gs`.
- `tn_bastion`.
- `tn_vxlan`.
- `ueransim`.
- `vxlan`.


<!-- Change latest version value at every release -->
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.4.0...unreleased
[v0.4.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.3.0...v0.4.0
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
