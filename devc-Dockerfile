FROM openliberty/open-liberty:full-java11-openj9-ubi

# Add Liberty server configuration including all necessary features
COPY --chown=1001:0  src/main/liberty/config/server.xml /config/server.xml
COPY --chown=1001:0  src/main/liberty/config/configDropins/defaults/derby.xml /config/configDropins/defaults/derby.xml
COPY --chown=1001:0  src/main/liberty/config/postgresql.xml /config/postgresql.xml
COPY --chown=1001:0  target/liberty/wlp/usr/servers/defaultServer/configDropins/defaults/system-test-vars.xml /config/configDropins/defaults/system-test-vars.xml
COPY --chown=1001:0  target/liberty/wlp/usr/servers/defaultServer/configDropins/overrides/liberty-plugin-variable-config.xml /config/configDropins/overrides/liberty-plugin-variable-config.xml
COPY --chown=1001:0  target/liberty/wlp/usr/servers/defaultServer/bootstrap.properties /config/bootstrap.properties
COPY --chown=1001:0  target/liberty/wlp/usr/shared/resources/derby-10.13.1.1.jar  /opt/ol/wlp/usr/shared/resources/
COPY --chown=1001:0  target/liberty/wlp/usr/shared/resources/postgresql-42.2.8.jar  /opt/ol/wlp/usr/shared/resources/

COPY --chown=1001:0  target/batch-bonuspayout-application.war  /config/apps/

# Add app
#COPY --chown=1001:0  apps/batch-bonus.war /config/dropins/

# This script will add the requested server configurations, apply any interim fixes and populate caches to optimize runtime
RUN configure.sh
