package com.ibm.websphere.samples.batch.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ibm.websphere.samples.batch.joboperator.WaitStateException;
import com.ibm.websphere.samples.batchee.jaxrs.RestEntry;
import com.ibm.websphere.samples.batchee.jaxrs.RestJobExecution;
import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.BasicAuthConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import com.ibm.websphere.samples.batch.joboperator.JBatchResource;
import com.ibm.websphere.samples.batch.joboperator.JobOperatorResource;
import com.ibm.websphere.samples.batchee.jaxrs.RestProperties;

import javax.batch.runtime.BatchStatus;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@MicroShedTest
public class BPRSIT {

	@RESTClient
	@BasicAuthConfig(user = "bob", password="bobpwd")
	public static JobOperatorResource appService;


	@RESTClient
	@BasicAuthConfig(user = "bob", password="bobpwd")
	public static JBatchResource batchService;
	
	@Test
	public void testAppResponse() {
		assertEquals("Batch is in the house", appService.getRequest());
	}

	@Test
	public void testStartSimple() {
		appService.start("SimpleBonusPayoutJob");
	}
	
	@Test
	public void testStartSimpleREST() {
		long execId = batchService.start("SimpleBonusPayoutJob", new RestProperties());
	}

	@Test
	public void testRunSimpleREST() throws WaitStateException {
		long execId = batchService.start("SimpleBonusPayoutJob", new RestProperties());
	    RestJobExecution restJobExecution = batchService.waitForJobExecution(execId);
	    assertEquals(BatchStatus.COMPLETED, restJobExecution.getBatchStatus());
	}

	@Test
	public void testRunToCompletion() throws WaitStateException {
		Properties props = new Properties();
		props.setProperty("generateFileNameRoot","logs/bonusPayoutGen");
		long execId = batchService.start("BonusPayoutJob", RestProperties.wrap(props));
		RestJobExecution restJobExecution = batchService.waitForJobExecution(execId);
		assertEquals(BatchStatus.COMPLETED, restJobExecution.getBatchStatus());
	}

	@Test
	public void testLotsOfRecords() throws WaitStateException {
		Properties props = new Properties();
		props.setProperty("generateFileNameRoot","logs/bonusPayoutGen");
		props.setProperty("numRecords","6000");
		long execId = batchService.start("BonusPayoutJob", RestProperties.wrap(props));
		RestJobExecution restJobExecution = batchService.waitForJobExecution(execId);
		assertEquals(BatchStatus.COMPLETED, restJobExecution.getBatchStatus());
	}
	@Test
	public void testForceFailure() throws WaitStateException {

		Properties props = new Properties();
		props.setProperty("generateFileNameRoot","logs/bonusPayoutGen");
		props.setProperty("forceFailure","500");

		long execId = batchService.start("BonusPayoutJob", RestProperties.wrap(props));
		RestJobExecution restJobExecution = batchService.waitForJobExecution(execId);
		assertEquals(BatchStatus.FAILED, restJobExecution.getBatchStatus());

		// Won't fail the second time since we should be past the record to fail on now (i.e. the failure count
		// is indexed from the entire, original data set, not the data set remaining upon restart)
		long restartExecId = batchService.restart(execId, RestProperties.wrap(props));
		RestJobExecution restartJobExecution = batchService.waitForJobExecution(restartExecId);
		assertEquals(BatchStatus.COMPLETED, restartJobExecution.getBatchStatus());
	}
}

