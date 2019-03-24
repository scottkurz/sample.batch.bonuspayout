package mypkg;

import static org.junit.Assert.assertTrue;

import java.util.Properties;
import java.util.concurrent.TimeoutException;

import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.batch.test.JsrTestUtils;

public class MyTest {
	
	@Test
	public void testRunToCompletion() throws TimeoutException{
    	Properties jobParms = new Properties();
    	jobParms.setProperty("jp.generateFileNameRoot", "logs/bonusPayoutGen");
		JobExecution jobExec = JsrTestUtils.runJob("BonusPayoutJPA", jobParms, 100000);
		BatchStatus bs = jobExec.getBatchStatus();
		assertTrue("Job didn't successfully complete, status = " + bs, bs.equals(BatchStatus.COMPLETED));
	}

}
