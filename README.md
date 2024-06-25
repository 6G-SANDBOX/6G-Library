<div align="center">

  [![Contributors][contributors-shield]][contributors-url]
  [![Forks][forks-shield]][forks-url]
  [![Stargazers][stars-shield]][stars-url]
  [![Issues][issues-shield]][issues-url]

<a href="https://6g-sandbox.eu/">
    <img src="https://6g-sandbox.eu/wp-content/uploads/2023/01/6g-sandbox-logo-2-300x138.jpg" alt="6G-Sandbox logo" title="6G-Sandbox" align="right" height="60" />
</a>

# 6G-SANDBOX SNS Library
  [![6G SANDBOX LIBRARY][6glibrary-badge]][6glibrary-url]

  [Report error](https://github.com/6G-SANDBOX/6G-Library/issues/new?assignees=&labels=&projects=&template=bug_report.md) · [Feature request](https://github.com/6G-SANDBOX/6G-Library/issues/new?assignees=&labels=&projects=&template=feature_request.md)
</div>

[6G-SANDBOX](https://6g-sandbox.eu/) is a HE funded research project (HORIZON-JU-SNS-2022-STREAM-C-01-01). The 6G-SANDBOX project brings a complete and modular facility for the European experimentation ecosystem (in line and under the directions set by SNS JU), which is expected to support for the next decade technology and research validation processes needed in the pathway towards 6G.

![6gsandbox-facility](https://6g-sandbox.eu/wp-content/uploads/2024/01/UPDATED-ARCHITECTURE-FIGURE-2024-1030x885.png)

To design these experiments, a 6G library will be available. This library contains modules and physical equipment that will be used to create the experiments that we call Trial Networks.
A Trial Network is a fully configurable, manageable and controllable environment, that combines virtual and physical components to model a 6G network environment.
The execution of experiments within a Trial Network, allows the capture of a multitude of KPIs to validate new technologies

In simple words, the 6G library is the catalog of building blocks that can be used to create a Trial Network environment.

## Table Of Content

- [Why do we use a git repository?](#why-do-we-use-a-git-repository)
- [Component Structure](#component-structure)
  * [Tools](#tools)
  * [Directory Structure](#directory-structure)
    + [.tnlcm/](#tnlcm)
    + [code/](#code)
    + [result_templates/](#result_templates)
    + [variables/](#variables)
- [Links](#links)

## Why do we use a git repository?

Git is a version control system used for tracking changes in computer files. It is generally used for source code management in software development.

+ Git is used to tracking changes in the source code. Every change is tracked.
+ It allows go back in the repository history at any point in time.
+ It allows collaborative work. Multiple developers to work together.
+ It supports non-linear development through its thousands of parallel branches
+ It is an standard on industry and is widely integrated by automation tools.

In the other hand Everything as Code (EaC) is a quite common approach in DevOps and software development that uses code to define and manage IT resources. Popular examples of EaC are Infrastructure as Code (IaC), Configuration as Code (CaC) or declarative pipelines (PaC).

Every component of this library has been developed following the EaC philosophy: each component is **self-contained** within its necessary scripts and automations to be deployed in a 6G-SANDBOX platform. 

In order to achieve uniformity in the creation of the components and their subsequent integration, a series of requirements have been defined and some good practises should be followed. All the components must meet those conditions for a correct integration with the rest of the components of a 6G-SANDBOX platform.

## Component Structure

The architecture of a component has been designed thinking on simplicity, clarity, variety, scalability and extensibility. 

For the sake of simplicity, two aspects are closed for every component: usable tools and folder structure.

### Tools

These are the selected tools used to automate how to create and configure a component:

<img width="800" alt="component_structure" src="https://raw.githubusercontent.com/6G-SANDBOX/6G-Library/assets/images/tools_logo.png">

+ **[Terraform](https://www.terraform.io/)**: To create virtual infrastructure on the private cloud providers. Components like virtual networks, virtual machines, containers, k8s clusters, etc...
+ **[Ansible](https://www.ansible.com/)**: To configure components, equipments, integrations. Fine fine-grained actions over the Trial Network components. Idempotent warranty.
+ **[Jenkins](https://www.jenkins.io/)**: To execute and orchestrate a component deployment.
+ **[Bash](https://www.gnu.org/software/bash/)**: To allow scripting on standalone operations.

### Component Directory Structure
```
Component_Name
├── .tnlcm/
│   └── public.yaml
├── code/
│   ├── all/
│   ├── one/
│   │   ├── cac/
│   │   └── iac/
│   ├── vmware/
│   │   ├── cac/
│   │   └── iac/
│   └── component_playbook.yaml
├── result_templates/
│   ├── fail_result.md.j2
│   └── ok_result.md.j2
├── variables/
│   ├── one/
│   │   └── private.yaml
│   └── vmware/
│       └── private.yaml
├── changelog.md
├── README.md
└── sample_input_file.yaml
```

#### .tnlcm/

Information used by the TNLCM to integrate the component into Trial Network descriptors.
+ **public.yaml**: File describing metadata, input and output variables for the component. Further explanations in the [.dummy_component](https://github.com/6G-SANDBOX/6G-Library/main/.dummy_component/.tnlcm/public.yaml) example


#### code/

+ **component_playbook.yaml**: Ansible playbook that starts the deployment with the variables passed by the TNLCM. Task files imported to its workflow can be unique to the component or common to all of them.

+ **`site_hypervisor`/**: Directory with CaC and IaC files adapted to a specific cloud provider (`one/`, `vmware/`, etc.) or to any of them (`all/`).

    + **cac/**: Files related to configuration automation. Ideally only Ansible files. Complex configurations can distinguish different phases:
        + **pre/**: Preliminary tasks related to the configuration of the environment that deploys the component.
        + **install/**: Tasks to be executed during the component configuration
        + **post/**: Colorary tasks to be executed after the compoent is already configured.
    + **iac/**: Files related to infrastructure definition. Ideally only jinja2 templates of Terraform manifests. 

#### result_templates/

Two jinja2 templates of markdown files related to two possible scenarios:
- **fail_result.md.j2**: Templated and sent to the TNLCM when the component deployment was failed. Includes the corresponding stderr.
- **ok_result.md.j2**: Templated and sent to the TNLCM when the component deployment was successful. Includes detailed information for the experimenter about the component.

#### variables/

Variable files sepparated by the cloud provider used by each site.

+ **private.yaml**: Default variables and their value. Their value depends on the selected site, and some of them can be overwritten by the TNLCM input.

## Links

* [6G-SANDBOX SITE](https://6g-sandbox.eu/)


<!-- Urls, Shields and Badges -->
[contributors-shield]: https://img.shields.io/github/contributors/6G-SANDBOX/6G-Library.svg?style=for-the-badge
[contributors-url]: https://github.com/6G-SANDBOX/6G-Library/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/6G-SANDBOX/6G-Library.svg?style=for-the-badge
[forks-url]: https://github.com/6G-SANDBOX/6G-Library/network/members
[stars-shield]: https://img.shields.io/github/stars/6G-SANDBOX/6G-Library.svg?style=for-the-badge
[stars-url]: https://github.com/6G-SANDBOX/6G-Library/stargazers
[issues-shield]: https://img.shields.io/github/issues/6G-SANDBOX/6G-Library.svg?style=for-the-badge
[issues-url]: https://github.com/6G-SANDBOX/6G-Library/issues

[6glibrary-badge]: https://img.shields.io/badge/6G--Library-v0.2.0-blue
[6glibrary-url]: https://github.com/6G-SANDBOX/6G-Library/releases/tag/v0.2.0
