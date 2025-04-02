# ELCM (Experiment Lifecycle Management)

ELCM is a system designed to manage the complete lifecycle of experiments in research and development environments. Its goal is to streamline the planning, deployment, monitoring, and termination of experiments efficiently and automatically.

## Key Features

- **Centralized management**: provides a unified platform to handle all stages of the experiment lifecycle.  
- **Automation**: simplifies the creation, execution, and termination of experiments with a high degree of automation.  
- **Monitoring and analysis**: offers tools to track the status and performance of experiments in real time.  
- **Metrics storage**: experiments executed through ELCM can store collected metrics in an InfluxDB database for further analysis.  
- **Integration with test infrastructure**: compatible with distributed test environments and advanced experimentation technologies.  

## Technologies  

- **Backend**: a REST API built with Python.  
- **Frontend**: a web application built with Python.  
- **Database**: influxDB for storing experiment metrics.  
- **Visualization**: grafana for real-time metric visualization.  

## More Information  

For further details, check the official documentation:  

- [**Backend**](https://gitlab.com/morse-uma/elcm)  
- [**Frontend**](https://gitlab.com/morse-uma/elcm-portal)  

![ELCM](https://github.com/6G-SANDBOX/6G-Library/blob/assets/elcm/elcm.png)
