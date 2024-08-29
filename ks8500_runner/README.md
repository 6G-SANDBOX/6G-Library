# ks8500_runner

The KS8500 Runner can be deployed to allow accessing local ressources such as instrument control interfaces during test executions scheduled from the cloud based KS8500 SaaS

This is the initial version of the automation scripts. Since we do not have access to a realistic testing environment yet, the code has not yet ever been executed as a whole so it is highly unlikely to work at this stage. It should improve as we progress with testing and corrections

# Notes for reviewers (to be handled before pull request to main):

## General

	We do not yet have access to the test environment, so we do not expect the code to work, but it is the best we have by now, without testing access

	We did a best effort copy of the ssh config for debugging hoping that it will work. Please advise if it is appropriate, it is possible we accidentally included something that is only relevant to ueransim

	The alpine image from Minione that we used for limited local testing needs Python installed before we can apply the playbook. Is that also the case in 6GLibrary, 
	or do we get a version of the Alpine image that is ready to run Ansible playbooks?

 	We prefixed the variables with one_ assuming this was to identify the hypervisor based on the ueransim example, but we do not know if this is best practice. The coude would look a bit cleaner if we did not use the prefix. Recomendation?
	
## Runner.tf.j2
	Copied and modified this from ueransim. Is it still appropriate for our component when we are not providing a custom VM (we install on generic Alpine Linux)  
	template_id = {{ site_available_components.runner.template_id }}

	# How do we discover the correct ID to use to get an Alpine Linux, similar to the one provided with Minione?
	image_id = "Alpine Linux 3.17" # TODO: how to do this correctly?

	We copied the network set-up logic from ueransim, please advise if it is appropriate. The purpose is to allow the person deploying the Component to provision relevant network access, so the KS8500 user can use/create plugins for the runner that connect to the instruments on the test networks and connect to the InfluxDB instance to deliver data

## component_playbook
	Is the assignment for vnet_id correct/appropriate? Tried to guess based on ueransim example
	
	Maybe we should get rid of the base64 encode to make the information more easily readable, but need to check if it creates other problems first
        registration_info: "{{ (registration_info.stdout | b64encode) }}" 
