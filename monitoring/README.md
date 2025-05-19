# Monitoring

Monitoring is composed of the following components:

InfluxDB is a time-series database designed to handle large volumes of event and metric data. It is ideal for applications that require high performance in data writes, such as infrastructure monitoring, log analysis, or IoT sensor metrics. In this project, InfluxDB is used to store and query time-based data, enabling trend analysis, alerting, and real-time visualization. Its ability to efficiently manage large amounts of data and integration with various visualization tools like Grafana make InfluxDB an excellent solution for such applications.

Grafana is an open-source platform for monitoring and observability. It provides a powerful and flexible way to visualize data from various sources, including databases, cloud services, and other monitoring tools. Grafana allows users to create interactive dashboards, set up alerts, and analyze data in real-time. It is widely used for infrastructure monitoring, application performance management, and business intelligence.

Prometheus is an open-source systems monitoring and alerting toolkit originally built at SoundCloud. It is designed for reliability and scalability, allowing users to collect and store metrics as time series data, providing powerful querying capabilities and a flexible alerting system. Prometheus is widely used for monitoring cloud-native applications, microservices and containerized environments, making it a popular choice in the DevOps community.

## Maintainers

- Carlos Andreo López <c.andreo@uma.es>

## Short Description

Deploy a virtual machine with InfluxDB v1 or v2, Grafana and Prometheus.

## Long Description

InfluxDB is an open-source time-series database designed to handle high write and query loads. It is optimized for fast, high-availability storage and retrieval of time series data, making it ideal for use cases such as monitoring, analytics and IoT applications. InfluxDB provides a powerful query language (InfluxQL) and supports various data formats, allowing users to easily store, retrieve, and analyze time-based data. With its built-in support for downsampling, retention policies, and continuous queries, InfluxDB enables efficient management of large volumes of time series data.

Grafana is an open-source analytics and monitoring platform that allows users to visualize and analyze data from various sources in real-time. It provides a powerful and flexible interface for creating interactive dashboards, enabling users to monitor metrics, logs, and other data in a visually appealing way. Grafana supports a wide range of data sources, including time-series databases like InfluxDB, Prometheus and Elasticsearch, making it an ideal choice for infrastructure monitoring, application performance management, and business intelligence.

Prometheus is an open-source systems monitoring and alerting toolkit originally built at SoundCloud. It is designed for reliability and scalability, allowing users to collect and store metrics as time series data, providing powerful querying capabilities and a flexible alerting system. Prometheus is widely used for monitoring cloud-native applications, microservices and containerized environments, making it a popular choice in the DevOps community.

## Hypervisors

- one

## Depends on

- tn_bastion

## Tags

- InfluxDB v1
- InfluxDB v2
- Grafana
- Prometheus
- Monitoring

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the Monitoring VM template to use in your OpenNebula environment |
| `image_id` | ID of the Monitoring VM image to use in your OpenNebula environment |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| one_influxdb_version | InfluxDB version to be installed. Format: X.X.X. Example: 2.7.11 | string | 2.7.11 | - | false |
| one_influxdb_user | Username used to login into the InfluxDB | string | admin | - | false |
| one_influxdb_password | Password used to login into the InfluxDB. Required to be at least 8 characters long | string | adminadmin | - | false |
| one_influxdb_org | Organization name. Required if InfluxDB v2 is selected | string | dummyorg | - | false |
| one_influxdb_bucket | Bucket name. For InfluxDB v1, it is the name of the database | string | dummybucket | - | false |
| one_influxdb_token | Token used to login into the InfluxDB. Required if InfluxDB v2 is selected | string | dummydummytoken | - | false |
| one_grafana_version | Grafana version to be installed. Format: X.X.X. Example: 11.6.0 | string | 11.6.0 | - | false |
| one_grafana_password | Password of the 'admin' user in Grafana. Required to be at least 8 characters long | string | adminadmin | - | false |
| one_prometheus_version | Prometheus version to be installed. Format: X.X.X. Example: 2.53.4 | string | 2.53.4 | - | false |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.

- `__entity_name__component_type`: "monitoring"
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"
- `__entity_name__grafana_conf`: # Dictionay of Grafana configuration. Generated from Terraform Manifest
    - `version`: input.one_grafana_version
    - `user`: "admin"
    - `password`: input.one_grafana_version
    - `host`: "First IP address of the VM. Generally the one in the tn_vxlan network"
    - `port`: 3000
- `__entity_name__influx_conf`: # Dictionay of InfluxDB configuration. Generated from Terraform Manifest
    - `version`: input.one_influxdb_version
    - `user`: input.one_influxdb_user
    - `password`: input.one_influxdb_password
    - `org`: input.one_influxdb_org
    - `bucket`: input.one_influxdb_bucket
    - `token`: input.one_influxdb_token
    - `host`: "First IP address of the VM. Generally the one in the tn_vxlan network"
    - `port`: 8086
