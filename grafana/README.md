# Grafana

Grafana is an open-source platform for monitoring and observability. It provides a powerful and flexible way to visualize data from various sources, including databases, cloud services, and other monitoring tools. Grafana allows users to create interactive dashboards, set up alerts, and analyze data in real-time. It is widely used for infrastructure monitoring, application performance management, and business intelligence.

## Maintainers

- Carlos Andreo López <c.andreo@uma.es>

## Short Description

Deploy a virtual machine with Grafana.

## Long Description

Grafana is an open-source analytics and monitoring platform that allows users to visualize and analyze data from various sources in real-time. It provides a powerful and flexible interface for creating interactive dashboards, enabling users to monitor metrics, logs, and other data in a visually appealing way. Grafana supports a wide range of data sources, including time-series databases like InfluxDB, Prometheus and Elasticsearch, making it an ideal choice for infrastructure monitoring, application performance management, and business intelligence.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- Grafana

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the Grafana VM template to use in your OpenNebula environment |
| `image_id` | ID of the Grafana VM image to use in your OpenNebula environment |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| one_grafana_version | Grafana version to be installed. Format: X.X.X. Example: 11.6.0 | string | 11.6.0 | - | false |
| one_grafana_password | Password of the 'admin' user in Grafana. Required to be at least 8 characters long | string | adminadmin | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.

- `__entity_name__component_type`: "grafana"
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
- `__entity_name__grafana_conf`: # Dictionay of Grafana configuration. Generated from Terraform Manifest
    - `version`: input.one_grafana_version
    - `user`: "admin"
    - `password`: input.one_grafana_version
    - `host`: "First IP address of the VM. Generally the one in the tn_vxlan network"
    - `port`: 3000