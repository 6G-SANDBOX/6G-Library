#!/bin/sh
# run this in mysql pane of ph_init.
script_dir=$(dirname $(readlink -f "$0"))
for s in $script_dir/*.sql
do
	echo applying $s
	mysql -u root < $s
done
