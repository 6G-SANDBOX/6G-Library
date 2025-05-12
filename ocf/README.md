# OpenCAPIF (OCF)

**OpenCAPIF (Open Common API Framework)** is an open-source implementation of ETSI's CAPIF (Common API Framework), providing a unified API exposure layer for network services and functions.

This component deploys OpenCAPIF inside a **Kubernetes cluster** using a **Helm Chart**, allowing experimentation and integration of CAPIF-compliant APIs within the Trial Network environment.

More information in the official documentation: [ETSI OCF Documentation](https://ocf.etsi.org/documentation/latest/)


![ocf](https://github.com/6G-SANDBOX/6G-Library/blob/assets/ocf/ocf.png)

## Maintainers

- Jorge Moratinos <jorge.moratinossalcines@telefonica.com>

## Short Description

Deploys an OpenCAPIF instance inside a Kubernetes cluster using a Helm chart from ETSI.

## Long Description

This component deploys the official [OpenCAPIF](https://ocf.etsi.org/documentation/latest/) API exposure solution, defined by ETSI, inside a Kubernetes cluster available in the Trial Network.  
It relies on a previously deployed `oneKE` cluster where the Helm chart and its resources will be applied.

OpenCAPIF enables API exposure and management functionalities, including optional deployment of monitoring and observability (Grafana, Loki, Tempo) and secret storage (Vault).  
By default, smoke tests are executed post-deployment, but a full test suite can also be run.

## Hypervisors

- one

## Depends on

- tn_bastion
- oneKE

## Tags

- OpenCAPIF
- ETSI
- API exposure

## Site-specific variables

Variables read from the site's encrypted file in the 6G-Sandbox-Sites repository.  
TNLCM checks that the variables are defined, and errors if attempting to deploy the component without them.

Each variable has a short description of what it does for informational purposes.

_N/A_

## Input variables

| Variable | Description | Type | Default Value | Choices | Required When |
|----------|-------------|------|---------------|---------|----------------|
| `ocf_one_oneKE` | Name of the oneKE cluster used to deploy the Helm Chart | oneKE | - | - | true |
| `ocf_any_repo` | OCF Repository URL | str | https://labs.etsi.org/rep/ocf/capif.git | - | false |
| `ocf_any_branch` | OCF Repository Branch to use | str | staging | - | false |
| `ocf_any_vault_namespace` | Vault namespace | str | ocf-vault | - | false |
| `ocf_any_vault_storage_class` | Vault Storage class | str | longhorn | - | false |
| `ocf_any_vault_storage_size` | Vault Storage Size | str | 1Gi | - | false |
| `ocf_any_monitoring_namespace` | Monitoring namespace | str | ocf-monitoring | - | false |
| `ocf_any_monitoring_service_name` | Monitoring service name | str | monitoring | - | false |
| `ocf_any_capif_namespace` | CAPIF namespace | str | ocf-capif | - | false |
| `ocf_any_capif_resources_reserve` | CAPIF resource reserve | str | NO | - | false |
| `ocf_any_capif_storage_class` | CAPIF storage class to use | str | longhorn | - | false |
| `ocf_any_capif_storage_access_mode` | CAPIF storage access mode | str | ReadWriteOnce | ReadWriteMany, ReadWriteOnce | false |
| `ocf_any_capif_grafana_storage_size` | Grafana storage size | str | 1Gi | - | false |
| `ocf_any_capif_loki_storage_size` | Loki storage size | str | 100Mi | - | false |
| `ocf_any_capif_mongo_storage_size` | CAPIF Mongo storage size | str | 1Gi | - | false |
| `ocf_any_capif_mongo_register_storage_size` | Register Mongo storage size | str | 1Gi | - | false |
| `ocf_any_capif_tempo_storage_size` | Tempo Persistent storage size | str | 1Gi | - | false |
| `ocf_any_user` | User to be registered on OpenCAPIF | str | client | - | false |
| `ocf_any_password` | User password to be registered on OpenCAPIF | str | password | - | false |
| `ocf_any_tests_to_pass` | Tests to launch after deployment | str | smoke | smoke, all | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.  

- `ocf-component_type`: `"ocf"`
- `ocf-ocf_metadata`: Object containing metadata values based on inputs and site variables, e.g. selected repo, branch, storage size, hostname, and registered user.
