package com.ibm.websphere.samples.batch.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ibm.websphere.samples.batch.joboperator.WaitStateException;
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

@MicroShedTest
@SharedContainerConfig(BonusPayoutITContainerConfig.class)
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
//	
//	@Test
//	public void testStartSimple() {
//		appService.start("SimpleBonusPayoutJob");
//	}
	
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
}

