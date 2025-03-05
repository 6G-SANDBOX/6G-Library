# UPF-P4 Software Component
The UPF-P4 SW component allows a P4-based implementation of the User Plane Function (UPF) for 5G networks. Additionally, this component includes a 5G Open5GS Release 16 control plane, which is compatible with the developed UPF-P4.

## 📚 What is UPF-P4 SW?
UPF-P4 SW is a software-based implementation of the 5G UPF, a critical network function responsible for handling user plane traffic within a 5G core network. This implementation leverages the power of P4 to define and control the packet processing behavior of the UPF, enabling flexible and efficient traffic management. 

By employing a Next Generation Software Defined Networking (NG-SDN) approach, UPF-P4 SW allows programming of both the control plane and the data plane, providing complete control and flexibility over network packet processing. This results in a UPF module that is efficient, entirely flexible, and real-time controllable.

## 🔧 Architecture Overview
The UPF-P4 SW component consists of three main parts:
1. **Data Plane**: P4-based packet processing pipeline running on BMv2 with Stratum as the data plane operating system
2. **Control Plane**: Python-based controller for UPF management
3. **5G Core**: Open5GS Release 16 components for network control

Key interactions include:
- P4Runtime between controller and data plane
- PFCP protocol between SMF and UPF
- GTP tunneling for user traffic

## ✨ Key Features

* **P4-based Data Plane:** The UPF's data plane logic is defined using the P4 language, providing fine-grained control over packet processing and forwarding. This allows users to add, modify, or remove algorithms, protocols, and functions in a completely customizable manner.
* **BMv2 Integration:** The P4 program is compiled and executed on the BMv2 software switch, enabling packet processing within a virtualized environment and facilitating rapid development and testing.
* **Open5GS Control Plane Integration:** The UPF interacts seamlessly with the Open5GS control plane to receive configuration and policy updates, ensuring full compliance with 3GPP technical specifications and integration with the 5G core network.
* **Trial Network Deployment:** Specifically designed for deployment within 5G trial networks, such as the Málaga platform for the 6G-SANDBOX project, providing a flexible and scalable solution for testing and evaluating 5G user plane functionality.

## 📦 Component Versioning
The UPF-P4 SW component uses three different versioning schemes:

### Individual Services Versions
The component uses specific versions for its core services:

1. **Data Plane (BMv2-Stratum)**
   - Version format: `ubuntu{version}-stratum-bmv2_{version}`
   - Example: `ubuntu18-stratum-bmv2_0.0.1`
   - Used for specific Ubuntu + Stratum-BMv2 combinations

2. **Control Plane (UPF Controller)**
   - Version format: `v{major}.{minor}.{patch}`
   - Example: `v2.0.0`
   - Follows semantic versioning for controller updates (It also includes the P4 data plane)
  
3. **Open5GS Control Plane**
   - Version format: `{major}.{minor}.{patch}`
   - Example: `2.5.6`
   - Includes all core network functions (AMF, SMF, etc.)
   - Compatible with 3GPP Release 16 specifications

### Component Version
The overall UPF-P4 SW component in 6G-Library uses its own version:
- Format: `v{major}.{minor}.{patch}`
- Example: `v0.5.0`
- Documented in [CHANGELOG.md](./CHANGELOG.md)

> [!NOTE]
> The component version may differ from individual service versions, as it represents the integration state within 6G-Library rather than individual service development.

## 📝 Configuration
The UPF-P4 software component's configuration is managed through a set of files and directories located at `/home/jenkins/config`. This directory contains all the necessary configuration files to set up and customize the behavior of the UPF-P4 data plane and its integration with the Open5GS control plane.

> [!NOTE]
> All these configuration files are **automatically configured when deploying the component** according to the parameters entered by the user through the TNLCM. The description in this section is only in case you want to modify them manually.

### Configuration Directory Structure
Here's a detailed overview of the configuration directory and its contents:

#### 1. `/home/jenkins/config/controller-upf/`

- **Description**: This directory contains the configuration for the UPF-P4 Python controller.
- **Key File**: `config.yaml`
  - **Purpose**: Defines various parameters for the UPF-P4 controller.
  - **Configurations Include**:
    - **Interface Settings**:
      - `n3` Interface (Access Network Interface)
        - IPv4 Address
        - MAC Address
      - `n6` Interface (External Data Network Interface)
        - IPv4 Address
        - MAC Address
      - `n4` Interface (Control Plane Interface)
        - IPv4 Address
        - MAC Address
    - **UE IP Pool**:
      - Defines the range of IP addresses to be assigned to User Equipments (UEs).

#### 2. `/home/jenkins/config/freeDiameter/`

- **Description**: Contains configuration files and keys for freeDiameter, which is used by Open5GS for Diameter protocol operations.
- **Note**: **Do not modify**.

#### 3. `/home/jenkins/config/open5gs/`

- **Description**: Houses individual YAML configuration files for each Open5GS network function.
- **Key Files**:
  - `amf.yaml` - Access and Mobility Management Function configuration
  - `smf.yaml` - Session Management Function configuration
  - `nrf.yaml` - Network Repository Function configuration
  - ...and others for each Open5GS component.
- **Configurations Include**:
  - Network interface bindings
  - Database settings
  - PLMN (Public Land Mobile Network) configurations
  - Subscriber data management
  - Inter-component communication settings

#### 4. `/home/jenkins/config/register_subscriber.sh`

- **Description**: A shell script used to register new UEs (User Equipments) into the Open5GS core network.
- **Usage**:
  - Automates the process of adding subscriber information to the Open5GS database.
  - Allows specifying parameters such as IMSI, authentication keys, and subscriber profiles.
