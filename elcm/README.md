# ELCM (Experiment Lifecycle Management)

ELCM is a system designed to manage the complete lifecycle of experiments in research and development environments. Its goal is to streamline the planning, deployment, monitoring, and termination of experiments efficiently and automatically.

![ELCM](https://github.com/6G-SANDBOX/6G-Library/blob/assets/elcm/elcm.png)

## Maintainers

- Almudena Díaz Zayas <adz@uma.es>
- Carlos Andreo López <c.andreo@uma.es>

## Short Description

Deploy a virtual machine with the ELCM backend and frontend.

## Long Description

ELCM (Experiment Lifecycle Management) is a system designed to manage the complete lifecycle of experiments in research and development environments. It provides a REST API backend built with Python and a web-based frontend also developed in Python.

The experiments executed through ELCM allow storing collected metrics in an InfluxDB database, enabling efficient data storage and retrieval for further analysis.

The ELCM component supports linking Grafana and InfluxDB components or directly using the Monitoring component that includes Grafana, InfluxDB and Prometheus in a virtual machine.

## Hypervisors

- one

## Depends on

- tn_bastion
- [influxdb, monitoring]
- [grafana, monitoring]

## Tags

- ELCM
- Experiment Lifecycle Management

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `template_id` | ID of the ELCM VM template to use in your OpenNebula environment |
| `image_id` | ID of the ELCM VM image to use in your OpenNebula environment |

## Input variables

| Variable | Description | Type | Default | Choices | Required When |
|----------|-------------|------|---------|---------|----------------|
| one_elcm_influxdb | Name of the InfluxDB component used to store the metrics | influxdb or monitoring | - | - | true |
| one_elcm_grafana | Name of the Grafana component used to visualize the metrics | grafana or monitoring | - | - | true |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components.  
Each variable has a short description of what it does for informational purposes.

- `__entity_name__component_type`: "elcm"
- `__entity_name__id`: "VM ID in OpenNebula. Generated from Terraform Manifest"
- `__entity_name__ips`: "Dictionary of VM IP addresses: {<VNet ID in OpenNebula>: <IP address>}. Generated from Terraform Manifest"

## More Information  

For further details, check the official documentation:  

- [**Backend**](https://gitlab.com/morse-uma/elcm)  
- [**Frontend**](https://gitlab.com/morse-uma/elcm-portal)
