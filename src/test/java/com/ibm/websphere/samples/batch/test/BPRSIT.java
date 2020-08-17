package com.ibm.websphere.samples.batch.test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.microshed.testing.SharedContainerConfig;
import org.microshed.testing.jaxrs.RESTClient;
import org.microshed.testing.jupiter.MicroShedTest;

import com.ibm.websphere.samples.batch.joboperator.JobOperatorResource;

@MicroShedTest
@SharedContainerConfig(BonusPayoutITContainerConfig.class)
public class BPRSIT {

	@RESTClient
	public static JobOperatorResource appService;


	@Test
	public void testAppResponse() {
		assertEquals("Batch is in the house", appService.getRequest());
	}

}

