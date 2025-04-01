# Changelog

## v0.5.0
### Added
- Component is now responsible to expose its AMF and UPF interfaces, by adding rules and firewall exceptions to `tn_bastion`.
### Changed
- Switched from 'latest' to fixed version tags in Docker images to ensure deployment stability and reproducibility.
- Updated UPF control plane to version v2.0.0 which includes:
  - New logging functionality
  - Improved ARP handling
  - Better stability
- Enhanced `README.md` documentation.
- Enhanced `sample_input_file.yaml` documentation.
- Component now specifies two new variables in the sites repository: `template_id` and `image_id`. They were previously inferred from `vm_kvm`.
### Fixed
- Fixed bastion host connectivity issue: Added SSH key configuration in `component_playbook.yaml` ansible playbook and `upf_p4_sw.tf.j2` terraform file to solve connectivity problem with bastion.

## v0.4.0
### Added
- Initial release of `upf_p4_sw` component to the 6G-Library.
