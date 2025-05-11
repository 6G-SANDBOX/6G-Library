# OpenCAPIF (OCF)

**OpenCAPIF (Open Common API Framework)** is an open-source implementation of ETSI's CAPIF (Common API Framework), providing a unified API exposure layer for network services and functions.

This component deploys OpenCAPIF inside a **Kubernetes cluster** using a **Helm Chart**, allowing experimentation and integration of CAPIF-compliant APIs within the Trial Network environment.

More information in the official documentation: [ETSI OCF Documentation](https://ocf.etsi.org/documentation/latest/)


![ocf](https://github.com/6G-SANDBOX/6G-Library/blob/assets/ocf/ocf.png)

## 🔧 Deployment Details

- Hypervisor: `OpenNebula`
- Requires an existing `oneKE` Kubernetes cluster (passed as input `ocf_one_oneKE`).
- Deploys OpenCAPIF via Helm Chart into the provided Kubernetes cluster.
- Supports configuration of namespaces, storage classes, storage sizes, monitoring, and credentials.
- Requires the `tn_bastion` component to be deployed beforehand for access.

## 📥 Input Variables

| Variable                         | Description                                                                                     | Type       | Required |
|---------------------------------|-------------------------------------------------------------------------------------------------|------------|----------|
| `ocf_one_oneKE`                  | Name of the oneKE Kubernetes cluster to deploy OpenCAPIF in.                                     | oneKE      | Required |
| `ocf_any_repo`                   | OCF repository URL.                                                                             | str        | Optional (default: `https://labs.etsi.org/rep/ocf/capif.git`) |
| `ocf_any_branch`                 | Branch to use from the OCF repository.                                                          | str        | Optional (default: `staging`) |
| `ocf_any_vault_namespace`        | Namespace for Vault deployment.                                                                 | str        | Optional (default: `ocf-vault`) |
| `ocf_any_vault_storage_class`    | Storage class for Vault persistent volume.                                                      | str        | Optional (default: `longhorn`) |
| `ocf_any_vault_storage_size`     | Size of Vault persistent volume.                                                                | str        | Optional (default: `1Gi`) |
| `ocf_any_monitoring_namespace`   | Namespace for monitoring stack deployment.                                                      | str        | Optional (default: `ocf-monitoring`) |
| `ocf_any_monitoring_service_name`| Name of monitoring service.                                                                     | str        | Optional (default: `monitoring`) |
| `ocf_any_capif_namespace`        | Namespace for OpenCAPIF deployment.                                                             | str        | Optional (default: `ocf-capif`) |
| `ocf_any_capif_resources_reserve`| Reserve CAPIF resources (default: `NO`).                                                        | str        | Optional |
| `ocf_any_capif_storage_class`    | Storage class for OpenCAPIF persistent volumes.                                                 | str        | Optional (default: `longhorn`) |
| `ocf_any_capif_storage_access_mode` | Access mode for OpenCAPIF storage. Valid options: `ReadWriteOnce`, `ReadWriteMany`.            | str        | Optional (default: `ReadWriteOnce`) |
| `ocf_any_capif_grafana_storage_size` | Storage size for Grafana.                                                                    | str        | Optional (default: `1Gi`) |
| `ocf_any_capif_loki_storage_size`    | Storage size for Loki.                                                                       | str        | Optional (default: `100Mi`) |
| `ocf_any_capif_mongo_storage_size`   | Storage size for MongoDB database.                                                            | str        | Optional (default: `1Gi`) |
| `ocf_any_capif_mongo_register_storage_size` | Storage size for MongoDB register database.                                               | str        | Optional (default: `1Gi`) |
| `ocf_any_capif_tempo_storage_size`   | Storage size for Tempo.                                                                      | str        | Optional (default: `1Gi`) |
| `ocf_any_user`                   | Username to be registered on OpenCAPIF.                                                         | str        | Optional (default: `client`) |
| `ocf_any_password`               | Password for the user registered on OpenCAPIF.                                                  | str        | Optional (default: `password`) |
| `ocf_any_tests_to_pass`          | Tests to launch after deployment (`smoke` or `all`).                                            | str        | Optional (default: `smoke`) |
