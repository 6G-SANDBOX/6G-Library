# Changelog

## v0.5.0
### Fixed
- Change param `required_when` to false in field `one_vm_kvm_size` and add default value to private yaml.


## v0.4.0
### Added
- Component now registers a DNS record in `tn_bastion`'s DNS server


## v0.2.0
### Added
- Initial release of `vm_kvm` component to the 6G-Library.
- Component replaces deprecated components:
  * `vm_kvm_exra_large`
  * `vm_kvm_large`
  * `vm_kvm_medium`
  * `vm_kvm_small`
  * `vm_kvm_very_small`
