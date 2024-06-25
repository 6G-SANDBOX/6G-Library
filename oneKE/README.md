# oneKE

Kubernetes cluster based on the oneKE service created and mantained by OpenNebula.
It creates a rancher-based kubernetes cluster with 6 different VMs: vnf_0, master_0, worker_0, storage_0, storage_1 and storage_2
More info in the official documentation: https://github.com/OpenNebula/one-apps/wiki/oneke_intro

Template needs to have a non-Default CPU architecture, just like the MinIO

NOTE: Cluster customization will be progresivelly incorporated ad the OpenNebula Terraform provider gets more functionalities
