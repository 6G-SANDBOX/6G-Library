FROM mcr.microsoft.com/dotnet/runtime:8.0-alpine AS prod

ARG OPENTAPVERSION=9.27.1
ARG RUNNERVERSION=1.13.0
ENV OPENTAP_RUNNER_HealthEndpoint=true
EXPOSE 20111

# OpenTAP tap executable requires bash
RUN apk add bash

# Install OpenTAP in an official way - at /opt/tap
RUN wget -O opentap.zip "https://packages.opentap.io/4.0/Objects/Packages/OpenTAP?version=$OPENTAPVERSION&os=Linux&architecture=x64"
RUN mkdir /opt/tap
RUN unzip -q /opentap.zip -d /opt/tap
# This base image does not have bash, so we need to change the first line to /bin/sh in the tap script
RUN sed -i '1s/^.*$/#!\/bin\/sh/' /opt/tap/tap
RUN chmod +x /opt/tap/tap
# Create a symlink to the tap script in /usr/local/bin
RUN ln -s /opt/tap/tap /usr/local/bin/tap

# Install the Runner package (signed and obfuscated)
# COPY ./Runner.*.TapPackage ./Runner.TapPackage
RUN wget -O /opt/tap/Runner.TapPackage "https://packages.opentap.io/4.0/Objects/Packages/Runner?version=$RUNNERVERSION&os=Linux&architecture=x64"
RUN tap package install /opt/tap/Runner.TapPackage --no-isolation --non-interactive

HEALTHCHECK --interval=1s --timeout=500ms --start-period=5s --retries=30 CMD wget --no-verbose --tries=1 --spider http://127.0.0.1:20116/healthz || exit 1

WORKDIR /opt/tap

RUN chmod -R a+w /opt/tap
RUN chmod a+x /opt/tap/tap

# Update the package list and install dependencies for python venv plugins
# TVD this step fails during creating image. let disable it temporary 
# RUN apk update && apk add --no-cache python3 py3-pip tshark libcap

USER $APP_UID
# TVD: comment out temporary for debug purpose
# COPY preferences /home/app/.config/wireshark/preferences

ENTRYPOINT "tap" "runner" "start" "-v"
