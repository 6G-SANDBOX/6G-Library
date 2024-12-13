# Changelog

## [v0.3.0]

### Added
- New input variable `one_oneKE_nginx_passthough` to enable [SSL/TLS passthrough](https://kubernetes.github.io/ingress-nginx/user-guide/tls/#ssl-passthrough).
- New output variable `metadata_dict` to share OneKE input variables to the future components

### Changed
- Ansible group of created K8s nodes no longer uses variables. It only added complexity without benefits.

### Fixed
- Fix error where in some runtimes, VNF was not ready on time

## [v0.2.0]

### Changed
- Component follows the new architecture.
- Component renamed from `k8s_medium`
- Cluster configuration now made directly through the master node. No tunneling
- OneKE v1.29 with three storage nodes is the new default.

## [v0.1.0]

### Added
Initial release of the 6G Library. Changes will be kept on track for now on.