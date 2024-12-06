#!/bin/sh

awk '/.*=.*/{print "export", $0}' /etc/open5gcore/*_env