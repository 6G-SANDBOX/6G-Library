#!/bin/bash

ps -o pid,command -u jenkins | grep ssh | awk '{print $1, $8}' | xargs kill -9 

exit 0