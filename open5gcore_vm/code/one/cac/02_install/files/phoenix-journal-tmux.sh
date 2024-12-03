#!/bin/sh

#for nf in nrf amf smf upf1 udm ausf pcf; do
#  printf 'tmux new-window -d -n %s "journalctl -a -f --no-hostname -u phoenix@%s"\n' $nf $nf
#done

tmux new-window -d -n nrf "journalctl -a -f --no-hostname -u phoenix@nrf"
tmux new-window -d -n amf "journalctl -a -f --no-hostname -u phoenix@amf"
tmux new-window -d -n smf "journalctl -a -f --no-hostname -u phoenix@smf"
tmux new-window -d -n upf1 "journalctl -a -f --no-hostname -u phoenix@upf1"
tmux new-window -d -n udm "journalctl -a -f --no-hostname -u phoenix@udm"
tmux new-window -d -n ausf "journalctl -a -f --no-hostname -u phoenix@ausf"
tmux new-window -d -n pcf "journalctl -a -f --no-hostname -u phoenix@pcf"