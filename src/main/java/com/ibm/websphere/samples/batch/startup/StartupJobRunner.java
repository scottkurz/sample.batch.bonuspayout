/*
 * Copyright 2020 International Business Machines Corp.
 * 
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership. Licensed under the Apache License, 
 * Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ibm.websphere.samples.batch.startup;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.security.RunAs;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.ibm.websphere.samples.batch.util.BonusPayoutConstants;

@Singleton
@RunAs("JOBSTARTER")
public class StartupJobRunner implements BonusPayoutConstants {
	
    private final static Logger logger = Logger.getLogger(BONUS_PAYOUT_LOGGER);

	@Inject
	@ConfigProperty(name = "autoStartBatch")
	private boolean autoStartBatch;

	//@Schedule(hour = "*", minute = "*/2", persistent = false)
	@Schedule(hour = "*", minute = "*", second = "*/12", persistent = false)
	public void runTask() {
		if (autoStartBatch) {
			try {
				JobOperator jobOperator = BatchRuntime.getJobOperator();
				Properties jobParms = new Properties();
				jobParms.setProperty("tableName", "ACCOUNT");
				long execId = jobOperator.start("BonusPayoutJob", null);

				logger.warning("\n\nRunning startup EJB task.\nSee batch job logs for results for jobExec = " + execId +".\n\n");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
