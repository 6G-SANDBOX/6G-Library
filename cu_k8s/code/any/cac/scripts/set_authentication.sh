#!/bin/bash

helm registry login registry-1.docker.io -u smecina -p $DOCKERHUB_TOKEN
kubectl create secret docker-registry regcred --docker-username=smecina --docker-password=$DOCKERHUB_TOKEN
