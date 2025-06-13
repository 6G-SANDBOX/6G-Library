# INT-P4 Software Component
The INT-P4 SW component implements In-band Network Telemetry (INT) functionality using P4, enabling real-time network performance monitoring without affecting data traffic.

## Maintainers

- Rafael Manuel García Arévalo <rafagarciaa31@uma.es>

## Short Description

P4-based In-band Network Telemetry (INT) implementation with source, transit, and sink switches plus integrated collector for real-time network monitoring.

## Long Description

The INT-P4 SW component deploys a comprehensive In-band Network Telemetry (INT) solution using P4 programming language on Ubuntu 22.04 LTS virtual machines. This component implements a complete INT pipeline consisting of source switches (that insert telemetry headers), transit switches (that add hop-by-hop metadata), and sink switches (that extract and report telemetry data) along with a dedicated collector virtual machine for data processing and visualization.

The component leverages P4-based data plane running on BMv2 with Stratum, Python-based controllers for INT management, and includes InfluxDB and Grafana integration for telemetry data storage and visualization. All services are containerized using Docker for easy deployment and management.

This component requires a previous "tn_bastion" component to be previously deployed and referenced in the networks configuration. The component uses custom appliances that must be previously installed in your OpenNebula site. The component is specifically designed for network monitoring and performance analysis in trial networks, supporting flexible configurations for different network topologies and telemetry requirements.

## Hypervisors

- OpenNebula (one)

## Depends on

- tn_bastion

## Tags

- VM
- appliance
- monitoring
- P4
- INT
- telemetry
- networking
- SDN
- trial network

## Site-specific variables