- **Instructions**: For detailed steps on registering new UEs, please refer to the [Registering New User Equipments](#registering-new-user-equipments) section.

#### 5. `/home/jenkins/config/stratum-upf/`

- **Description**: Contains configuration files necessary for the P4 data plane component of the UPF.
- **Key Files**:
  - `chassis_config.pb.txt`
    - **Purpose**: Defines the physical and virtual port mappings for the UPF-P4 application.
    - **Configurations Include**:
      - Port IDs
      - Associated network interfaces
      - Pipeline stages
  - P4 specific program configurations and artifacts
- **Note**: Modifying these files affects how the UPF handles packet processing at the data plane level. Ensure any changes are consistent with the P4 program logic.

### Applying Configuration Changes
After making changes to the configuration files, it's essential to restart the services for the new configurations to take effect. This involves bringing down the Docker containers and bringing them back up using Docker Compose.

#### Steps to Apply New Configuration:

1. **Navigate to the Docker Compose Directory**:

   Ensure you are in the directory containing the `docker-compose.yml` file for the UPF-P4 and Open5GS services.

   ```bash
   cd /home/jenkins/
   ```

2. **Stop the Running Containers**:

   Bring down the currently running containers to stop the services.

   ```bash
   docker-compose -f docker-compose.yaml down
   ```

   - This command stops and removes the containers, networks, and any associated volumes created by Docker Compose.

3. **Verify the Containers are Stopped**:

   You can list the running containers to ensure they have been stopped.

   ```bash
   docker ps
   ```

   - The list should not include the UPF-P4 or Open5GS containers.

4. **Start the Services with Updated Configurations**:

   Bring up the containers with the updated configurations.

   ```bash
   docker-compose -f docker-compose.yaml up -d
   ```

   - The `-d` flag runs the containers in detached mode (in the background).
   - Docker Compose reads the `docker-compose.yml` and starts the services with the updated configurations from `/home/jenkins/config`.

5. **Confirm the Services are Running**:

   ```bash
   docker-compose ps
   ```

   - Check that all the necessary services are up and running.

### Important Considerations
- **Consistency Across Files**:

  - Ensure that any IP addresses, ports, and interface names are consistently updated across all relevant configuration files to prevent misconfigurations.
  - For example, if you change the `n4` interface IP address in `config.yaml`, ensure that the corresponding settings in Open5GS configuration files are also updated if necessary.

### Registering new user equipments
By default, the SIM <MCC|MNC|MSIN> is automatically registered with the <Key>, <OPC>, <APN> and <SLICE> configured in the component input parameters.
To add new UEs to the network, you need to register them in the Open5GS core network database using the provided script (`register_subscriber.sh`).

#### Steps:
  1. **Navigate to the config folder**:

  Ensure you are in the directory containing the `register_subscriber.sh` script.

   ```bash
   cd /home/jenkins/config/
   ```

  2. **Modify the register_subscriber.sh script**:

  Open the script with an editor and modify or add a new line taking into account the format:

  ```bash
  docker run -ti --rm \
    --net host \
    -e DB_URI=mongodb://localhost/open5gs \
    gradiant/open5gs-dbctl:0.10.3 "open5gs-dbctl add_ue_with_slice MCC|MNC|MSIN> <Key> <OPC> <APN> <S_NSSAI_SST> <S_NSSAI_SD>"
  ```

  3. **Run the register_subscriber.sh script**:

  ```bash
  ./register_subscriber.sh
  ```

## 📋 Logs and Monitoring
### Accessing Component Logs
To access the logs of any container within the UPF-P4 component, you need to:

1. Access the VM where the component is deployed
2. List all containers to get their IDs:
   ```bash
   docker ps -a
   ```
3. View the logs for a specific container:
   ```bash
   docker logs <container_id>
   ```

### Debug Mode
By default, the `upf-dn-bmv2-stratum` service runs in normal mode. To enable debug logging:

1. Modify the `docker-compose.yaml` file by uncommenting the following line:
   ```yaml
   command: ["-bmv2_log_level=debug"]
   ```
2. Restart the services:
   ```bash
   docker-compose down
   docker-compose up -d
   ```
3. Re-register your UE using the provided script:
   ```bash
   ./config/register_subscriber.sh
   ```

> [!NOTE]
> Running in debug mode will provide more detailed logs but impact performance. Use this mode only when necessary for troubleshooting.

## 🔍 Troubleshooting
### Common Issues
1. **UE Registration Fails**
   - Verify PLMN configuration matches between AMF and UE
   - Check authentication parameters in Open5GS
   - Validate network slice configuration

2. **GTP Tunnel Setup Issues**
   - Verify N3 interface configuration
   - Check TEID allocation in SMF logs
   - Monitor P4 table entries for tunnel rules

3. **Data Plane Connectivity Problems**
   - Validate P4 pipeline configuration
   - Check interface mappings in chassis config
   - Monitor packet counters for drops

TODO: Explain how to do NAT (in other VMs)

## 📚 References
- [P4 Language Specification](https://p4.org/p4-spec/p4-16/docs/P4-16-spec.html)
- [Open5GS Documentation](https://open5gs.org/open5gs/docs/)
- [BMv2 Github Repository](https://github.com/p4lang/behavioral-model)
- [3GPP TS 23.501](https://portal.3gpp.org/desktopmodules/Specifications/SpecificationDetails.aspx?specificationId=3144)
- [Stratum SDN Switch OS](https://github.com/stratum/stratum)

![upf_p4_sw](https://github.com/6G-SANDBOX/6G-Library/blob/assets/upf_p4_sw/upf_p4_sw.png)
