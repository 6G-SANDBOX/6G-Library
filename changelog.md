# Changelog

## [unreleased]

### Added
- Component `open5gs_vm` new parameter `one_open5gs_vm_install_webui` to install the WEB ui
### changed:
- Component 'ks8500_runner' are updated with tasks to add firewwall exceptions for `loadcore` and `ixchariot` middlewares

## [v0.5.0]
### Added
- Component `oneKE` now supports OneKE 1.31 and OneKE 1.31 Airgapped, and they are the new default version. New custom templates for 6G-Sandbox available in the official marketplace.
- Common task file `nftables_add.yaml` now also supports adding NAT exceptions into the `tn_bastion`.
- All '5G/6G core' components (`open5gcore_vm`, `open5gs_k8s`, `open5gs_vm` and `upf_p4_sw`) are now responsible of enabling the necessary routing to their interfaces in the `tn_bastion`'s route-manager-api.
### Changed
- *.tnlcm/public.yaml* files normalized between components.
- Software from `open5gs_k8s` upgraded from version `v2.7.0` to `v2.7.2`. Used Chart upgraded from version `v2.2.2` to `v2.2.6`.
- New input variables for `int_p4_sw` and `upf_p4_sw`.
- Big changes in many variables of `ueransim`. New appliance required, and new mandatory variable to select its operating mode. For more details check the component's changelog.
- Software from `elcm` upgraded from version `v3.6.3` to `3.7.1`.
- Software from `ks8500_runner` upgraded from version `v1.11.4` to `1.13.0`.
- Variable `one_vnet_gw` in component `vnet` has now no default variable.
- Component `int_p4_sw` requires new variables in sites repository. For more details check the component's changelog.
### Deprecated
- All 'gNB' components (`berlin_ran`, `iswireless_radio`, `nokia_radio` and `ueransim`) no longer require a 'proxy' variable.

