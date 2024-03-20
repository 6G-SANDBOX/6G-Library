#!/bin/bash

ps -o pid,command -u jenkins | grep ssh | grep 16443 | awk '{print $1}' | xargs kill -9 || true
