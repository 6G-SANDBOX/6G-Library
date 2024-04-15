# Changelog
This branch justifies itself as a testbed for some of the most radical changes in the
**6G Library** repository

## Changes made
- New `.gitignore` file (add your own personal files)
- Deleted `.global/pac/DEPLOY_COMPONENT_FROM_COMMIT.groovy`
- Changed OpenNebula credentials so that `oneadmin` isn't the Jenkins user no more.
A less privileged user named `jenkins-master` will be the one deploying the components.
It is the admin of a newly created group called `6G-Sandbox`, where custom users may be added in the future.
- New `.global/cac/deploy_component.yaml` file with ansible tasks common to all components.
All ansible executions will start from here, to import the corresponding "component playbook"
- Deleted `.global/cac/mix_variables.yaml` file, and `.global/cac/mix_variables_tasks.yaml` renamed to `load_variables.yaml`
- New `.global/hypervisors` directory with the steps currently found in `<component>/private/<hypervisor>/prepare.yml`
- Other lesser changes (format, comments, typos, blank spaces...)

## Changes not implemented yet
- **TODO**: Rename `<component>/private/manifest.yaml` to `<component>/private/playbook.yaml`.
The term manifest is ambiguous an can refer both to the terraform files and to this yaml one.
The name `playbook.yml` is more clear and would lead to less confusion.
- **TODO**: Replace Ansible's `amazon.aws.aws_s3`module to a current one like `amazon.aws.s3_object`

## Changes to discuss
Polemic changes that involve modifying production code.
Some of them just require some defaults in the Jenkins groovy file, so that the new parameters can be defined from web or not.
- Add the `LIBRARY_URL` parameter in Jenkins and it's pipeline, allowing teams to test their own forks of the project.
- Possibility to select the branch in the **6G-Sandbox-Sites** repository. Leverages the fear of breaking working variables to test new ones.
- Add a `SITE_URL` parameter to test forks of the **6G-Sandbox-Sites** repository,

Remember to change from Jenkins the value of the `GITHUB_JENKINS` credential so that it can access all required repos.