### Fixed
- TN_IDs can now correctly set DNS records when they have _ and mixed mayor/minorcase characters.
- Storage nodes of `oneKE` can now be resized from the deployment itself.
- Component `open5gs_vm` removed hardcoded ip (10.45.0.1) from the `ogstun` interface (#99)
- Component `vm_kvm` change param `required_when` to false in field `one_vm_kvm_size` and add default value to private yaml.
- Add firewall exception for the `loadcore_agent` middleware  (#112).
- Hardware RAN components (`berlin_ran`, `iswireless_radio` and `nokia_radio`) are now correctly exposed to the Trial Network by adding a NATting exception into the `tn_bastion`.

## [v0.4.0]
### Added
- New component `berlin_ran`.
- New component `int_p4_sw`.
- New component `iswireless_radio`.
- New component `ixc_endpoint`.
- New component `open5gs_vm`.
- New component `open5gcore_vm`.
- New component `upf_p4_sw`.
- 3 new ansible task files in `.global/cac`:
  - `nftables_add`: Tasks to create an nft file with exceptions to the firewall. Allowed IPs are sent via a list variable named `exceptions`.
  - `routemanager_add`: Utility task to send requests to a `route-manager-api`. It works both for the `route-manager-api` service inside the `tn_bastion`, or for standalone `route-manager-api` VMs deployed from the marketplace appliance.
  - `tnuser_ssh_config.yaml`: Used to create/update the ssh_config file the experimenter can use to ssh their VMs once the TN has been deployed. This decouples the code used to create the ssh_config the Jenkins uses (file `jenkins_ssh_config.yaml`, previously named `ssh_config.yaml`) as ssh access as Jenkins user should be reserved to site administrators.
- Tasks in `publish_fail_results.yaml` and `publish_ok_results.yaml` now only write the generated files locally if flag `debug` is enabled. Code is also optimized.
- Component `vnet`  has a new terraform output variable.
### Changed
- Field `metadata` in `.tnlcm/public.yaml` includes three new optional variables: `destroy_script`, `resource_manager` and `appliance`. More information about them in the dummy component.
- Field `output` in `.tnlcm/public.yaml`renamed to `terraform_outputs` and repurposed to document the values each component saves as terraform outputs.
- Components that upload an utility file to the minIO, now prefix them with with `misc-`, to diferenciate them from other files such as terraform manifests (`tf-`) or success markdowns (`ok_result-`).
- Components `tn_init`, `tn_bastion` and `tn_vxlan` upgraded to use the new appliance with new features. Please read more on `tn_bastion` and `tn_vxlan` changelogs.
- Components `nokia_radio` and `oneKE` suffered a complete redesign following the changes in tn_bastion and route-manager-api. Read their changelogs for more details.
- Component `.dummy_component` with and updated description of what "default_value" in public.yaml is really for.
- Software from `ks8500_runner` upgraded to version `v1.11.4.0`. Added python dependencies. Updated to latest stable OpenTap.
- Component `loadcore_agent` now uses a preconfigured appliance downloadable from the [6G-SANDBOX Marketplace](https://marketplace.mobilesandbox.cloud:9443/appliance). However, *IaC* is currently broken. VM is deployed by Ansible itself.
- Component `open5gs` renamed to `open5gs_k8s`. Helm chart reference URL now points to the 6G-Sandbox dockerhub. New input variable and bugfix.
- Components `opensand_gw`, `opensand_sat` and `opensand_st` now include a new input variable.
- Component `ueransim` now enables variable autocompletion from posterior components.
- Component `vm_kvm` now registers itself in the DNS server.
### Removed
- TNLCM callbacks don't include an output field anymore
### Fixed
- Selecting vnets for VM components from either `tn_vxlan` or `vnet` now works correctly.
- Markdown report of component `tsn`.


## [v0.3.0] - 2024-10-16
### Added
- New component `elcm`.
- New component `ks8500_runner`.
- New component `loadcore_agent`.
- New component `ocf`.
- New components `opensand_gw`, `opensand_sat` and `opensand_st`.
- New component `stf_ue`.
- New component `xrext`.
- New common task `publish_fail_results.yaml`, abstracting tasks from `terraform_apply.yaml`.
- New input variable `one_oneKE_nginx_passthough` in component `oneKE` to enable [SSL/TLS passthrough](https://kubernetes.github.io/ingress-nginx/user-guide/tls/#ssl-passthrough).
- New output variable `metadata_dict` in component `oneKE` to share its input variables to future components.
### Changed
- Ansible's `hostname` variable from created VM components no longer contains nested variables. It only added complexity without benefits.
- `.dummy_component` upgraded to version `v0.3.0`. Added comments to make its following less confusing. Also output variables naming recommendation is reflected on `.tnlcm/public.yaml`.
- Component `nokia_radio_uma` renamed to `nokia_radio` and upgraded to version `v0.3.0` with JSON callback bugfix
### Removed
- Removed ansible task in unique components where `entity_name=component_type`. `entity_name` is now only defined during the Jenkins Pipeline.
- Removed `pipeline-parameters.yaml` from playbook execution. Pipeline parameters are now sent as extraVars when executing the ansible playbook.
### Fixed
- Markdown rendering issues in the `ueransim` component.


## [v0.2.1] - 2024-09-02
### Added
- New component `nokia_radio_uma`.
### Changed
- Updated description of pipeline parameters in TN_DEPLOY.groovy and TN_DESTROY.groovy.
### Fixed
- Markdown file with stderr from failed executions of terraform apply are now correctly uploaded to the object storage.


## [v0.2.0] - 2024-06-06
### Added
- Standardized changelog format to fit [keep-a-changelog](https://github.com/olivierlacan/keep-a-changelog/blob/main/CHANGELOG.md) rules.
- New parameters added to the Jenkinsfile.
- Pipeline executins can point to a specific commit, release or branch.
- New component `tn_init`, combining `tn_vxlan` and `tn_bastion` together, and ment to be the go-to way to deploy Trial Networks
- New component `tsn`, enabling further networking capabilities.
- New component `vm_kvm`, replacing all previous VM components.
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
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.5.0...unreleased
[v0.5.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.4.0...v0.5.0
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
