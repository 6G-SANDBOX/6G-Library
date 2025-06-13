# Prometheus

Prometheus is an open-source systems monitoring and alerting toolkit originally built at SoundCloud. It is designed for reliability and scalability, allowing users to collect and store metrics as time series data, providing powerful querying capabilities and a flexible alerting system. Prometheus is widely used for monitoring cloud-native applications, microservices and containerized environments, making it a popular choice in the DevOps community.

## Maintainers

- Carlos Andreo López <c.andreo@uma.es>

## Short Description

Deploy a virtual machine with Prometheus.

## Long Description

Prometheus is an open-source systems monitoring and alerting toolkit originally built at SoundCloud. It is designed for reliability and scalability, allowing users to collect and store metrics as time series data, providing powerful querying capabilities and a flexible alerting system. Prometheus is widely used for monitoring cloud-native applications, microservices and containerized environments, making it a popular choice in the DevOps community.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- Prometheus

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the Prometheus VM template to use in your OpenNebula environment |
| `image_id` | ID of the Prometheus VM image to use in your OpenNebula environment |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| one_prometheus_version | Prometheus version to be installed. Format: X.X.X. Example: 2.53.4 | string | 2.53.4 | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.

- `__entity_name__component_type`: "prometheus"
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