| Variable | Description |
|----------|-------------|
| `collector_template_id` | ID of the INT-P4 Collector VM template to use in your OpenNebula environment |
| `collector_image_id` | ID of the INT-P4 Collector VM image to use in your OpenNebula environment |
| `switch_template_id` | ID of the Ubuntu 22.04 LTS VM template to use in your OpenNebula environment |
| `switch_image_id` | ID of the Ubuntu 22.04 LTS VM image to use in your OpenNebula environment |
| `harbor_token` | Token to authenticate against the Harbor registry (https://dockerhub.mobilesandbox.cloud:9443) |

## Input variables

| Variable | Description | Type | Default | Required When |
|----------|-------------|------|---------|----------------|
| `one_int_p4_sw_cpu` | Number of CPUs for all VMs (switches and collector) | int | 2 | false |
| `one_int_p4_sw_memory` | Memory in MB for all VMs (switches and collector) | int | 4096 | false |
| `one_int_p4_sw_disk` | Disk size in MB for all VMs (switches and collector) | int | 25600 | false |
| `one_int_p4_sw_switches` | Configuration for INT-P4 switches with type, networks, and detailed INT configuration | dict | [Complex default configuration] | true |
| `one_int_p4_sw_collector` | Configuration for INT-P4 collector including networks and collector-specific settings | dict | [Default collector configuration] | true |

## Generated terraform outputs

Unknown before the deployment, they are stored in the S3 Object Storage, and available to use by future components. Each variable has a short description of what it does for informational purposes.

- **__entity_name__component_type**: "int_p4_sw"
- **__entity_name__int_p4_sw_metadata**: Contains comprehensive INT metadata including switch IPs, switch IDs, collector IP, collector ID, and bastion IP for network monitoring and management

## 📚 What is INT-P4 SW?
INT-P4 SW is a software-based implementation of in-band telemetry that allows collecting detailed information about network state as traffic flows through it. Using P4 to program data plane behavior, this component can insert, collect, and process telemetry metadata in real-time.

By employing a Next Generation Software Defined Networking (NG-SDN) approach, INT-P4 SW allows programmable network monitoring with minimal overhead, providing deep insights into network behavior and performance metrics. This makes it an invaluable tool for network operators and researchers who need to understand the real-time behavior of their networks.

## 🔧 Architecture Overview
The INT-P4 SW component consists of three main parts:
1. **Data Plane**: P4-based packet processing pipeline running on BMv2 with Stratum as the data plane operating system
2. **Control Plane**: Python-based controller for INT management
3. **Collector System**: Dedicated collector and visualization stack for telemetry data processing

Key interactions include:
- P4Runtime between controller and data plane
- gRPC-based telemetry reporting from sink switches
- InfluxDB time series database for metrics storage
- Grafana dashboards for visualization

## ✨ Key Features

* **P4-based Data Plane:** The data plane logic is defined using the P4 language, providing fine-grained control over packet processing and forwarding.
* **INT Switch Types:** Supports three types of switches:
  * **Source:** Initiates telemetry by inserting INT headers
  * **Transit:** Adds telemetry information as packets pass through
  * **Sink:** Extracts and reports collected telemetry information
* **INT Collector:** Includes a dedicated collector that receives, processes, and stores telemetry data. Based on the [int-collector](https://github.com/GEANT-DataPlaneProgramming/int-collector).
* **BMv2 Integration:** The P4 program runs on the BMv2 virtual switch, facilitating development and testing
* **Data Visualization:** Integration with InfluxDB and Grafana for metric storage and visualization

## 📦 Component Versioning
The INT-P4 SW component uses different versioning schemes:

### Individual Services Versions
The component uses specific versions for its core services:

1. **Data Plane (BMv2-Stratum)**
   - Version format: `ubuntu{version}-stratum-bmv2_{version}`
   - Current version: `ubuntu18-stratum-bmv2_0.0.1`
   - Used for specific Ubuntu + Stratum-BMv2 combinations

2. **Control Plane (INT Controller)**
   - Version format: `{major}.{minor}.{patch}`
   - Current version: `2.0.0`
   - Follows semantic versioning for controller updates
   - New features in version 2.0.0:
     - Enhanced logging functionality for the control plane
     - Improved ARP handling for better neighbor discovery
     - Better overall stability and error handling
     - Improved INT metadata processing

3. **INT Collector**
   - Based on the [int-collector](https://github.com/GEANT-DataPlaneProgramming/int-collector) project
   - Integrated with InfluxDB and Grafana for data storage and visualization

### Component Version
The overall INT-P4 SW component in 6G-Library uses its own version:
- Format: `v{major}.{minor}.{patch}`
- Current version: `v1.1.0`
- Documented in [CHANGELOG.md](./changelog.md)

> [!NOTE]
> The component version may differ from individual service versions, as it represents the integration state within 6G-Library rather than individual service development.

## 🛠️ Appliance Installation
The INT-P4 SW component requires custom appliances to be installed in your OpenNebula site before deployment:

### INT Collector Appliance
The component uses a specialized INT collector appliance that must be available in your OpenNebula marketplace:

1. **Appliance URL**: `https://marketplace.mobilesandbox.cloud:9443/appliance/service_Collector_INT`
2. **Requirements**: 
   - Access to the Harbor registry (https://dockerhub.mobilesandbox.cloud:9443)
   - Valid `harbor_token` configured in site variables
   - OpenNebula marketplace access

### Site Configuration
Ensure the following variables are properly configured in your site repository:
- `collector_template_id`: Template ID for the INT collector VM
- `collector_image_id`: Image ID for the INT collector VM  
- `switch_template_id`: Template ID for Ubuntu 22.04 LTS (for switches)
- `switch_image_id`: Image ID for Ubuntu 22.04 LTS (for switches)
- `harbor_token`: Authentication token for Docker registry access

## 📝 Configuration
The INT-P4 SW component's configuration is managed through files located at `/home/jenkins/config`. This directory contains all necessary files to configure the behavior of INT-P4 switches and collector.

> [!NOTE]
> All these configuration files are **automatically configured when deploying the component** according to the parameters entered by the experimenter through the TNLCM. The description in this section is only in case you want to modify them manually.

### Configuration Directory Structure

#### 1. `/home/jenkins/config/controller-int/`

- **Description**: Contains the configuration for the INT-P4 controller
- **Key File**: `config.yaml`
  - **Purpose**: Defines parameters for each INT switch type
  - **Configurations Include**:
    - **Source Switch**:
      - Ingress port
      - Egress port
      - Metadata length
      - Maximum hop count
      - Instruction mask
    - **Transit Switch**:
      - Switch ID
    - **Sink Switch**:
      - Reporting port
      - Collector configuration
    - **Forwarding Rules**:
      - Destination MAC addresses
      - Output ports
      - Priorities

#### 2. `/home/jenkins/config/stratum-int/`

- **Description**: Contains P4 data plane configuration
- **Key Files**:
  - `chassis_config.pb.txt`
    - **Purpose**: Defines physical and virtual port mappings
    - **Configurations**:
      - Port IDs
      - Associated network interfaces
  - Other P4-specific configuration files

### Applying Configuration Changes

To apply configuration changes:

1. **Navigate to Docker Compose Directory**:
   ```bash
   cd /home/jenkins/
   ```

2. **Stop the Containers**:
   ```bash
   docker compose down
   ```

3. **Start Services with New Configuration**:
   ```bash
   docker compose up -d
   ```

## 📋 Logs and Monitoring
### Accessing Component Logs

#### INT Switch Logs
```bash
# Data plane logs (Stratum BMv2)
docker logs int-p4-bmv2-stratum-optimized

# Control plane logs
docker logs p4-int-controller-bmv2
```

#### INT Collector Logs
```bash
# View logs in real-time
tail -f /var/log/int_collector.log
```

### Debug Mode
To enable debug mode for more detailed logging:

1. Modify the `docker-compose.yaml` file:
   ```yaml
   command: ["-bmv2_log_level=debug"]
   ```
2. Restart the services:
   ```bash
   docker compose down
   docker compose up -d
   ```
   

### Monitoring Metrics
TODO

## 🔍 Troubleshooting
TODO

## 📚 References
- [INT Specification](https://p4.org/p4-spec/docs/INT_v1_0.pdf)
- [P4 Language Specification](https://p4.org/p4-spec/p4-16/docs/P4-16-spec.html)
- [BMv2 Github Repository](https://github.com/p4lang/behavioral-model)
- [Stratum SDN Switch OS](https://github.com/stratum/stratum)

![int_p4_sw](https://github.com/6G-SANDBOX/6G-Library/blob/assets/int_p4_sw/int_p4_sw.png)
