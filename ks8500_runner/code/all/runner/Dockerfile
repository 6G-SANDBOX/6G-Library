FROM mcr.microsoft.com/dotnet/runtime:9.0-alpine AS base
ARG OPENTAP_VERSION=9.31.0
ARG RUNNERVERSION=2.0.0
ARG TARGETARCH

# .NET + OpenTAP env
ENV OPENTAP_NO_UPDATE_CHECK=true \
    OPENTAP_RUNNER_HealthEndpoint=true \
    DOTNET_SYSTEM_GLOBALIZATION_INVARIANT=false

# Ensure mirrors are reachable, refresh index, and install required tools/libraries
# - bash is in 'main' (Alpine 3.22)
# - icu-libs supplies ICU runtime; icu-data-full adds full locale data (recommended)
# - wget/unzip used by later RUN and healthcheck

RUN echo "https://dl-cdn.alpinelinux.org/alpine/v3.22/main" > /etc/apk/repositories \
 && echo "https://dl-cdn.alpinelinux.org/alpine/v3.22/community" >> /etc/apk/repositories \
 && apk update \
 && apk add --no-cache bash icu-libs icu-data-full tzdata wget unzip curl


# ---------------------------- build stage ----------------------------
FROM base AS build


# 1) Download the Runner package to a file (don’t pipe to unzip)
RUN wget -nv -O /tmp/Runner.TapPackage \
  "https://packages.opentap.io/4.0/Objects/Packages/Runner?version=${RUNNERVERSION}&os=Linux&architecture=x64"

# 2) Map Buildx TARGETARCH -> OpenTAP architecture strings; download OpenTAP to a file and unzip it
RUN ARCH="$([ "$TARGETARCH" = "arm64" ] && echo arm64 || echo x64)" \
 && echo "Building docker image for architecture: ${ARCH} (${TARGETARCH})" \
 && mkdir -p /opt/tap \
 && wget -nv -O /tmp/opentap.zip \
      "https://packages.opentap.io/4.0/Objects/Packages/OpenTAP?version=${OPENTAP_VERSION}&os=Linux&architecture=${ARCH}" \
 && unzip -d /opt/tap /tmp/opentap.zip \
 && chmod +x /opt/tap/tap \
 && /opt/tap/tap package install /tmp/Runner.TapPackage --no-isolation --non-interactive --no-cache \
 && mkdir -p /opt/tap/Storage /opt/tap/Config /opt/tap/Settings \
 && chown -R app:app /opt/tap \
 && chmod -R a+w /opt/tap \
 && chmod a+x /opt/tap/tap \
 && rm -rf /opt/tap/SessionLogs/* /opt/tap/.opentap_recent_logs

#---------------------------- updatable stage ----------------------------

FROM base AS updatable
COPY --from=build --chown=$APP_UID:$APP_UID /opt/tap /opt/tap
RUN ln -s /opt/tap/tap /usr/local/bin/tap

EXPOSE 20111
HEALTHCHECK --interval=1s --timeout=500ms --start-period=5s --retries=30 CMD wget --no-verbose --tries=1 --spider http://127.0.0.1:20116/healthz || exit 1
WORKDIR /opt/tap
USER $APP_UID
VOLUME [ "/opt/tap" ]

ENTRYPOINT ["tap", "runner", "start", "-v"]

# ---------------------------- sixgsandbox stage ----------------------------
FROM updatable AS sixgsandbox

USER root
RUN apk update && apk add --no-cache python3 py3-pip tshark libcap
COPY preferences /home/app/.config/wireshark/preferences
USER $APP_UID


