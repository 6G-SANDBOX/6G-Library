# RIS - Reconfigurable Intelligence Surface

Reconfigurable Intelligent Surfaces (RIS) corresponds to a planar surface composed of unit-cells, whose properties can be controlled dynamically to 'tune' the incident wireless signals through reflection, refraction, focusing, collimation, modulation or absorption. RIS can be potentially deployed for both indoor and outdoor usage, including offices, airports, shopping centres, lamp posts and advertising billboards, and may take any shape or be integrated onto objects. Its characteristics may also result in low energy consumption, making RIS a sustainable technology solution. RIS can be configured to operate at any part of the radio spectrum, including frequencies from below 6 GHz to THz, and may harness tools from Artificial Intelligence (AI) and Machine Learning (ML) to enable systems operation and optimization.

As RIS is envisaged to be a new enabling candidate wireless technology for the control of the radio signals between a transmitter and a receiver in a dynamic and goal-oriented way, turning the wireless environment into a service. This has motivated a host of potential new use cases targeting at:

- the enhancement of various system key-performance-indicators (KPIs), and
- the support of new wireless technology applications and capabilities.

These include enhancements to the capacity, coverage, positioning, security, and sustainability, as well as the support of further sensing, wireless power transfer, and ambient backscattering capabilities.

The configuration exposed by the RIS is:

- `ris_f0`: "Frequency (GHz). 26.5GHz – 29.5Ghz"
- `ris_dist`: "Distance (m)"
- `ris_phi_t`: "Incident angle azimuth (degrees 0-360)"
- `ris_theta_t`: "Incident angle polar (degrees 0-90)"
- `ris_num_phi_r`: "Number of azimuth segments (1, num_phi_r * num_theta_r <= 256)"
- `ris_phi_r_low`: "Receiver azimuth low (degrees 0-360)"
- `ris_phi_r_high`: "Receiver azimuth high (degrees 0-360)"
- `ris_num_theta_r`: "Number of polar segments (1, num_phi_r * num_theta_r <= 256)"
- `ris_theta_r_low`: "Receiver polar low (degrees 0-360)"
- `ris_theta_r_high`: "Receiver polar high (degrees 0-360)"
- `ris_reservation_time`: "Time that is reserved to be used (minutes)"

> [!NOTE]  
> Currently this component is only available in the site "uma".