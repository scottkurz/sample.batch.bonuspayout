# works in dev mode via
  mvn -Pfabric docker:start liberty:dev
# Works in pipeline via:
  mvn -Pfabric pre-integration-test liberty:create liberty:install-feature liberty:start liberty:deploy failsafe:integration-test liberty:stop docker:stop failsafe:verify
# devc
 mvn -DkeepTempDockerfile -DreuseDB -Pfabric pre-integration-test liberty:devc
   # then 
   mvn -Pfabric docker:stop
   




