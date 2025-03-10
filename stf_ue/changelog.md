# Changelog

## v0.4.0
Complete redisign of the component following the changes in tn_bastion and route-manager-api.
### Added
- New input variables `any_stf_ue_start_time` and `any_stf_ue_duration` replacing `stf_ue_any_lease`.
- Deployment now needs to define an exception on tn_bastion's firewall
- Component now creates a DNS entry on tn_bastion's DNS server.


## v0.3.0
### Added
- Initial release of `stf_ue` component to the 6G-Library.