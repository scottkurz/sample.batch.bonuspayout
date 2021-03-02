
1. recreate

    git clone --single-branch --branch slf4j-sample git@github.com:scottkurz/sample.batch.bonuspayout.git # sorry it's not a slimmer sample
    mvn package liberty:dev

2. diagnose SFL4J bad cases (logback-classic is known to be problematic, as I think log4j is?)

[INFO] |  +- ch.qos.logback:logback-classic:jar:1.2.3:compile

[INFO] |  |  \- ch.qos.logback:logback-core:jar:1.2.3:compile

3. diagnose w/ Xtrace (for IBM/OpenJ9 VMs)



If it's a J9 JVM, you could also add this JVM argument to print if removeHandlersForRootLogger is being called (output goes to stderr):

-Xtrace:print=mt,methods={org/slf4j/bridge/SLF4JBridgeHandler.removeHandlersForRootLogger},trigger=method{org/slf4j/bridge/SLF4JBridgeHandler.removeHandlersForRootLogger,jstacktrace}

 
