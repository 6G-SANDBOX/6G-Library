#!/usr/bin/env bash

ansible-playbook -v playbook_00_generate_deployment_playbooks.yml

ARG_DEFS=(
  "--environment=(.*)"
  "[--product-version=(.*)]"
  "[--deployment-tags=(.*)]"
)

function init() {
    
  export PRODUCT_VERSION=${PRODUCT_VERSION:-$(get_version)}
  export DEPLOYMENT_GROUP=${DEPLOYMENT_GROUP:-canary}
  export DEPLOYMENT_TAGS=${DEPLOYMENT_TAGS:-}
  export SKIP_TAGS=${SKIP_TAGS:-}
  export ANSIBLE_HOST_KEY_CHECKING=${ANSIBLE_HOST_KEY_CHECKING:-False}

  cd deploy

  ANSIBLE_ARGS=" -e @config.yml -i inventories/$ENVIRONMENT"
  echo "Deploying version $PRODUCT_VERSION to environment $ENVIRONMENT"
}

# Create and update the deployment to "pending" status.
# Always ends successfully (this task is optional).
function init_deployment() {
  DEPLOYMENT_ID=$(create_deployment) && update_deployment $DEPLOYMENT_ID "pending" || DEPLOYMENT_ID=""
}

# Complete the deployment by updating to the status passed as first argument.
function end_deployment() {
  local status=$1
  [ -z "$DEPLOYMENT_ID" ] || update_deployment "$DEPLOYMENT_ID" "$status" || return 0
}

function run() {

  init_deployment

  ansible-galaxy install -r requirements.yml -p ./roles && \
    ansible-playbook -v playbook_00_generate_deployment_playbooks.yml $ANSIBLE_ARGS && \
    ansible-playbook -v playbook.yml $ANSIBLE_ARGS && \
#    ../scripts/test-e2e.sh && \
    local status="success" || local status="failure"

  end_deployment "$status"

  # Undo hop machine requirements
  if [ -f inventories/hop_$ENVIRONMENT.key ]; then
    ssh-add -d inventories/hop_$ENVIRONMENT.pub
    git checkout -- inventories/hop_$ENVIRONMENT.key
  fi

  ansible-vault view --vault-password-file ~/.vault_pass.txt vars/ctpd-$ENVIRONMENT-vault.yml | shyaml get-value vault.admin_credentials.cert_pub > inventories/onlife.pub
  ssh-add -d inventories/onlife.pub
  rm inventories/onlife.key
  rm inventories/onlife.pub

  [ "$status" == "success" ] && return 0 || return 1
}