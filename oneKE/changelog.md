# Changelog

## unreleased
### Added
- Component support for OneKE 1.31 and 1.31 Airgapped. New custom templates for 6G-Sandbox available in the official marketplace.
### Changed
- Variables `one_oneKE_multus`, `one_oneKE_metallb`, `one_oneKE_longhorn` and `one_oneKE_traefik` are now boolean.
### Fixed
- Jenkins is now able to resize the Longhorn disk in the storage nodes.

## v0.4.0
### Added
- The web interfaces are now registered in the new tn_bastion DNS server.
### Removed
- OneKE v1.27 is no longer allowed
- Input variable one_oneKE_dns is no longer present.

## v0.3.0
### Added
- New input variable `one_oneKE_nginx_passthough` to enable [SSL/TLS passthrough](https://kubernetes.github.io/ingress-nginx/user-guide/tls/#ssl-passthrough).
- New output variable `metadata_dict` to share OneKE input variables to the future components.

### Changed
- Ansible group of created K8s nodes no longer uses variables. It only added complexity without benefits.

### Deprecated
- OneKE v1.27 is no longer mantained (untested)

### Fixed
- Fix error where in some runtimes, VNF was not ready on time.
- `tnuser`'s account is no longer locked on `vnf_0`.


## v0.2.0
### Changed
- Component follows the new architecture.
- Component renamed from `k8s_medium`.
- Cluster configuration now made directly through the master node. No tunneling.
- OneKE v1.29 with three storage nodes is the new default.


## v0.1.0
### Added
Initial release of the 6G Library. Changes will be kept on track for now on.
