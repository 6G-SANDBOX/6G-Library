# UPF-P4 Hardware Component
The UPF-P4 HW component implements a hardware-accelerated User Plane Function (UPF) for 5G networks using the Intel Tofino 2 P4 programmable ASIC. Like its software counterpart, this component includes a 5G Open5GS Release 16 control plane that is fully compatible with the P4-based UPF data plane.

> [!NOTE] 
> Currently this component is only available in the site "uma".

## 📚 What is UPF-P4 HW?
UPF-P4 HW is a hardware-accelerated implementation of the 5G User Plane Function (UPF), a critical network function responsible for handling user plane traffic within a 5G core network. This implementation leverages the power of P4 programming language and Intel Tofino 2 programmable ASICs to deliver high-performance packet processing at line rate.

By utilizing Intel's programmable switching architecture, this UPF implementation achieves significantly higher throughput and lower latency compared to software-based solutions, making it suitable for production environments and high-demand scenarios. The hardware acceleration enables efficient handling of 5G traffic while maintaining programmability through the P4 language.

## ✨ Key Features

* **Intel Tofino 2 ASIC:** The UPF's data plane leverages Intel's high-performance programmable ASIC, capable of processing packets at multi-gigabit speeds.
* **P4-based Data Plane:** The UPF's data plane logic is defined using the P4 language, providing fine-grained control over packet processing while maintaining hardware acceleration benefits.
* **Line-Rate Performance:** Achieves consistent, deterministic packet processing at the full line rate of the underlying hardware.
* **Open5GS Control Plane Integration:** Seamlessly integrates with the Open5GS control plane to receive configuration and policy updates, ensuring compliance with 3GPP technical specifications.
* **PFCP Protocol Support:** Implements the Packet Forwarding Control Protocol (PFCP) to establish proper communication between the SMF control plane and the UPF data plane.


## 📝 Configuration
# TODO: Permit experimenter access to Intel Tofino HW ? (I dont think so) Permit API call? (I don't think so too). So, I this section only put that If someone want reconfigure UPF, he should recreate component (and TN)
