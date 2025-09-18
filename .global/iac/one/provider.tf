terraform {
  required_providers {
    opennebula = {
      source = "OpenNebula/opennebula"
      version = "~> 1.5"
    }
  }
}

provider "opennebula" {}

resource "opennebula_group" "group" {
  name = "OpenNebula"
}
