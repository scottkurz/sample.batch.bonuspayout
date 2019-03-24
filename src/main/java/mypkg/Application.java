package mypkg;

import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
@EnableBatchProcessing
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	JobOperator jobOperator = BatchRuntime.getJobOperator();
	
    @Override
    public void run(String... args) throws Exception
    {
    	
    	System.out.println("SKSK: JobOperator class = " + jobOperator.getClass());

    	Properties jobParms = new Properties();
    	jobParms.setProperty("generateFileNameRoot", "logs/bonusPayoutGen");
    	long execId = jobOperator.start("BonusPayoutJPA", jobParms);
    	
    	waitForCompletion(execId);
    }

	private void waitForCompletion(long execId) {

		System.out.println("SKSK: waiting for completion");
		while (true) {
			BatchStatus bs = jobOperator.getJobExecution(execId).getBatchStatus();
               if (bs.equals(BatchStatus.COMPLETED)) {
                       System.out.println("SKSK: Final status = COMPLETED");
                        break;
               } else if (bs.equals(BatchStatus.FAILED)) {
                       System.out.println("SKSK: Final status = FAILED");
                       throw new RuntimeException("Job failed.");
               }
		}
        
	}
}
