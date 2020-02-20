# TO RECREATE ISSUE

1. mvn clean liberty:dev
2. In dev mode, edit src/main/liberty/config/server.xml and uncomment (activate) feature: `<feature>ejbLite-3.2</feature>`
3. `ls target/liberty/wlp/lib/features/*ejb*`

Note you will NOT see the cdi+ejb autofeature:
target/liberty/wlp/lib/features/com.ibm.websphere.appserver.cdi2.0-ejb3.2.mf  

Note the issue goes away if you run with  `-Dliberty.runtime.version=19.0.0.9`   

Current version is 20.0.0.2...also known to fail at 19.0.0.12.


`
