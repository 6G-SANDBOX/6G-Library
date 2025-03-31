# INT-P4 Software Component
The INT-P4 SW component implements In-band Network Telemetry (INT) functionality using P4, enabling real-time network performance monitoring without affecting data traffic.

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
