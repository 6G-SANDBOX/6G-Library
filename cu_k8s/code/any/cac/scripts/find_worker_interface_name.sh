#!/bin/bash

interface_name=$(ip -o link show | awk -F': ' 'NR==2 {print $2}')
echo "$interface_name"