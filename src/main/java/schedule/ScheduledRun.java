package schedule;

import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.security.RunAs;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;



//@Startup
@RunAs("JOBSTARTER")
@Singleton
public class ScheduledRun {
    private final static Logger logger = Logger.getLogger("BonusPayout");

    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void doSomething() {
    	
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        Properties props = new Properties();
        props.setProperty("generateFileNameRoot","logs/bonusPayoutGen");
        		
        jobOperator.start("BonusPayoutJob", props);

    }
    
 }

