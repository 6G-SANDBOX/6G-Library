# Changelog

## [unreleased] - 2025-XX-XX
### Added
- Component `ocf` has 2 new public variables: `ocf_any_capif_release` and `ocf_any_capif_name_version_chart`.
### Changed
- Upgraded OpenNebula Terraform provider from 1.4 to 1.5
- `open5gs_vm` New input variabe `one_open5gs_vm_loglevel` to set the log-level of the open5gs components
### Fixed
- Fixed broken links for components that have appliances in `.tnlcm/public.yaml` file.
- Fixed `open5gs_k8s` charts not being able to pull mongodb images. Chart version upgraded from `v2.3.1` to `v2.3.3`.
- Fixed disk resize method used in `ks8500_runner`.
- Disabled `open5gs-seppd` component of `open5gs_vm`

## [v1.0.0] - 2025-09-11
### Added
- New metadata variables `depends_on` and `tags` now present in the `.tnlcm/public.yaml` file of all components.
- New component `prometheus` to scrape time series data.
- New component `influxdb` to store time series data.
- New component `grafana` to visualize time series data.
- New component `monitoring` to deploy InfluxDB, Grafana and Prometheus stack together.
- New component `mongodb` to store time series data.
- New component `ris` to configure a RIS exposed from an API.
- New `component_type` terraform output added to all components.
- Component `oneKE` adds support for scaling the number of worker nodes in the cluster.
- New mandatory input variable for component `xrext`: `one_xrext_external_server`.
- New input variable `one_open5gs_vm_install_webui` in `open5gs_vm`, enabling the installation of the webUI.
- New input variable `one_loadcore_agent_hugepages` and site variable schema in `loadcore_agent`, to switch between the 2 possible appliances.
- New component `athens_ran` implemented to support the athens testbed related to ericsson RAN and the Open5Gs module.
### Changed
- Common task file `routemanager_add.yaml` now also supports adding routes with key `dev`.
- MTU default values in 'subnet' components (`tn_vxlan`, `vnet` and `tn_init`) are now first gathered from 6G Sandbox sites repository as suggested in issue #78
- Modified terraform outputs for all 5G Core components (`open5gcore_vm`, `open5gs_k8s`, `open5gs_vm` and `upf_p4_sw`).
- Modified terraform outputs for all gNB components (`berlin_ran`, `iswireless_radio`, `nokia_radio` and `ueransim`).
- Components `tn_bastion` and `tn_init` now support the inclusion of additional custom routes, and firewall/NAT exceptions.
- Component `nokia_radio` renamed the site variables `cp_ip` and `up_ip` to `n2_ip` and `n3_ip` respectively, for coherence with the 5G Core outputs.
- Software from `open5gs_vm` and `open5gs_k8s` upgraded from version `v2.7.2` to `v2.7.6` and `v2.7.5` respectively. Chart version upgraded from `v2.2.6` to `v2.3.1`.
- Enhanced variable autocompletion on `ueransim`, gathering all UE-related metadata directly from the gNB's linked 5G Core.
- Component `elcm` now can use external influxdb and grafana instances.
- Upgraded dockerfile to version `1.15.1` in component `ks8500_runner`.
- Component `iswireless_radio` now supports **DU-RU** mode, connecting to a virtualized CU instead of a 5G core.
- ks8500_runner upgraded from version v1.15.1 to 1.16.1
### Fixed
- Component `ks8500_runner` updated to add firewall exceptions for `loadcore` and `ixchariot` middlewares.
- All `.tnlcm/public.yaml` files now fit the LLM requirements as suggested in issue #128
- remove the transmission of icmp-reirect messages from the UPF of `open5gcore_vm` and `open5gs_vm` component
- Component `loadcore_agent` now correctly works with both the hugepages appliance, and the "light" one.
- Component is now also deployable by only using Terraform, without the ansible workaround. For more details check the component's changelog.
### Deprecated
- Multiple redundant variables removed from `ueransim`. **ue** mode now autocompletes its variables directly from the 5G core used by the *gNB*.
### Removed
- Component `oneKE` does no longer support OneKE 1.29


## [v0.5.0] - 2025-03-31
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
- Component `oneKE` dropped support for OneKE 1.29.

### Fixed
- TN_IDs can now correctly set DNS records when they have _ and mixed mayor/minorcase characters.
- Storage nodes of `oneKE` can now be resized from the deployment itself.
- Component `open5gs_vm` removed hardcoded ip (10.45.0.1) from the `ogstun` interface (#99)
- Component `vm_kvm` change param `required_when` to false in field `one_vm_kvm_size` and add default value to private yaml.
- Add firewall exception for the `loadcore_agent` middleware  (#112).
- Hardware RAN components (`berlin_ran`, `iswireless_radio` and `nokia_radio`) are now correctly exposed to the Trial Network by adding a NATting exception into the `tn_bastion`.

## [v0.4.0] - 2025-02-06
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
- New component `nokia_radio`.
### Changed
- Ansible's `hostname` variable from created VM components no longer contains nested variables. It only added complexity without benefits.
- `.dummy_component` upgraded to version `v0.3.0`. Added comments to make its following less confusing. Also output variables naming recommendation is reflected on `.tnlcm/public.yaml`.
- Updated description of pipeline parameters in TN_DEPLOY.groovy and TN_DESTROY.groovy.
### Removed
- Removed ansible task in unique components where `entity_name=component_type`. `entity_name` is now only defined during the Jenkins Pipeline.
- Removed `pipeline-parameters.yaml` from playbook execution. Pipeline parameters are now sent as extraVars when executing the ansible playbook.
### Fixed
- Markdown rendering issues in the `ueransim` component.
- Failed execution markdown now includes stderr logs from "terraform apply" steps.


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
[unreleased]: https://github.com/6G-SANDBOX/6G-Library/compare/v1.0.0...unreleased
[v1.0.0]:https://github.com/6G-SANDBOX/6G-Library/compare/v0.5.0...v1.0.0
[v0.5.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.4.0...v0.5.0
[v0.4.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.3.0...v0.4.0
[v0.3.0]: https://github.com/6G-SANDBOX/6G-Library/compare/v0.2.0...v0.3.0
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
