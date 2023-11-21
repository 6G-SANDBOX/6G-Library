<a href="https://6g-sandbox.eu/">
    <img src="https://6g-sandbox.eu/wp-content/uploads/2023/01/6g-sandbox-logo-2-300x138.jpg" alt="6G-Sandbox logo" title="6G-Sandbox" align="right" height="60" />
</a>

# 6G-SANDBOX SNS Library

[6G-SANDBOX](https://6g-sandbox.eu/) is a HE funded research project (HORIZON-JU-SNS-2022-STREAM-C-01-01). The 6G-SANDBOX project brings a complete and modular facility for the European experimentation ecosystem (in line and under the directions set by SNS JU), which is expected to support for the next decade technology and research validation processes needed in the pathway towards 6G.

![6gsandbox-facility](https://6g-sandbox.eu/wp-content/uploads/2022/12/6G_SANBOX-Facility-verview.png)

To design these experiments, a 6G library will be available. This library contains modules and physical equipment that will be used to create the experiment that we call Trial Network.

A Trial Network should be a fully configurable, manageable and controllable environment, combining virtual and physical components and modeling a 6G network environment, allowing experiments to be executed, capturing a multitude of KPIs so that new technologies could be validated.

In simple words, the library is a catalog of building block that we can put together to model the Trial Network

## Table Of Content

- [Why do we use a git repository?](#why-do-we-use-a-git-repository)
- [Component Structure](#component-structure)
  * [Tools](#tools)
  * [Folder Structure](#folder-structure)
    + [Public Folder](#public-folder)
    + [Private Folder](#private-folder)
    + [Documentation Folder](#documentation-folder)
- [Links](#links)

## Why do we use a git repository?

Git is a version control system used for tracking changes in computer files. It is generally used for source code management in software development.

+ Git is used to tracking changes in the source code. Every change is tracked.
+ It allows go back in the repository history at any point in time.
+ It allows collaborative work. Multiple developers to work together.
+ It supports non-linear development through its thousands of parallel branches
+ It is an standard on industry and is widely integrated by automation tools.

In the other hand Everything as Code (EaC) is an approach quite common in software development and DevOps that uses code to define and manage IT resources. Infrastructure as Code, Config as Code, declarative pipelines are examples of EaC that are becoming more popular because of benefits combining Git and EaC

Every component of this library has been developed following the EaC philosophy. Each component is **self-contained** with the automations and scripts necessary to be deployed in a 6G-SANDBOX platform. 

In order to achieve uniformity in the creation of the components and their subsequent integration, a series of requirements have been defined and some good practises should be followed. All the components must meet those conditions for a correct integration with the rest of the components of a 6G-SANDBOX platform.

## Component Structure

The architecture of a component has been designed thinking on simplicity, clarity, variety, scalability and extensibility. 

In order to simplify, there are two aspects that are closed to every component: tools to use and folder structure.

### Tools

These are the selected tools used to automate how to create and configure a component:

<img width="800" alt="component_structure" src="https://raw.githubusercontent.com/6G-SANDBOX/6G-Library/assets/images/tools_logo.png">

+ **Terraform**: To create virtual infrastructure on the private cloud providers. Components like virtual networks, virtual machines, containers, k8s clusters, etc... (https://www.terraform.io/)
+ **Ansible**: To configure components, equipments, integrations. Fine fine-grained actions over the Trial Network components. Idempotent warranty (https://www.ansible.com/).
+ **Jenkins**: To execute and orchestrate a component deployment (https://www.jenkins.io/).
+ **Bash**: To allow scripting on standalone operations (https://www.gnu.org/software/bash/).

### Folder Structure
<img width="500" alt="component_structure" src="https://raw.githubusercontent.com/6G-SANDBOX/6G-Library/assets/images/component_structure.png">

**title: dummy_component structure**

#### Public Folder

Contains the components public information that could be used by third parties to integrate the catalog.

+ **description.yaml (file)**: Is a descriptor file with the component public information. It contains metadata (component name, version, etc, ...) and the public variables that should be used to deploy it.
+ **changelog.yaml (file)**: Is a file with the component historical evolution: new version, bug fixing, etc..

#### Private Folder

Contains the component private files with information and automation that will be used to deploy the component in a 6G-SANDBOX site

+ **values.yaml (file)**: It contains variables needed by automation tools to accomplish deployment and configuration
+ **manifest.yaml (file)**: It contains the flow that must be followed to deploy the component

+ **cloud_provider(folder)**: One folder per cloud provider where the component will be available

    + **cac(folder)**: Contains files related to configuration automation
        + **pre(folder)**: Contains files related to configuration that should be done in advance. For example, gather some information from cloud provider.
        + **install(folder)**: Contains files related to component configuration
        + **post(folder)**: Contains files related to configuration that should be done after component set-up. For example, test execution.
    + **iac(folder)**: Contains files related to infrastructure definition

#### Documentation Folder

It contains, detailed information about the component in MarkDown format. That enables direct integration between third parties and the catalog.

Documentation will be always update with new component versions.

## Links

* [6G-SANDBOX SITE](https://6g-sandbox.eu/)
